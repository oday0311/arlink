package Utils;

import Types.*;

import java.util.ArrayList;
import java.util.Arrays;

public class merkleUtils {

    /**
     * Generates the data_root, chunks & proofs
     * needed for a transaction.
     *
     * This also checks if the last chunk is a zero-length
     * chunk and discards that chunk and proof if so.
     * (we do not need to upload this zero length chunk)
     *
     * @param data
     */
    static public Chunks GenerateChunks(byte[] data){
        Chunk[] chunks = chunkData(data);

        Node[] leaves = generateLeaves(chunks);
        Node root = buildLayer(leaves,0); //leaf node level == 0
        Proof[] proofs =   generateProofs(root);


        // Discard the last chunk & proof if it's zero length.
        Chunk lastChunk = chunks[chunks.length-1];
        if (lastChunk.MaxByteRange -lastChunk.MinByteRange == 0 ){

            chunks = Arrays.copyOfRange(chunks,0, chunks.length-1);
            proofs = Arrays.copyOfRange(proofs, 0, proofs.length-1);
        }

        Chunks result = new Chunks();
        result.DataRoot = root.ID;
        result.Proofs = proofs;
        result.Chunks = chunks;

        return result;
    }


   static public Proof[] generateProofs(Node rootNode) {
        byte[] proof = new byte[0];
        return resolveBranchProofs(rootNode, proof, 0);
   }

   static public Proof[] resolveBranchProofs(Node node, byte[] proof, int depth){

        ArrayList<Proof> proofs = new ArrayList<>();
        if (node.Type == Const.LeafNodeType)
        {
            Proof p = new Proof();
            p.Offest = node.MaxByteRange -1;

            ArrayList<byte[]> proofData= new ArrayList<>();
            proofData.add(proof);
            proofData.add(node.DataHash);
            proofData.add(intToBuffer(node.MaxByteRange));
            p.Proof =  ConcatBuffer(proofData);

            proofs.add(p);



            Proof[] result = new Proof[proofs.size()];

            return proofs.toArray(result);
        }

        if (node.Type == Const.BranchNodeType){
            byte[] partialProof = null;
            {
                ArrayList<byte[]> datas = new ArrayList<>();
                datas.add(proof);
                datas.add(node.LeftChild.ID);
                datas.add(node.RightChild.ID);
                datas.add(intToBuffer(node.ByteRange));
                partialProof = ConcatBuffer(datas);
            }
            Proof [] leftProofs = resolveBranchProofs(node.LeftChild, partialProof, depth+1);
            Proof [] rightProofs = resolveBranchProofs(node.RightChild, partialProof, depth+1);

            for (int i =0;i<leftProofs.length;i++)
            {
                proofs.add(leftProofs[i]);
            }
            for(int j = 0;j<rightProofs.length;j++)
            {
                proofs.add(rightProofs[j]);
            }


            Proof[] result = new Proof[proofs.size()];

            return proofs.toArray(result);
        }

        return null;
   }

   static public Node buildLayer(Node[] nodes, int level)
   {
       Node result = new Node();
       if (nodes.length==1)
       {
           result = nodes[0];
           return result;
       }

       ArrayList<Node> nexyLayer = new ArrayList<>();

       for (int i = 0;i<nodes.length;i=i+2)
       {
           Node left = nodes[i];
           Node right = null;
           if (i+1<nodes.length)
           {
               right = nodes[i+1];
           }
           Node nextNode = hashBranch(left, right);
           nexyLayer.add(nextNode);
       }

       Node[] nodeArray = new Node[nexyLayer.size()];
       for(int i = 0;i<nexyLayer.size();i++)
       {
           nodeArray[i] = nexyLayer.get(i);
       }
       result = buildLayer(nodeArray, level+1);
       return result;
   }

   static public Node hashBranch(Node left, Node right){
        if (right==null){
            return left;
        }

        byte[] hLeafNodeId = sha256.getSHA256StrJava(left.ID);
        byte[] hRightNodeId = sha256.getSHA256StrJava(right.ID);

        byte[] hLeafNodeMaxRange = sha256.getSHA256StrJava(intToBuffer(left.MaxByteRange));


        ArrayList<byte[]> datas = new ArrayList<>();
        datas.add(hLeafNodeId);
        datas.add(hRightNodeId);
        datas.add(hLeafNodeMaxRange);
        byte[] id =  Hash(datas);

        Node branchNode = new Node();


        branchNode.Type = Const.BranchNodeType;
        branchNode.ID = id;
        branchNode.DataHash = null;
        branchNode.MinByteRange = 0;
        branchNode.MaxByteRange = right.MaxByteRange;
        branchNode.ByteRange = left.MaxByteRange;
        branchNode.LeftChild = left;
        branchNode.RightChild = right;
        return branchNode;

   }
    static public Chunk[] chunkData(byte[] data){
        int cursor = 0;
        byte[] rest = data;
        ArrayList<Chunk> result = new ArrayList<>();
        while (rest.length >= Const.MAX_CHUNK_SIZE){
            long chunkSize = Const.MAX_CHUNK_SIZE;
            long nextChunkSize = rest.length - Const.MAX_CHUNK_SIZE;
            // 查看下一轮的chunkSize 是否小于最小的size，如果是则在这轮中调整chunk size 的大小
            if (nextChunkSize> 0 && nextChunkSize < Const.MIN_CHUNK_SIZE){
                double dec = Math.ceil(new Double(rest.length / 2.0));
                chunkSize = (new Double(dec)).intValue();
            }

            byte[] chunk = Arrays.copyOfRange(rest, 0, (int)chunkSize);
            byte[] dataHash = sha256.getSHA256StrJava(chunk);
            cursor = cursor + chunk.length;

            Chunk c = new Chunk(dataHash, cursor-chunk.length, cursor);
            result.add(c);

            rest = Arrays.copyOfRange(rest,(int)chunkSize, rest.length);
        }

        //
        byte[] hash = sha256.getSHA256StrJava(rest);
        Chunk c = new Chunk(hash, cursor, cursor+rest.length);
        result.add(c);

        Chunk[] resultArray = new Chunk[result.size()];
        return result.toArray(resultArray);
    }

    static public Node[] generateLeaves(Chunk[] chunks){
        ArrayList<Node> leafs = new ArrayList<>();

        for (int i = 0;i<chunks.length;i++)
        {
            Chunk c = chunks[i];
            Node n = new Node();
            n.Type = Const.LeafNodeType;
            n.DataHash = c.DataHash;
            n.MaxByteRange = c.MaxByteRange;
            n.MinByteRange = c.MinByteRange;
            n.ByteRange = 0;
            n.LeftChild = null;
            n.RightChild = null;
            {
               byte[] data1 = sha256.getSHA256StrJava(c.DataHash);
               byte[] data2 = sha256.getSHA256StrJava(intToBuffer(c.MaxByteRange));

               byte[] contractByte= byteMerger(data1, data2);
                n.ID = sha256.getSHA256StrJava(contractByte);
            }

            leafs.add(n);

        }

        Node[] leafsArray = new Node[leafs.size()];
        return leafs.toArray(leafsArray);
    }


    static byte[] intToBuffer(int note)
    {
        byte[] buffer = new byte[Const.NOTE_SIZE];
        for (int i =buffer.length-1;i>=0;i--)
        {
            int byt = note % 256;
            buffer[i] = (byte)(byt & 0xFF);
            note = (note-byt)/256;
        }
        return buffer;
    }


    static byte[] Hash(ArrayList<byte[]> data){
        byte[] content = ConcatBuffer(data);


        byte[] result = sha256.getSHA256StrJava(content);
        return result;
    }

    public static byte[] byteMerger(byte[] bt1, byte[] bt2){
        byte[] bt3 = new byte[bt1.length+bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }


    static byte[] ConcatBuffer(ArrayList <byte[]>buffers){
        int totalLength = 0;
        for(int i = 0;i<buffers.size();i++)
        {
            totalLength = totalLength + buffers.get(i).length;
        }

        byte[] temp = new byte[totalLength];
        int start = 0;
        for (int i =0;i<buffers.size();i++)
        {
            byte[] data = buffers.get(i);
            for (int j = 0;j<data.length;j++)
            {
                temp[start] = data[j];
                start = start+1;
            }
        }

        return temp;
    }

    static byte[] ConcatBuffer(byte[][] buffers){
        int totalLength = 0;
        for(int i = 0;i<buffers.length;i++)
        {
            totalLength = totalLength + buffers[i].length;
        }

        byte[] temp = new byte[totalLength];
        int start = 0;
        for (int i =0;i<buffers.length;i++)
        {
            byte[] data = buffers[i];
            for (int j = 0;j<data.length;j++)
            {
                temp[start] = data[j];
                start = start+1;
            }
        }

        return temp;
    }
}

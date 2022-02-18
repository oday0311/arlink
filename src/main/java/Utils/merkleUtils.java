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
        return null;
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

        Chunk[] resultArray = new Chunk[rest.length];
        return result.toArray(resultArray);
    }

    static public Node[] generateLeaves(Chunk[] chunks){


        return null;
    }

    static public Node buildLayer(Node[] nodes, int level){

        return null;
    }
}

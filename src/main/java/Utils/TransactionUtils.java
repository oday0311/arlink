package Utils;

import Types.*;
import walletCore.Wallet;

import java.util.ArrayList;
import java.util.Arrays;

import static Utils.Hash.DeepHash;
import static Utils.merkleUtils.GenerateChunks;

public class TransactionUtils {


    static public Transaction SignTransaction(Transaction tx, Wallet w) throws Exception{
        byte[] signData = GetSignatureData(tx);

        byte[] signature = w.walletSign(signData);

        byte[] txId = sha256.getSHA256StrJava(signature);
        tx.id = base64.encode(txId);
        tx.signature = base64.encode(signature);


        return tx;
    }

    static public byte[] GetSignatureData(Transaction tx) throws Exception
    {
        switch (tx.format) {
            case 1:
                //todo
                break;
            case 2:
                byte[] data = base64.decode(tx.data);
                //prepare chunk
                tx = PrepareChunk(tx, data);
                //
                {
                    //todo tags:
                }
            ArrayList<Object> dataList = new ArrayList<>();
            String formatStr = base64.encode("" + tx.format);
            dataList.add(formatStr);
            dataList.add(tx.owner);
            dataList.add(tx.target);
            String quantityStr = base64.encode(tx.quantity.getBytes());
            dataList.add(quantityStr);
            String rewardStr = base64.encode(tx.reward.getBytes());
            dataList.add(rewardStr);
            dataList.add(tx.last_tx);
            //dataList.add(tags);
                String datasizeStr = base64.encode(tx.data_size.getBytes());
                dataList.add(datasizeStr);
                if (tx.data_root != null) {
                    dataList.add(tx.data_root);
                }
                byte[] hash = DeepHash(dataList);
                return hash;

            default:
                System.out.println("unknown format");
        }

        return null;
    }

    static public Transaction PrepareChunk(Transaction tx, byte[] data)
    {
        if (tx.chunks==null && data.length>0){
            tx.chunks = GenerateChunks(data);
            tx.data_root = base64.encode(tx.chunks.DataRoot);
        }

        if (tx.chunks==null && data.length == 0) {
            tx.chunks = new Chunks();
        }
        return tx;
    }
    static public GetChunk GetChunkFromTx(Transaction tx, int index, byte[] data){
        if (tx.chunks==null){
            System.out.println("the transaction is not prepared.");
            return null;
        }
        Proof proof = tx.chunks.Proofs[index];
        Chunk chunk = tx.chunks.Chunks[index];

        GetChunk result = new GetChunk();
        result.DataRoot = tx.data_root;
        result.DataSize = tx.data_size;
        result.DataPath = base64.encode(proof.Proof);
        result.Offset = "" + proof.Offest;
        byte[] cData = Arrays.copyOfRange(data,chunk.MinByteRange, chunk.MaxByteRange);
        result.Chunk = base64.encode(cData);


        return result;
    }

}

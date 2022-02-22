package Utils;

import Types.Chunks;
import Types.Transaction;
import walletCore.Wallet;

import java.util.ArrayList;

import static Utils.Hash.DeepHash;
import static Utils.merkleUtils.GenerateChunks;

public class TransactionUtils {


    static public Transaction SignTransaction(Transaction tx, Wallet w) throws Exception{
        byte[] signData = GetSignatureData(tx);

        byte[] signature = w.walletSign(signData);

        byte[] txId = sha256.getSHA256StrJava(signature);
        tx.ID = base64.encode(txId);
        tx.signature = base64.encode(signature);


        return tx;
    }

    static byte[] GetSignatureData(Transaction tx) throws Exception
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
            dataList.add(tx.lastTx);
            //dataList.add(tags);
                String datasizeStr = base64.encode(tx.dataSize.getBytes());
                dataList.add(datasizeStr);
                if (tx.dataRoot != null) {
                    dataList.add(tx.dataRoot);
                }
                byte[] hash = DeepHash(dataList);
                return hash;

            default:
                System.out.println("unknown format");
        }

        return null;
    }

    static Transaction PrepareChunk(Transaction tx, byte[] data)
    {
        if (tx.chunks==null && data.length>0){
            tx.chunks = GenerateChunks(data);
            tx.dataRoot = base64.encode(tx.chunks.DataRoot);
        }

        if (tx.chunks==null && data.length == 0) {
            tx.chunks = new Chunks();
        }
        return tx;
    }

}

package Utils;

import Types.Chunks;
import Types.Transaction;

import static Utils.merkleUtils.GenerateChunks;

public class TransactionUtils {

    static byte[] GetSignatureData(Transaction tx)
    {
        switch (tx.format) {
            case 1:
                //todo
                break;
            case 2:
                byte[] data = base64.decode(tx.data);
                //prepare chunk

                break;
            default:
                System.out.println("unknown format");
        }

        return null;
    }

    static void PrepareChunk(Transaction tx, byte[] data)
    {
        if (tx.chunks==null && data.length>0){
            tx.chunks = GenerateChunks(data);
            tx.dataRoot = base64.encode(tx.chunks.DataRoot);
        }

        if (tx.chunks==null && data.length == 0) {
            tx.chunks = new Chunks();
        }
        return;
    }

}

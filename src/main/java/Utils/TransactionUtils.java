package Utils;

import Types.Transaction;

public class TransactionUtils {

    static byte[] GetSignatureData(Transaction tx)
    {
        switch (tx.format) {
            case 1:
                //todo
                break;
            case 2:
                byte[] data = base64.decode(tx.data);

                break;
            default:
                System.out.println("unknown format");
        }

        return null;
    }
}

package walletCore.uploader;

import Types.Transaction;
import walletCore.Client;

public class TransactionUploader {
    public Client client;
    public int chunkIndex;
    public boolean txPosted;
    public Transaction transaction;
    public long lastRequestTimeEnd;
    public int lastResponseStatus;
    public String lastResponseError;

    public byte[] data;
    public int totalErrors;

}

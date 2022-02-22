package walletCore.uploader;

import Types.Transaction;

public class SerializedUploader {

    public int chunkIndex;
    public boolean txPosted;
    public Transaction transaction;
    long lastRequestTimeEnd;
    int lastResponseStatus;
    String lastResponseError;

}

package walletCore.uploader;

import Types.Chunks;
import Types.Const;
import Types.GetChunk;
import Types.Transaction;
import Utils.TransactionUtils;
import Utils.base64;
import Utils.merkleUtils;
import walletCore.Client;

import java.util.Date;

public class Uploader {

    public Client client;

    public Transaction transaction;
    public int chunkIndex;
    public boolean txPosted;

    long lastRequestTimeEnd;
    int lastResponseStatus;
    String lastResponseError;
    public int totalErrors;
    public byte[] data;



    static  public Uploader createUploader(Const.UploaderType type, Transaction tx, Client client){


        if (type==Const.UploaderType.TransactionUploader)
        {
            Uploader uploader =  initUpdateLoader(tx, client);
            return uploader;
        }

        return null;

    }

    static public Uploader initUpdateLoader(Transaction tx, Client client)
    {
        if (tx.id == null )
        {
            return null;
        }

        if (tx.chunks == null)
        {
            System.out.println("warning , transactions not prepared.");
        }

        TransactionUploader uploader = new TransactionUploader();
        uploader.transaction = new Transaction();
        uploader.client = client;
        uploader.data = base64.decode(tx.data);

        uploader.transaction.format = tx.format;
        uploader.transaction.id = tx.id;
        uploader.transaction.last_tx = tx.last_tx;
        uploader.transaction.owner = tx.owner;
        uploader.transaction.tags = tx.tags;
        uploader.transaction.target = tx.target;
        uploader.transaction.quantity = tx.quantity;
        uploader.transaction.data = "";
        uploader.transaction.data_size = tx.data_size;
        uploader.transaction.data_root = tx.data_root;
        uploader.transaction.reward = tx.reward;
        uploader.transaction.signature = tx.signature;
        uploader.transaction.chunks = tx.chunks;


        return uploader;

    }

    public int TotalChunks()
    {
        if (this.transaction.chunks==null)
        {
            return 0;
        }
        else{
            if (this.transaction.chunks.Chunks == null){
                return 0;
            }
            return this.transaction.chunks.Chunks.length;
        }
    }

    public int UploadedChunks(){
        return this.chunkIndex;
    }

    public boolean IsComplete(){
        Chunks data = this.transaction.chunks;
        if (data==null)
        {
            return false;
        }
        else{
            boolean result =  this.txPosted && (this.chunkIndex==data.Chunks.length) || this.txPosted && data.Chunks.length==0;
            return result;
        }
    }

    public void Once(){
        while (!this.IsComplete()){
            System.out.println("upload chunks.....");
            this.uploadChunk();
        }
    }



    public int uploadChunk()
    {
        System.out.println("chunks upload: uploaded chunks: " +  UploadedChunks() + " / total: " + TotalChunks());
        if (this.lastResponseError!=""){
            this.totalErrors ++;
        }
        else
        {
            this.totalErrors = 0;
        }

        //if total error reach out 100, break;
        if ( this.totalErrors == 100)
        {
            System.out.println("too many errors detect, try later.");
            return 1;
        }



        float delay = 0.0f;
        if (this.lastResponseError!="")
        {
            Date date =new Date();

            long ts = date.getTime();
            delay = Math.max(this.lastRequestTimeEnd+Const.ERROR_DELAY- ts, Const.ERROR_DELAY);
        }

        if (delay>0.0) {
            delay = (float) (delay - delay * 0.3 * Math.random());
            if (delay > 4 * 1000)
            {
                delay = 4 * 1000;
            }
            try {
                Thread.sleep((long)delay );
            }catch (Exception e)
            {
                System.out.println("delay sleep .....");
            }
        }

        this.lastResponseError = "";
        if (!this.txPosted){
            return this.postTransaction();
        }


        //todo....
        GetChunk chunk = TransactionUtils.GetChunkFromTx(this.transaction, this.chunkIndex, this.data);
        byte[] path = base64.decode(chunk.DataPath);
        int offset = Integer.getInteger(chunk.Offset).intValue();
        int dataSize = Integer.getInteger(chunk.DataSize).intValue();

        //validate Path
        boolean valid = merkleUtils.ValidatePath();
        if (!valid){
            System.out.println("the valide fail");
            return -1;
        }

        int code = this.client.submitChunk(chunk);

        Date date =new Date();
        long ts = date.getTime();
        this.lastRequestTimeEnd = ts;
        this.lastResponseStatus = code;

        if (code==200){
            this.chunkIndex++;
        }
        else{
            this.lastResponseError = "error "+ code;
            return -1;
        }

        return 0;

    }

    public int postTransaction()
    {
        boolean uploadInBody = this.TotalChunks() <= Const.MAX_CHUNKS_IN_BODY;
        this.uploadTx(uploadInBody);

        return 0;
    }



    public int uploadTx(boolean withBody)
    {
        System.out.println("in upload tx ....");
        if(withBody)
        {
            this.transaction.data = base64.encode(this.data);
        }
        int code = this.client.submitTransaction(this.transaction);

        if (code >= 400){
            this.lastResponseError = "error " + code;
            this.lastResponseStatus = code;
            return -1;
        }

        Date date =new Date();
        long ts = date.getTime();
        this.lastRequestTimeEnd = ts;
        this.lastResponseStatus = code;
        if (withBody){
            this.transaction.data = "";
        }

        //tx already processed
        if (code >= 200 && code < 300)
        {
            this.txPosted = true;
            if (withBody){
                this.chunkIndex = Const.MAX_CHUNKS_IN_BODY;
            }
            return 0;
        }
        //end

        if (withBody){
            this.lastResponseError = "";
        }
        return 0;


    }

}

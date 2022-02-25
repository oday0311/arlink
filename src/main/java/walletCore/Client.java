package walletCore;

import Types.GetChunk;
import Types.Transaction;
import Utils.JsonUtils;
import okhttp3.*;

import java.io.IOException;


public class Client {
    public OkHttpClient httpClient = new OkHttpClient();
    public static String baseUrl = "https://arweave.net"; //default
    public void setup(String url)
    {
        httpClient = new OkHttpClient();
        baseUrl = url;

    }

    public void getNetworkInfo(Callback callback)
    {
        String url = Client.baseUrl+"/info";
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void getPeers(Callback callback){
        String url = Client.baseUrl+"/peers";
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }


    public void GetTransactionByID(String id ,Callback callback){
        String url = Client.baseUrl+"/tx/" + id;
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetTransactionStatus(String id ,Callback callback){
        String url = Client.baseUrl+"/tx/" + id + "/status";
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetTransactionStatus(String id , String field,Callback callback){
        String url = Client.baseUrl+"/tx/" + id + "/" + field;
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetTransactionTags(String id ,Callback callback){
        String url = Client.baseUrl+"/tx/" + id + "/" + "tags";
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetTransactionData(String id, String extension, Callback callback){
        String url = Client.baseUrl+"/tx/" + id + "/" + "data";
        if (extension.length()>0)
        {
            url = url+"."+extension;
        }
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetTransactionDataByGateway()
    {
        //todo
    }


    public void GetTransactionPrice(byte[] data, String target, Callback callback){

        long length = 0; // 0 data size for sending ar to targer price .
        if (data==null) {
          length =  data.length;
        }
        String url = Client.baseUrl+"/price/" + length;
        if (target.length()>0)
        {
            url = url+"/"+target;
        }
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );

    }

    public String syncGetTransactionPrice(byte[] data, String target){
        long length = 0; // 0 data size for sending ar to targer price .
        if (data!=null) {
            length =  data.length;
        }
        String url = Client.baseUrl+"/price/" + length;
        if (target.length()>0)
        {
            url = url+"/"+target;
        }
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        try {
            Response res = call.execute();
            String price = res.body().string();
            return price;
        }catch (Exception e){
            System.out.println(e.toString());
        }


        return "0";

    }

    public void GetTransactionAnchor(Callback callback){
        String url = Client.baseUrl+"/tx_anchor";

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public String syncGetTransactionAnchor(){
        String url = Client.baseUrl+"/tx_anchor";

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);

        try {
            Response res = call.execute();
            String result = res.body().string();
            return result;
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return null;
    }



    public void SubmitTransaction(Transaction tx, Callback callback)
    {
        //todo
    }

    public void SubmitChunks(GetChunk gc, Callback callback){
        //todo
    }

    public void GraphQL(String query, Callback callback){
        //todo
    }

    public void GetWalletBalance(String address, Callback callback){
        String url = Client.baseUrl+"/wallet/" + address+ "/balance";

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }


    public void GetLastTransactionID(String address, Callback callback){
        String url = Client.baseUrl+"/wallet/" + address+ "/last_tx";

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetBlockById(String id, Callback callback){
        String url = Client.baseUrl+"/block/hash/"+ id;

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetBlockByHeight(long height, Callback callback){
        String url = Client.baseUrl+"/block/height/"+ height;

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }


    public void GetChunk(long offset, Callback callback){
        String url = Client.baseUrl+"/chunk/" + offset;

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetChunkData(long offset, Callback callback){
        String url = Client.baseUrl+"/chunk/" + offset;

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetTransactionOffset(String offset, Callback callback){
        String url = Client.baseUrl+"/tx/" + offset + "/offset";

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }


    public void DownloadChunkData(String id, Callback callback){
        String url = Client.baseUrl+"/tx/" + id + "/offset";

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetUnconfirmedTx(String arId, Callback callback){
        String url = Client.baseUrl+"/unconfirmed_tx/" + arId;

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }

    public void GetPendingTxIds(Callback callback){
        String url = Client.baseUrl+"/tx/pending" ;

        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(
                callback
        );
    }


    public int submitTransaction(Transaction tx){
        //httpPost "tx", json.
        String url = Client.baseUrl+"/tx";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = JsonUtils.objectToJson(tx);
        RequestBody body = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder().url(url).post(body).build();
        try{
            Call call = httpClient.newCall(request);
            Response res = call.execute();
            String result = res.body().string();
            System.out.println("the post result is " + result);
            return res.code();
        }catch (Exception e)
        {

            System.out.println("the post result is " + e.toString());
            return -1;
        }
    }



    public int submitChunk(GetChunk chunk){
        //httpPost "tx", json.
        String url = Client.baseUrl+"/chunk";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = JsonUtils.objectToJson(chunk);
        RequestBody body = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder().url(url).post(body).build();
        try{
            Call call = httpClient.newCall(request);
            Response res = call.execute();
            String result = res.body().string();
            System.out.println("the post result is " + result);
            return res.code();
        }catch (Exception e)
        {
            System.out.println("the post result is " + e.toString());
            return -1;
        }
    }
}

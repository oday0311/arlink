import Types.Transaction;
import Utils.JsonUtils;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import walletCore.*;

import java.io.IOException;

public class ClientTest {
    static public String testingNode = "http://178.62.222.154:1984";
    @Test
    public void TestGetNetworkInfo() throws Exception{
        Object lock = new Object();
        Client c = new Client();
        c.setup("https://arweave.net");

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
                synchronized (lock) {
                    lock.notify();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                synchronized (lock) {
                    lock.notify();
                }
            }
        };
        c.getNetworkInfo(callback);


        try{
            synchronized (lock)
            {
                lock.wait();
            }
        }
        catch (Exception e)
        {

        }
    }
    @Test
    public void TestPeers() throws Exception{
        Object lock = new Object();
        Client c = new Client();
        c.setup("https://arweave.net");

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
                synchronized (lock) {
                    lock.notify();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                synchronized (lock) {
                    lock.notify();
                }
            }
        };
        c.getPeers(callback);

            synchronized (lock)
            {
                lock.wait();
            }

    }




    @Test
    public void TestGetBlockByHeight() throws Exception{
        Object lock = new Object();
        Client c = new Client();
        c.setup("https://arweave.net");

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
                synchronized (lock) {
                    lock.notify();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                synchronized (lock) {
                    lock.notify();
                }
            }
        };


        c.GetBlockByHeight(793755, callback);

        synchronized (lock)
        {
            lock.wait();
        }
    }




    @Test
    public void TestGetPendingTx() throws Exception{
        Object lock = new Object();
        Client c = new Client();
        c.setup("https://arweave.net");

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
                synchronized (lock) {
                    lock.notify();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                synchronized (lock) {
                    lock.notify();
                }
            }
        };
        c.GetPendingTxIds(callback);

        synchronized (lock)
        {
            lock.wait();
        }

    }

    @Test
    public void TestGetBalance() throws Exception{
        String address = "dQzTM9hXV5MD1fRniOKI3MvPF_-8b2XDLmpfcMN9hi8";
        Object lock = new Object();
        Client c = new Client();
        c.setup("https://arweave.net");

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
                synchronized (lock) {
                    lock.notify();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                synchronized (lock) {
                    lock.notify();
                }
            }
        };
        c.GetWalletBalance(address, callback);

        synchronized (lock)
        {
            lock.wait();
        }
    }
    @Test
    public void TestLastTransactionId() throws Exception{
        String address = "dQzTM9hXV5MD1fRniOKI3MvPF_-8b2XDLmpfcMN9hi8";
        Object lock = new Object();
        Client c = new Client();
        c.setup("https://arweave.net");

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
                synchronized (lock) {
                    lock.notify();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                synchronized (lock) {
                    lock.notify();
                }
            }
        };
        c.GetLastTransactionID(address, callback);

        synchronized (lock)
        {
            lock.wait();
        }
    }

    @Test
    public void TestTransactionPrice() throws Exception{
        String data = "123";
        Object lock = new Object();
        Client c = new Client();
        c.setup("https://arweave.net");

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
                synchronized (lock) {
                    lock.notify();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                synchronized (lock) {
                    lock.notify();
                }
            }
        };
        String target = "";
        c.GetTransactionPrice(data.getBytes(),target, callback);

        synchronized (lock)
        {
            lock.wait();
        }
    }

    @Test
    public void TestGetTransactionAnchor() throws Exception{
        Object lock = new Object();
        Client c = new Client();
        c.setup("https://arweave.net");

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
                synchronized (lock) {
                    lock.notify();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                synchronized (lock) {
                    lock.notify();
                }
            }
        };
        c.GetTransactionAnchor( callback);

        synchronized (lock)
        {
            lock.wait();
        }
    }


    @Test
    public void TestGetTransaction() throws Exception{
        Object lock = new Object();
        Client c = new Client();
        c.setup("https://arweave.net");

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
                synchronized (lock) {
                    lock.notify();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                System.out.println("====================");
                System.out.println(call.request().url().toString());
                System.out.println(response.body().string());
                synchronized (lock) {
                    lock.notify();
                }
            }
        };
        String txId = "ggt-x5Q_niHifdNzMxZrhiibKf0KQ-cJun0UIBBa-yA";
        c.GetTransactionStatus(txId, callback);
        c.GetBlockByHeight(575660, callback);
        c.GetTransactionByID(txId,callback);

        c.GetTransactionTags("gdXUJuj9EZm99TmeES7zRHCJtnJoP3XgYo_7KJNV8Vw", callback);

        synchronized (lock)
        {
            lock.wait();
        }

        //wait for finish
        Thread.sleep(5000);
    }


    @Test
    public void TestSyncGetTransactionPrice()
    {
        Client c = new Client();
        c.setup("https://arweave.net");
        String price = c.syncGetTransactionPrice(null, "eIgnDk4vSKPe0lYB6yhCHDV1dOw3JgYHGocfj7WGrjQ");

        System.out.println("the transaction price is "+ price);
    }

    @Test
    public void TestSubmitTransaction()
    {
        String txId = "n1iKT3trKn6Uvd1d8XyOqKBy8r-8SSBtGA62m3puK5k";
        Client c = new Client();
        c.setup("https://arweave.net");
        c.setup(testingNode);

        String url = Client.baseUrl+"/tx/" + txId;
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();

        Call call = c.httpClient.newCall(request);
        Transaction tx ;
        try {
            Response res = call.execute();
            String result = res.body().string();
            System.out.println("the transaction is " + result);
            tx = JsonUtils.jsonToPojo(result, Transaction.class);
            System.out.println("the transaction content is " + tx.toString());
        }
        catch (Exception e)
        {
            System.out.println("exception is " + e.toString());
            return;
        }




        //post to /tx
            url = Client.baseUrl+"/tx";
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
            String jsonStr = JsonUtils.objectToJson(tx);
            RequestBody body = RequestBody.create(JSON, jsonStr);
            request = new Request.Builder().url(url).post(body).build();
            try{
                call = c.httpClient.newCall(request);
                Response res = call.execute();
                String result = res.body().string();
                System.out.println("the post result is " + result);
            }catch (Exception e)
            {
                System.out.println("the post result is " + e.toString());
            }


    }

}

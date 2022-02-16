import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import walletCore.*;

import java.io.IOException;

public class ClientTest {
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

}

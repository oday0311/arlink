package walletCore;

import Types.Const;
import Types.Tag;
import Types.Transaction;
import Utils.*;
import com.nimbusds.jose.util.Base64URL;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.crypto.signers.PSSSigner;
import walletCore.uploader.Uploader;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Wallet {

    public Client client = new Client();
    public String address;
    public RSAKeyParameters pkey;
    public RSAPrivateCrtKeyParameters privateKey;
    public String n;
    public String e = "AQAB"; // equal to 65536.

    public Wallet setupFromPath()
    {
        //todo
        client = new Client();
        client.setup("https://arweave.net");
        Wallet wallet = new Wallet();

        return wallet;

    }
    public void setClientUrl(String url)
    {
        client.setup(url);
    }

    public Wallet setup(byte[] b, String clientUrl, String proxyUrl){
        Wallet wallet = new Wallet();
        client = new Client();
        client.setup("https://arweave.net");
        return wallet;

    }

    public void setupKeys(RSAKeyParameters pub,
                          RSAPrivateCrtKeyParameters privateKey, String n)
    {
        this.pkey = pub;
        this.privateKey = privateKey;
        this.n = n;

    }


    public  String getOwner(Wallet wallet)
    {

        return wallet.n;
    }




    public String SendAr(BigDecimal amount, String target, ArrayList<Tag> tags) throws Exception
    {
        BigDecimal winsonAmount = winston.ArToWinston(amount);
        BigDecimal amountWithOutFloat = winsonAmount.setScale(0,BigDecimal.ROUND_UP);
        String result = SendWinstonSpeedUp(amountWithOutFloat, target, tags, 0);
        return result;
    }

    public String SendWinstonSpeedUp(BigDecimal amount, String target,ArrayList<Tag>tags, long seepdFactor) throws Exception{
        // 1. get tx price

        String price = client.syncGetTransactionPrice(null, target);

        price = updateReward(price, seepdFactor);



        //2. build transaction
        Transaction tx = new Transaction();

        //init
        tx.format= 2;
        tx.target = target;
        tx.quantity = amount.toString();
        tx.data = "";
        tx.data_size = "0";
        tx.reward = price;
        //tx.tags = ((Tag[]) Tags.TagsDecode(tags).toArray());

        //3.send
        String result = SendTransaction(tx);
        return  result;
    }




    //func (w *Wallet) SendTransaction(tx *types.Transaction) (id string, err error) {
    public String SendTransaction(Transaction tx) throws Exception{
        //1. GetTransactionAnchor
        String anchor = client.syncGetTransactionAnchor();
        System.out.println("the anchor is " + anchor);


        tx.last_tx = anchor;
        tx.owner = getOwner(this);
        System.out.println("get wallet owner " + tx.owner);

        //2. sign
        tx = TransactionUtils.SignTransaction(tx, this);
        String id = tx.id;
        //3.upload tx : todo
        //Uploader uploader = Uploader.createUploader(Const.UploaderType.TransactionUploader, tx, this.client);
        //uploader.Once();
        this.client.submitTransaction(tx);

        String json =JsonUtils.objectToJson(tx);
        System.out.println("the json string is " + json);

        return id;
    }




    public String updateReward(String price, long speedFactor){
        BigDecimal base = new BigDecimal("100");
        BigDecimal speed = new BigDecimal(speedFactor);

        BigDecimal bg_price = new BigDecimal(price);

        BigDecimal reward = (bg_price.multiply((base.add(speed)))).divide(base);

        return reward.toString();
    }


    public byte[] walletSign(byte[] data)
    {

        byte[] result = null;

        PSSSigner pssSigner = new PSSSigner(new RSAEngine(), new SHA256Digest(), 32);
        pssSigner.init(true, new ParametersWithRandom(this.privateKey));
        pssSigner.update(data, 0, data.length);


        try {
            byte[]  s = pssSigner.generateSignature();
            System.out.println("the pss signature is " + Base64URL.encode(s));
            result = s;

            {//verify
                Base64URL base64URL_n = new Base64URL(this.n);
                Base64URL base64URL_e = new Base64URL(this.e);
                RSAKeyParameters pub = new RSAKeyParameters(false,
                        base64URL_n.decodeToBigInteger(),
                        base64URL_e.decodeToBigInteger());


                //verify signature with PssSigner
                pssSigner.init(false, (CipherParameters) pub);
                pssSigner.update(data, 0, data.length);
                System.out.println(pssSigner.verifySignature(s));

            }

        } catch (Exception ex) {
            throw new RuntimeException("Cannot generate pss signature. " + ex.getMessage(), ex);
        }


        return result;

    }


    public void sendData(byte[] data, Tag[] tags) throws Exception
    {
        sendDataSpeedup(data, tags, 0);
    }


    public String sendDataSpeedup(byte[] data, Tag[] tags, long speed) throws Exception{
        String price = client.syncGetTransactionPrice(data, null);

        price = updateReward(price, speed);
        //2. build transaction
        Transaction tx = new Transaction();

        //init
        tx.format= 2;
        tx.target = "";
        tx.quantity = "0";
        tx.data = base64.encode(data);
        tx.data_size = "" + data.length;
        tx.reward = price;

        String result = SendTransaction(tx);
        return result;
    }

}

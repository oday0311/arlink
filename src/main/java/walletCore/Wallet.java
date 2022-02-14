package walletCore;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {

    public Client walletAPIClient;
    public String address;
    public PublicKey pkey;
    public PrivateKey privateKey;

    public Wallet setupFromPath()
    {
        //todo
        Wallet wallet = new Wallet();
        return wallet;

    }

    public Wallet setup(byte[] b, String clientUrl, String proxyUrl){
        Wallet wallet = new Wallet();
        return wallet;

    }


    public  String getOwner(Wallet wallet)
    {
        byte[] data =  wallet.pkey.getEncoded();
        String owner = new String(data);
        return owner;
    }


    //RSASSA-PSS signature scheme according to RFC 8017.
    public String signature()
    {
        return "";
    }
}

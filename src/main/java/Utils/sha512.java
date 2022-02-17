package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sha512 {

    public static byte[] getSHA384StrJava(byte[] data){
        MessageDigest messageDigest;
        byte[] encodeStr = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-384");
            messageDigest.update(data);
            encodeStr = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
}

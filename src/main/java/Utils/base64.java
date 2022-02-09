//using java util base64 instead;
package Utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class base64 {

    public static String encode(byte[] data){
        String encodedString = Base64.getUrlEncoder().withoutPadding().encodeToString(data);
        return encodedString;
    }

    public static String encode(String originalInput)
    {
        String encodedString = Base64.getUrlEncoder().withoutPadding().encodeToString(originalInput.getBytes());
        return encodedString;
    }


    public static byte[] decode(String originalInput)
    {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(originalInput);
        return decodedBytes;
    }
}
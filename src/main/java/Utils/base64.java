//using java util base64 instead;
package Utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class base64 {
    static String encode(String originalInput)
    {
        String encodedString = Base64.getEncoder().withoutPadding().encodeToString(originalInput.getBytes());
        return encodedString;
    }


    static String decode(String originalInput)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(originalInput);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
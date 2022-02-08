import Utils.base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class base64Test {
    @Test
    public void testEncode()
    {
        try {

            String originalInput = "App-Name";
            String encodedString = Base64.getEncoder().withoutPadding().encodeToString(originalInput.getBytes());



             System.out.println(encodedString );


            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
            String decodedString = new String(decodedBytes);

            System.out.println(decodedString);

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}

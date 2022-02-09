import Utils.base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;


public class base64Test {
    @Test
    public void testEncode()
    {
        try {

            String originalInput = "App-Name";
            String encodedString = base64.encode(originalInput);



             System.out.println(encodedString );


            byte[] decoded = base64.decode(encodedString);
            String decodedString = new String(decoded);

            System.out.println(decodedString);

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}

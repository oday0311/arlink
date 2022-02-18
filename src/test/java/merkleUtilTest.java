import Utils.base64;
import Utils.merkleUtils;
import org.junit.jupiter.api.Test;

import java.util.Base64;

public class merkleUtilTest
{


    @Test
    public void TestChunkData()
    {
        byte[]data = base64.decode("NzcyNg");

        byte[] result = merkleUtils.chunkData(data)[0].DataHash;
        String str = base64.encode(result);
        System.out.println("the result is = " + str);

        //55w6-CA_Um7muHLnJvBlUUKTjpa35cPLv1PCIPQs6M8
    }

}

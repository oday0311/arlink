import Utils.Hash;
import Utils.base64;
import org.junit.jupiter.api.Test;

public class HashTest {

    @Test
    public void TestDeepHashString() throws Exception
    {
        String str = "123";
        String strEncode = base64.encode(str);
        byte[] result =  Hash.DeepHashStr(strEncode);
        for (int i = 0;i<result.length;i++) {
            int unsigned = (int) (result[i] & 0x0000FF);
            System.out.print(unsigned + " ");
        }

        //shoud be 39 187......195
    }


}

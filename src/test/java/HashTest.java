import Utils.Hash;
import Utils.base64;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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



    @Test
    public void TestDeepHash() throws Exception{
        ArrayList<Object> datalist = new ArrayList<>();
        String data = base64.encode("123".getBytes());

        datalist.add(data);

        byte[] hashResult =  Hash.DeepHash(datalist);
        for (int i = 0;i<hashResult.length;i++) {
            int unsigned = (int) (hashResult[i] & 0x0000FF);
            System.out.print(unsigned + " ");
        }
        //should be 253 51 ....
        System.out.println("\n=======================\n");
        datalist.add(data);
        hashResult = Hash.DeepHash(datalist);
        for (int i = 0;i<hashResult.length;i++) {
            int unsigned = (int) (hashResult[i] & 0x0000FF);
            System.out.print(unsigned + " ");
        }
        //should be 231 11 237....


        String[][] data3 = new String[2][2];
        {
            String[] data31 = new String[2];
            data31[0] = base64.encode("APP".getBytes());
            data31[1] = base64.encode("1.0".getBytes());


            data3[0] = data31;
        }
        {
            String[] data32 = new String[2];
            data32[0] = base64.encode("Contract".getBytes());
            data32[1] = base64.encode("0x000".getBytes());

            data3[1] = data32;
        }


        System.out.println("\n=======================\n");

        datalist.add(data3);
        hashResult = Hash.DeepHash(datalist);
        for (int i = 0;i<hashResult.length;i++) {
            int unsigned = (int) (hashResult[i] & 0x0000FF);
            System.out.print(unsigned + " ");
        }
        //should be 251 155....
    }




}

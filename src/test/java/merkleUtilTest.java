import Utils.base64;
import Utils.merkleUtils;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
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


    @Test
    public void TestGenerateLeaves(){
        byte[]data = base64.decode("NzcyNg");

        byte[] result = merkleUtils.generateLeaves(merkleUtils.chunkData(data))[0].ID;
        String str = base64.encode(result);
        System.out.println("the result is = " + str);

        //z3rQGxyiqdQuOh2dxDst176oOKmW3S9MwQNTEh4DK1U

    }


    @Test
    public void TestGenerateChunks() throws  Exception{
        byte[] data = base64.decode("NzcyNg");
        String code = base64.encode(merkleUtils.GenerateChunks(data).DataRoot);
        System.out.println("the code is " + code);
        //z3rQGxyiqdQuOh2dxDst176oOKmW3S9MwQNTEh4DK1U

        byte[] fileData = readData();

        String fileChunkData = base64.encode(merkleUtils.GenerateChunks(fileData).DataRoot);
        System.out.println("the file chunk data "+ fileChunkData);


    }


    public byte[] readData() throws Exception
    {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        InputStream in = this.getClass().getResourceAsStream("/1mb.bin");
        byte[] buf = new byte[1024];
        int length = 0;
        //循环读取文件内容，输入流中将最多buf.length个字节的数据读入一个buf数组中,返回类型是读取到的字节数。
        //当文件读取到结尾时返回 -1,循环结束。
        while((length = in.read(buf)) != -1){
            bos.write(buf);
        }
        in.close();

        return bos.toByteArray();

    }
}

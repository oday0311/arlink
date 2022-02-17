package Utils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Hash {

    static public byte[] DeepHash(ArrayList<Object> data) throws Exception{

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] result = new byte[48];

        String listSize = new String(""+ data.size());
        String key = "list";
        os.write(key.getBytes());
        os.write(listSize.getBytes());

        byte[] tag = os.toByteArray();
        byte[] tagHash = sha512.getSHA384StrJava(tag);


        return DeepHashChunk(data,tagHash);
    }


    static public byte[] DeepHashStr(String str) throws Exception {
        byte[] result = new byte[48];

        byte[] data = base64.decode(str);

        String length = new String(""+data.length);
        String blob = "blob";
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        os.write(blob.getBytes());
        os.write(length.getBytes());
        byte[] tag = os.toByteArray();

        byte[] tagHash = sha512.getSHA384StrJava(tag);
        byte[] dataHash = sha512.getSHA384StrJava(data);

        os = new ByteArrayOutputStream();
        os.write(tagHash);
        os.write(dataHash);
        byte[] finalContent = os.toByteArray();

        result = sha512.getSHA384StrJava(finalContent);



        return result;
    }


    static public byte[] DeepHashChunk(ArrayList<Object> data, byte[] acc) throws  Exception{
        if (data.size()<1)
        {
            return  acc;
        }

        byte[] result = new byte[48];
        byte[] dHash = new byte[48];
        if (data.get(0) instanceof String){
            //string.
            String str = (String) data.get(0);
            dHash = DeepHashStr(str);
        } else{
            //todo
            
        }


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(acc);
        os.write(dHash);

        result = os.toByteArray();
        result = sha512.getSHA384StrJava(result);


        return result;
    }


}


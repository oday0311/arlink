package Utils;


public class owner {

    public static String OwnerToAddress( String owner) {
        byte[] by = base64.decode(owner);

        byte[] addrByte = sha256.getSHA256StrJava(by);
        return base64.encode(addrByte);
    }

    public static String OwnerToAddressWithLog( String owner) {
        byte[] by = base64.decode(owner);
        System.out.println("===============");
        for (int i = 0;i<by.length;i++) {
            int unsigned = (int) (by[i] & 0x0000FF);
            System.out.print(unsigned + " ");
        }
        System.out.println("===end===");


        byte[] addrByte = sha256.getSHA256StrJava(by);
        for (int i = 0;i<addrByte.length;i++) {
            int unsigned = (int) (addrByte[i] & 0x0000FF);
            System.out.print(unsigned + " ");
        }
        System.out.println("=======================");
        String addr = new String(addrByte);
        System.out.println(addr);
        return base64.encode(addrByte);
    }

}
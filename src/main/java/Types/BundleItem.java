package Types;

public class BundleItem {
    String SignatureType;
    String Signature;
    String Owner;  // utils.Base64Encode(wallet.PubKey.N.Bytes())
    String Target; //optional
    String Anchor; //optional
    String Data;
    Tag[]  Tags;
    String Id;
    Byte[] ItemBinary;
}

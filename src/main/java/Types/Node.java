package Types;

public class Node {
    public byte[] ID;
    public String Type;
    public byte[] DataHash;
    public int MinByteRange ;    // only leaf node
    public int MaxByteRange ;
    public int ByteRange    ;   // only branch node
    public Node LeftChild ;    // only branch node
    public Node RightChild;  // only branch node
}

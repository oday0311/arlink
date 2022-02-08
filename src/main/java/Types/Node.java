package Types;

public class Node {
    Byte[] ID;
    String Type;
    Byte[] DataHash;
    int MinByteRange ;    // only leaf node
    int MaxByteRange ;
    int ByteRange    ;   // only branch node
    Node LeftChild ;    // only branch node
    Node RightChild;  // only branch node
}

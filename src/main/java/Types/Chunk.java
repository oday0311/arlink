package Types;

public class Chunk {
    public Chunk()
    {

    }
    public Chunk(byte[] DataHash, int MinByteRange, int MaxByteRange)
    {
        this.DataHash = DataHash;
        this.MinByteRange = MinByteRange;
        this.MaxByteRange = MaxByteRange;
    }
    public byte[] DataHash;
    public int MinByteRange;
    public int MaxByteRange;
}

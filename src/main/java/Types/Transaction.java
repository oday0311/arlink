package Types;

import lombok.Data;

@Data
public class Transaction {
    public int format;
    public String ID;
    public String lastTx;
    public String owner;
    public Tag[] tags;
    public String target;
    public String quantity;
    public String data;
    public String dataSize;
    public String dataRoot;
    public String reward;
    public String signature;

    public Chunks chunks;


}

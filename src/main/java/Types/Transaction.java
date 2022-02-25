package Types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Transaction {
    public int format;
    public String id;
    public String last_tx;
    public String owner;
    public Tag[] tags = new Tag[0];
    public String target;
    public String quantity;
    public String data;
    public String data_size;
    public String data_root = "";
    public String reward;
    public String signature;

    @JsonIgnore
    public Chunks chunks;

    public Object[] data_tree = new Object[0];


}

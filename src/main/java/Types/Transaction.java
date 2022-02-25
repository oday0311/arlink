package Types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Transaction {
    public int format;
    public String id;
    public String last_tx;
    public String owner;
    public Tag[] tags;
    public String target;
    public String quantity;
    public String data;
    public String data_size;
    public String data_root;
    public String reward;
    public String signature;

    public Chunks chunks;

    @JsonIgnore
    public Object[] data_tree;


}

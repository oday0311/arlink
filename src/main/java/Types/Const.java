package Types;

import java.util.HashMap;

public class Const {
   static public String SuccessTxStatus = "Success";
   static public String PendingTxStatus = "Pending";

   static public long MAX_CHUNK_SIZE = 256 * 1024;
   static public long MIN_CHUNK_SIZE = 32 * 1024;
   static public int NOTE_SIZE      = 32;
   static public int HASH_SIZE      = 32;

    // number of bits in a big.Word

    //a ascii 8 bits;
    static public long WordBits = 8; //32 << (uint64(^big.Word(0)) >> 63);
    // number of bytes in a big.Word

   static public long WordBytes      = WordBits / 8;
   static public String BranchNodeType = "branch";
   static public String LeafNodeType   = "leaf";

    // Maximum amount of chunks we will upload in the body.
   static public int  MAX_CHUNKS_IN_BODY = 1;

    // We assume these errors are intermitment and we can try again after a delay:
    // - not_joined
    // - timeout
    // - data_root_not_found (we may have hit a node that just hasn't seen it yet)
    // - exceeds_disk_pool_size_limit
    // We also try again after any kind of unexpected network errors

    // Amount we will delay on receiving an error response but do want to continue.
   static public int ERROR_DELAY = 1000 * 40;


    // about bundle
   static public String BUNDLER_HOST           = "https://node1.bundlr.network";
   static public int MIN_BUNDLE_BINARY_SIZE = 1044;

   static public HashMap<String,String> FATAL_CHUNK_UPLOAD_ERRORS = new HashMap<>();
   static public HashMap<String,Object> Input = new HashMap<>();

   static public String e = "AQAB";
}

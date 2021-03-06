package Utils;

import Types.Tag;

import java.util.ArrayList;
import java.util.List;

public class Tags {
    public
    static List<Tag> TagsEncode(List<Tag> tags){
        List<Tag> result = new ArrayList<>();
        for (int i = 0;i<tags.size();i++)
        {
            Tag t = new Tag();
            t.Name =  base64.encode(tags.get(i).Name);
            t.Value = base64.encode(tags.get(i).Value);
            result.add(t);
        }
        return result;
    }


    public
    static List<Tag> TagsDecode(List<Tag> tags){
        List<Tag> result = new ArrayList<>();
        for (int i = 0;i<tags.size();i++)
        {
            Tag t = new Tag();
            t.Name =  new String(base64.decode(tags.get(i).Name));
            t.Value = new String(base64.decode(tags.get(i).Value));
            result.add(t);
        }
        return result;
    }

}

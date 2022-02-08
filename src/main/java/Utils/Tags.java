package Utils;

import Types.Tag;

import java.util.ArrayList;
import java.util.List;

public class Tags {
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


    static List<Tag> TagsDecode(List<Tag> tags){
        List<Tag> result = new ArrayList<>();
        for (int i = 0;i<tags.size();i++)
        {
            Tag t = new Tag();
            t.Name =  base64.decode(tags.get(i).Name);
            t.Value = base64.decode(tags.get(i).Value);
            result.add(t);
        }
        return result;
    }

}

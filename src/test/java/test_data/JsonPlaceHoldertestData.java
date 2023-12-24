package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHoldertestData {

    public static Map<String,Object> jsonPlaceHolderMapper(int userId,String title,Boolean comleted){

        Map<String,Object> map=new HashMap<>();
        map.put("userId", userId);
        map.put("title", title);
        map.put("completed",comleted);

        return map;
    }
}

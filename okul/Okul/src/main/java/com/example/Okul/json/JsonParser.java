package com.example.Okul.json;

import java.util.List;
import java.util.Map;

public class JsonParser {

    public static String parse(Map<String,Object> map){
        StringBuilder json = new StringBuilder("");
        json.append("{ ");
        int counter = 0;
        for(Map.Entry<String, Object> entry : map.entrySet()){
            json.append("\""+entry.getKey()+"\":\""+entry.getValue()+"\" ");
            counter++;
            if(counter!=map.size()){
                json.append(",");
            }
        }
        json.append(" }");
        return json.toString();
    }

    public static String parse(List<Map<String,Object>> list){
        StringBuilder json = new StringBuilder("");
        json.append("[ ");
        int counter = 0;
        for(Map<String,Object> map : list){
            json.append(parse(map));
            counter++;
            if(counter<map.size()-1) {
                json.append(" , ");
            }
        }
        json.append(" ]");
        return json.toString();
    }

}

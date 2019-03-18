package com.bytd.crm.roadinfo.core;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

/**
 * json串帮助类
 * 1.将对象实例转化为Json串
 * 2.将json串转化为对象实例
 * @author bytd
 */
public class JsonHelper {

    public static String objectToJson(Object entity){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(entity);
    }

    public static <T> List<T> jsonToObject(String json, Class<T> t){
        List<T> list = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
        for(JsonElement jsonElement : jsonArray){
            T t1 = gson.fromJson(jsonElement, t);
            list.add(t1);
        }
        return list;
    }
}

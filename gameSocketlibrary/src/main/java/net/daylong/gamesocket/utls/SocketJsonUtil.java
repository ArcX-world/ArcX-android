package net.daylong.gamesocket.utls;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class SocketJsonUtil {
    private static Gson sGson = new Gson();

    /**
     * 对象转json字符串
     * @param Object
     * @return
     */
    public static <T> String toJson(T Object){
        return sGson.toJson(Object);
    }
    /**
     * 对象转json字符串
     * @param Object
     * @return
     */
    public static <T> String toJson(T Object, String key){
        Map map = new HashMap();
        map.put(key,Object);
        return sGson.toJson(map);
    }

    /**
     * json字符串转实体对象
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T fromJsonToObject(String json, Class<T> cls){
        return sGson.fromJson(json,cls);
    }

    /**
     * json字符串转实体对象
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJsonToObject(String json, Type type){
        return sGson.fromJson(json,type);
    }

}

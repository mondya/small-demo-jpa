package com.xhh.smalldemojpa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * object 转 json
     *
     * @param object
     * @return
     */
    public static String objectToJson(Object object) {
        try {
            if (object instanceof String) {
                return (String) object;
            } else {
                return MAPPER.writeValueAsString(object);
            }
        } catch (Exception e) {
            log.error("object to string error: ", e);
        }
        
        return "";
    }


    /**
     * str转类<T></>
     * @param json
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T stringToObject(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("str to object error, str:{} , e : ", json, e);
        }
        
        return null;
    }


    /**
     * str 转 object List
     * @param str
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> List<T> stringToObjectList(String str, Class<T> clazz) {
        try {
            CollectionType collectionType = MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
            return MAPPER.readValue(str, collectionType);
        } catch (Exception e) {
            log.error("str to list object error, str@{}, e:", str, e);
        }
        
        return List.of();
    }
    
    
}

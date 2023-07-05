package com.xhh.smalldemojpa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.xhh.smalldemojpa.domain.user.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
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
            log.error("object cast to string error: ", e);
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
            log.error("str cast to object error, str:{} , e : ", json, e);
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
            log.error("str cast to list object error, str@{}, e:", str, e);
        }
        
        return List.of();
    }


    /**
     * json 转 JsonNode，用于获取嵌套json对象
     * @param json
     * @return
     */
    public static JsonNode jsonToNode(String json) {
        try {
            return MAPPER.readTree(json);
        } catch (Exception e) {
            log.error("json cast to JsonNode error, str@{}, e:", json, e);
        }
        
        return null;
    }

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User u1 = new User();
        u1.setId(1L);
        userList.add(u1);
        User u2 = new User();
        u2.setId(2L);
        userList.add(u2);
        System.out.println(objectToJson(userList));
    }
}

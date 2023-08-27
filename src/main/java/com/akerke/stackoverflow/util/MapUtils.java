package com.akerke.stackoverflow.util;

import com.akerke.stackoverflow.constants.TokenType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
@Slf4j
public class MapUtils {
    public static Map<String, Object> toClaims(Object obj , TokenType type) {
        Map<String, Object> map = new HashMap<>();
        Class<?> objClass = obj.getClass();

        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);

                String fieldName = field.getName();
                Object fieldValue = field.get(obj);

                map.put(fieldName, fieldValue);

                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                log.error("error:{}" , e.getMessage());
            }
        }

        map.put("token-type" , type.name());

        return map;
    }
}

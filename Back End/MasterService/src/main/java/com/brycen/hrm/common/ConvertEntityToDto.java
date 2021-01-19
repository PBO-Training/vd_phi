package com.brycen.hrm.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

public class ConvertEntityToDto {
    @SuppressWarnings("unchecked")
    public <T> List<T> mapList(List<Tuple> listTuple, Class<T> desiredClass)
            throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException {
        Map<String, Object> listColName = new HashMap<String, Object>();
        for (Field field : desiredClass.getDeclaredFields()) {
            if (field.getType().equals(List.class) || field.getType().equals(ArrayList.class)) {
                Type type = ((ParameterizedType) desiredClass.getDeclaredField(field.getName()).getGenericType()).getActualTypeArguments()[0];
                listColName.put(field.getName(), type);
            }
        }
        List<Map<String, Object>> jsons = new ArrayList<Map<String, Object>>();
        for (Tuple t : listTuple) {
            List<TupleElement<?>> cols = t.getElements();
            Map<String, Object> value = new HashMap<>();
            for (TupleElement<?> col : cols) {
                value.put(col.getAlias(), t.get(col.getAlias()));
            }
            jsons.add(value);
        }
        List<T> result = new ArrayList<>();
        Map<String, T> hashMapList = new HashMap<String, T>();
        if (jsons != null) {
            Gson gson = new Gson();
            for (Object object : jsons) {
                Map<String, Object> hashMap = (Map<String, Object>) object;
                // hashMap = (Map<String, Object>) object;
                List<T> listChildren = new ArrayList<T>();
                Map<String, Object> children = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                    if (entry.getKey().toString().contains("list")) {
                        if (entry.getValue() != null) {
                            List<Object> listObject = Arrays.asList(entry.getValue().toString().split("<@CDATA>"));
                            for (Object objectChildren : listObject) {
                                T map = gson.fromJson(objectChildren.toString(), (Class<T>) listColName.get(entry.getKey()));
                                listChildren.add(map);

                            }
                            children.put(entry.getKey(), listChildren);
                        }
                    }
                }
                // Map<String, Object> hashMapRemoveList = removeByKey(hashMap, "list");
                removeByKey(hashMap, "list");
                String json = gson.toJson(hashMap);
                T model = gson.fromJson(json, desiredClass);
                if (listChildren.size() > 0) {
                    for (Map.Entry<String, Object> entry : children.entrySet()) {
                        try {
                            String methodName = "set" + entry.getKey().toString().substring(0, 1).toUpperCase() + entry.getKey().toString().substring(1);
                            Method method = desiredClass.getMethod(methodName, List.class);
                            try {
                                method.invoke(model, entry.getValue());
                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IllegalArgumentException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } catch (NoSuchMethodException | SecurityException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                result.add(model);
            }
        }
        // for (Map.Entry<String, T> entry : hashMapList.entrySet()) {
        // T model = (T) entry.getValue();
        // result.add(model);
        // }
        return result;
    }

    public static <K, V> Map<K, V> removeByKey(Map<K, V> map, String key) {
        // Map<K, V> hashMap = new HashMap<K, V>();
        // hashMap = map;
        Iterator<Map.Entry<K, V>> itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<K, V> curr = itr.next();
            if (curr.getKey().toString().contains(key))
                itr.remove();
        }
        return map;
    }
}

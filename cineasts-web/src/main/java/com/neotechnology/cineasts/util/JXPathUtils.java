package com.neotechnology.cineasts.util;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.jxpath.JXPathContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JXPathUtils {

    public static JSONArray jxJSONArray(Object contextObject, String xpath) {
        return jxValue(contextObject, xpath, JSONArray.class);
    }
    
    public static JSONObject jxJSONObject(Object contextObject, String xpath) {
        return jxValue(contextObject, xpath, JSONObject.class);
    }
    
    public static Collection<JSONObject> jxJSONObjectCollection(Object contextObject, String xpath) {
        return jxValueCollection(contextObject, xpath, JSONObject.class);
    }
    
    public static String jxString(Object contextObject, String xpath) {
        JXPathContext context = JXPathContext.newContext(contextObject);
        context.setLenient(true);
        Object value = context.getValue(xpath);
        if (value==null) {
            return null;
        }
        else {
            return value.toString();
        }
    }
    
    public static <T> T jxValue(Object contextObject, String xpath, Class<T> resultClass) {
        JXPathContext context = JXPathContext.newContext(contextObject);
        context.setLenient(true);
        Object value = context.getValue(xpath);
        if (value==null) {
            return null;
        }
        if (resultClass.isAssignableFrom(value.getClass())) {
            return resultClass.cast(value);
        }
        else {
            throw new RuntimeException("Unexpected value type ");
        }
    }

    public static <T> Collection<T> jxValueCollection(Object contextObject, String xpath, Class<T> resultElementClass) {
        JXPathContext context = JXPathContext.newContext(contextObject);
        context.setLenient(true);
        Object value = context.getValue(xpath);
        if (value==null) {
            return null;
        }
        if (!(value instanceof Collection<?>)) {
            throw new RuntimeException("Unexpected value type ");            
        }
        Collection<?> valueCollection = (Collection<?>) value;
        Collection<T> result = new ArrayList<T>();
        for (Object element: valueCollection) {
            if (resultElementClass.isAssignableFrom(element.getClass())) {
                result.add(resultElementClass.cast(element));
            }
            else {
                throw new RuntimeException("Unexpected element type ");
            }            
        }
        return result;
    }
}

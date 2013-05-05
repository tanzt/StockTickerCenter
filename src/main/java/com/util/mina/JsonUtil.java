package com.util.mina ; 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
        
/**      
 * ����json�Ĺ����࣬����json����ת����java�����java����ת����json      
 *       
 * @author tanzetao      
 * @date 2013-5-2 ����10:47:13      
 * @version 1.0      
 */        
public class JsonUtil {         
        
    /**      
     * ��һ��JSON �����ַ���ʽ�еõ�һ��java����      
     *       
     * @param jsonString      
     * @param pojoCalss      
     * @return      
     */        
    public static Object  getObject4JsonString(String jsonString, Class pojoCalss) {         
        Object pojo;         
        JSONObject jsonObject = JSONObject.fromObject(jsonString);         
        pojo = JSONObject.toBean(jsonObject, pojoCalss);         
        return pojo;         
    }         
        
        
    /**      
     * ��json HASH���ʽ�л�ȡһ��map����map֧��Ƕ�׹���      
     *       
     * @param jsonString      
     * @return      
     */        
    public static Map getMap4Json(String jsonString) {         
        JSONObject jsonObject = JSONObject.fromObject(jsonString);         
        Iterator keyIter = jsonObject.keys();         
        String key;         
        Object value;         
        Map valueMap = new HashMap();         
        
        while (keyIter.hasNext()) {         
            key = (String) keyIter.next();         
            value = jsonObject.get(key);         
            valueMap.put(key, value);         
        }         
        
        return valueMap;         
    }         
        
        
    /**      
     * ��json�����еõ���Ӧjava����      
     *       
     * @param jsonString      
     * @return      
     */        
    public static Object[] getObjectArray4Json(String jsonString) {         
        JSONArray jsonArray = JSONArray.fromObject(jsonString);         
        return jsonArray.toArray();         
        
    }         
        
        
    /**      
     * ��json���󼯺ϱ��ʽ�еõ�һ��java�����б�      
     *       
     * @param jsonString      
     * @param pojoClass      
     * @return      
     */        
    public static List getList4Json(String jsonString, Class pojoClass) {         
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);         
        JSONObject jsonObject;         
        Object pojoValue;         
        
        List list = new ArrayList();         
        for (int i = 0; i < jsonArray.size(); i++) {         
        
            jsonObject = jsonArray.getJSONObject(i);         
            pojoValue = JSONObject.toBean(jsonObject, pojoClass);         
            list.add(pojoValue);         
        
        }         
        return list;         
        
    }         
        
        
    /**      
     * ��json�����н�����java�ַ�������      
     *       
     * @param jsonString      
     * @return      
     */        
    public static String[] getStringArray4Json(String jsonString) {         
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);         
        String[] stringArray = new String[jsonArray.size()];         
        for (int i = 0; i < jsonArray.size(); i++) {         
            stringArray[i] = jsonArray.getString(i);         
        
        }         
        
        return stringArray;         
    }         
        
        
    /**      
     * ��json�����н�����javaLong�Ͷ�������      
     *       
     * @param jsonString      
     * @return      
     */        
    public static Long[] getLongArray4Json(String jsonString) {         
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);         
        Long[] longArray = new Long[jsonArray.size()];         
        for (int i = 0; i < jsonArray.size(); i++) {         
            longArray[i] = jsonArray.getLong(i);         
        
        }         
        return longArray;         
    }         
        
        
    /**      
     * ��json�����н�����java Integer�Ͷ�������      
     *       
     * @param jsonString      
     * @return      
     */        
    public static Integer[] getIntegerArray4Json(String jsonString) {         
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);         
        Integer[] integerArray = new Integer[jsonArray.size()];         
        for (int i = 0; i < jsonArray.size(); i++) {         
            integerArray[i] = jsonArray.getInt(i);         
        
        }         
        return integerArray;         
    }         
        
                
        
    /**      
     * ��json�����н�����java Integer�Ͷ�������      
     *       
     * @param jsonString      
     * @return      
     */        
    public static Double[] getDoubleArray4Json(String jsonString) {         
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);         
        Double[] doubleArray = new Double[jsonArray.size()];         
        for (int i = 0; i < jsonArray.size(); i++) {         
            doubleArray[i] = jsonArray.getDouble(i);         
        
        }         
        return doubleArray;         
    }         
        
        
    /**      
     * ��java����ת����json�ַ���      
     *       
     * @param javaObj      
     * @return      
     */        
    public static String getJsonString4JavaPOJO(Object javaObj) {         
        
        JSONObject json;         
        json = JSONObject.fromObject(javaObj);         
        return json.toString();         
        
    }         
        
    /**      
     * ��java����ת����json�ַ���,���趨���ڸ�ʽ      
     *       
     * @param javaObj      
     * @param dataFormat      
     * @return      
     */        
    public static String getJsonString4JavaPOJO(Object javaObj,         
            String dataFormat) {         
        
        JSONObject json;         
        JsonConfig jsonConfig = configJson(dataFormat);         
        json = JSONObject.fromObject(javaObj, jsonConfig);         
        return json.toString();         
        
    }         
        
    /**      
     * JSON ʱ���������      
     *       
     * @param datePattern      
     * @return      
     */        
    public static JsonConfig configJson(String datePattern) {         
        JsonConfig jsonConfig = new JsonConfig();         
        jsonConfig.setExcludes(new String[] { "" });         
        jsonConfig.setIgnoreDefaultExcludes(false);         
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);         
        jsonConfig.registerJsonValueProcessor(Date.class,         
                new JsonDateValueProcessor(datePattern));         
        
        return jsonConfig;         
    }         
        
        
    /**      
     * ��ȥ�������ɵ��ֶΣ��ر��ʺ�ȥ�������Ķ���+ʱ��ת��      
     * @param excludes ��ȥ�������ɵ��ֶ�      
     * @param datePattern      
     * @return      
     */        
    public static JsonConfig configJson(String[] excludes, String datePattern) {         
        JsonConfig jsonConfig = new JsonConfig();         
        jsonConfig.setExcludes(excludes);         
        jsonConfig.setIgnoreDefaultExcludes(true);         
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);         
        jsonConfig.registerJsonValueProcessor(Date.class,         
                new JsonDateValueProcessor(datePattern));         
        
        return jsonConfig;         
    }         
        
}    

class JsonDateValueProcessor implements JsonValueProcessor {            
    
    private String format = "yyyy-MM-dd HH:mm:ss";            
           
    public JsonDateValueProcessor() {            
           
    }            
           
    public JsonDateValueProcessor(String format) {            
        this.format = format;            
    }            
           
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {            
        String[] obj = {};            
        if (value instanceof Date[]) {            
            SimpleDateFormat sf = new SimpleDateFormat(format);            
            Date[] dates = (Date[]) value;            
            obj = new String[dates.length];            
            for (int i = 0; i < dates.length; i++) {            
                obj[i] = sf.format(dates[i]);            
            }            
        }            
        return obj;            
    }            
           
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {            
        if (value instanceof Date) {            
            String str = new SimpleDateFormat(format).format((Date) value);            
            return str;            
        }            
        return value == null ? null : value.toString();            
    }            
           
    public String getFormat() {            
        return format;            
    }            
           
    public void setFormat(String format) {            
        this.format = format;            
    } 
}
     

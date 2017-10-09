package com.lexinsmart.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TypeChange {
	public TypeChange() {
	}

	// change the string type to the int type
	public static int stringToInt(String intstr) {
		
		if (intstr==null || intstr.equals("")) {
			return 0;
		}
		Integer integer;
		integer = Integer.valueOf(intstr);
		return integer.intValue();
	}

	// change int type to the string type
	public static String intToString(int value) {

		Integer integer = new Integer(value);
		return integer.toString();
	}

	// change the string type to the float type
	public static float stringToFloat(String floatstr) {
		if (floatstr == null || floatstr.equals("")) {
			return 0.0f;
		}
		Float floatee;
		floatee = Float.valueOf(floatstr);
		return floatee.floatValue();
	}

	// change the float type to the string type
	public static String floatToString(float value) {
		Float floatee = new Float(value);
		return floatee.toString();
	}

	// change the string type to the sqlDate type
	public static java.sql.Date stringToDate(String dateStr) {
		return java.sql.Date.valueOf(dateStr);
	}

	// change the sqlDate type to the string type
	public static String dateToString(java.sql.Date datee) {
		return datee.toString();
	}
	
	/** 
	 * 使用Introspector进行转换 
	 */
	public static Map<String, Object> objectToMap(Object obj) throws Exception {    
        if(obj == null)  
            return null;      
  
        Map<String, Object> map = new HashMap<String, Object>();   
  
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
        for (PropertyDescriptor property : propertyDescriptors) {    
            String key = property.getName();    
            if (key.compareToIgnoreCase("class") == 0) {   
                continue;  
            }  
            Method getter = property.getReadMethod();  
            Object value = getter!=null ? getter.invoke(obj) : null;  
            map.put(key, value);  
        }    
  
        return map;  
    }    
	
	 public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
	        if (map == null)   
	            return null;    
	  
	        Object obj = beanClass.newInstance();  
	  
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
	        for (PropertyDescriptor property : propertyDescriptors) {  
	            Method setter = property.getWriteMethod();    
	            if (setter != null) {  
	                setter.invoke(obj, map.get(property.getName()));   
	            }  
	        }  
	  
	        return obj;  
	    }    
		//使用String的split 方法   
	    public static String[] convertStrToArray(String str){   
	        String[] strArray = null;   
	        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
	        return strArray;
	    }   
}
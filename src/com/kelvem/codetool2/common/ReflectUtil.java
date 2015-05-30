/**
 * 
 */
package com.kelvem.codetool2.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author kelvem
 * 
 */
public class ReflectUtil {

	private ReflectUtil() {

	}

	public static void main(String[] args) {
	}
	
	/**
	 * 取Bean的属性和值对应关系的MAP
	 * 
	 * @param bean
	 * @return Map
	 */
	public static Map<String, Object> getFieldValueMap(Object obj) {
		
		Class<? extends Object> cls = obj.getClass();
		Map<String, Object> valueMap = new HashMap<String, Object>();

		Field[] fields = cls.getFields();
		for (Field field : fields) {
			try {
				String fieldName = field.getName();
				
				Object result = null;
				if ( checkGetMethod(obj, fieldName)){
					result = getMethodGetValue(obj, fieldName);
				} else {
					result = getFieldValue(obj, fieldName);
				}
				valueMap.put(fieldName, result);
				
			} catch (Exception e) {
				continue;
			}
		}
		return valueMap;
	}
	public static Set<String> getFieldHashSet(Class<?> clazz) {
		
		Field[] fields = clazz.getFields();
		Set<String> result = new HashSet<String>();
		
		for (Field field : fields) {
			result.add(field.getName());
		}

		return result;
	}
	
	public static boolean setParamValue(Object obj, String fieldName, Object val) {
		
		if ( !ReflectUtil.checkField(obj, fieldName) ){
			System.err.println("无法从类" + obj.getClass() + "中获取属性值 " + fieldName);
			return false;
		}
			
		if ( checkGetMethod(obj, fieldName)){
			return setMethodSetValue(obj, fieldName, val);
		} else {
			return setFieldValue(obj, fieldName, val);
		}
	}
	
	public static Object getParamValue(Object obj, String fieldName) {
		
		if ( !ReflectUtil.checkField(obj, fieldName) ){
			System.err.println("无法从类" + obj.getClass() + "中获取属性值 " + fieldName);
			return null;
		}
			
		Object result = null;
		if ( checkGetMethod(obj, fieldName)){
			result = getMethodGetValue(obj, fieldName);
		} else {
			result = getFieldValue(obj, fieldName);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	private static List<Object> getParamValues(Object obj, String fieldName){
		
		List<Object> result = new ArrayList<Object>();
		
		int classType = ReflectUtil.getClassType(obj);
		if ( classType == 1 ){
			for (Object buf : (AbstractCollection)obj) {
				Object bufObj = ReflectUtil.getParamValue(buf, fieldName);
				result.add(bufObj);
			}
		} else if (classType == 2) {
			System.err.println("不支持在Map：" + obj.getClass() + "中获取参数" + fieldName);
//			Object buf = ((AbstractMap)obj).get(fieldName);
//			Object bufObj = ReflectUtil.getParamValue(buf, fieldName);
//			result.add(bufObj);
		} else {
			Object bufObj = ReflectUtil.getParamValue(obj, fieldName);
			result.add(bufObj);
		}
		return result;
	}

	private static Object getFieldValue(Object obj, String fieldName) {

		try {
			if ( !checkField(obj, fieldName)){
				System.err.println("类" + obj.getClass() + "中不包含成员变量 " + fieldName);
				return null;
			}
			Class<? extends Object> cls = obj.getClass();
			Field field = cls.getField(fieldName);
//			System.out.println(field.getName());
			
			Object paramValue = field.get(obj);
//			System.out.println(paramValue);
			
			if (paramValue == null){
				return null;
			} else if (field.getGenericType().equals(String.class)){
				return paramValue.toString();
			} else {
				return paramValue;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Object getMethodGetValue(Object obj, String fieldName) {

		try {
			Class<? extends Object> cls = obj.getClass();
			
			String methodGetName = StringUtil.fieldGetName(fieldName);

			Method method = cls.getMethod(methodGetName, new Class[] {});
			System.out.println(method.getName());
			Object paramValue = method.invoke(obj);
			
			System.out.println(paramValue);
			
			return paramValue;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean setFieldValue(Object obj, String fieldName, Object val) {

		try {
			if ( !checkField(obj, fieldName)){
				System.err.println("类" + obj.getClass() + "中不包含成员变量 " + fieldName);
				return false;
			}
			Class<? extends Object> cls = obj.getClass();
			Field field = cls.getField(fieldName);
//			System.out.println(field.getName());
			
			if (field.getType().equals(Integer.class)
					|| field.getType().equals(int.class)) {
				field.setInt(obj, (Integer)val);
			} else if (field.getType().equals(Double.class)
					|| field.getType().equals(double.class)) {
				field.setDouble(obj, (Double)val);
			} else if (field.getType().equals(Boolean.class)
					|| field.getType().equals(boolean.class)) {
				field.setBoolean(obj, (Boolean)val);
			} else {
				field.set(obj, val);
			}
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean setMethodSetValue(Object obj, String fieldName, Object val) {

		try {
			Class<? extends Object> cls = obj.getClass();
			
			String methodSetName = StringUtil.fieldSetName(fieldName);

			Method method = cls.getMethod(methodSetName, new Class[] {val.getClass()});
//			System.out.println(method.getName());
			method.invoke(obj, val);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断是否存在某属性的 get方法 　　
	 * @param methods 　　
	 * @param fieldName 　　
	 * @return
	 * boolean 　　
	 */
	public static boolean checkGetMethod(Object obj, String fieldName) {
		
		Class<? extends Object> clazz = obj.getClass();

		return checkGetMethod(clazz, fieldName);
	}
	public static boolean checkGetMethod(Class<?> clazz, String fieldName) {
		
		Method[] methods = clazz.getDeclaredMethods();
		
		String fieldGetMetethod = StringUtil.fieldGetName(fieldName);
		for (Method method : methods) {
			if (fieldGetMetethod.equals(method.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkSetMethod(Object obj, String fieldName) {
		
		Class<? extends Object> clazz = obj.getClass();

		return checkSetMethod(clazz, fieldName);
	}
	public static boolean checkSetMethod(Class<?> clazz, String fieldName) {
		
		Method[] methods = clazz.getDeclaredMethods();
		
		String fieldSetMetethod = StringUtil.fieldSetName(fieldName);
		for (Method method : methods) {
			if (fieldSetMetethod.equals(method.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否存在某属性 　　
	 * @param methods 　　
	 * @param fieldName 　　
	 * @return
	 * boolean 　　
	 */
	public static boolean checkField(Object obj, String fieldName) {
		
		Class<? extends Object> clazz = obj.getClass();
		
		return checkField(clazz, fieldName);
	}
	public static boolean checkField(Class<?> clazz, String fieldName) {
		
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			if (fieldName.equals(field.getName())) {
				return true;
			}
		}
		return false;
	}

	public static int getClassType(Object obj){
		
		Class<?> collection = java.util.AbstractCollection.class;
		Class<?> map = java.util.AbstractMap.class;
		
		if ( collection.isInstance(obj) ){
			return 1;
		} else if (map.isInstance(obj)){
			return 2;
		}else {
			return 0;
		}

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Object> getValues(Object obj, String fieldName){

		String params[] = fieldName.split("\\.");
		
		List<Object> listSrc = new ArrayList<Object>();
		listSrc.add(obj);
		for (int i = 0; i < params.length; i++) {
			List<Object> bufList = new ArrayList<Object>();

			List<Object> listObj = ReflectUtil.getParamValues(listSrc, params[i]);

			for ( Object buf : listObj){
				int classType = ReflectUtil.getClassType(buf);
				if ( classType == 1 ){
					bufList.addAll((AbstractCollection)buf);
				} else if (classType == 2) {
					// ###
					bufList.add(buf);
				} else {
					bufList.add(buf);
				}
			
			}
			
			listSrc.clear();
			listSrc.addAll(bufList);			
		}
		
		return listSrc;
	}
	
}

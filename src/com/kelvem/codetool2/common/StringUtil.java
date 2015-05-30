/**
 * 
 */
package com.kelvem.codetool2.common;

/**
 * @author kelvem
 *
 */
public class StringUtil {

	private StringUtil(){
		// do nothing
	}

	/**
	 * 拼接某属性的 get方法
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String fieldGetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}

	/**
	 * 拼接在某属性的 set方法
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String fieldSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}
	
	/**
	 * <p>Aaa -> aaa</p>
	 * 
	 * @param name
	 * @return String
	 * @see
	 */
	public static String toLowerFirstChar(String str) {
		if ( str == null || str.isEmpty()){
			return str;
		} 
		
		if ( str.length() > 1 ){
			str = str.substring(0, 1).toLowerCase() + str.substring(1);
		} else {
			str = str.toLowerCase();
		}
		
		return str;
	}
	
	/**
	 * <p>aaa -> Aaa</p>
	 * 
	 * @param name
	 * @return String
	 * @see
	 */
	public static String toUpperFirstChar(String str) {
		if ( str == null || str.isEmpty()){
			return str;
		} 
		
		if ( str.length() > 1 ){
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
		} else {
			str = str.toUpperCase();
		}
		
		return str;
	}

	/**
	 * <p>AAA_AAA -> AaaAaa</p>
	 * 
	 * @param name
	 * @return String
	 * @see
	 */
	public static String AAA_AAAToAaaAaa(String name) { 
		
		if ( name == null || name.isEmpty()){
			return name;
		} 
		 
		if ( name.toLowerCase().startsWith("b_") || name.toLowerCase().startsWith("c_") || name.toLowerCase().startsWith("t_")){
			name = name.substring(2); 
		}
		
		name = name.toLowerCase();
		
		StringBuffer buf = new StringBuffer("");
		
		String[] fields = name.replace('.', '_').split("_");
		
		for (String field : fields) {
			buf.append(toUpperFirstChar(field));
		}

		return buf.toString(); 
	}
	
	/**
	 * <p>AAA_AAA -> aaaAaa</p>
	 * 
	 * @param name
	 * @return String
	 * @see
	 */
	public static String AAA_AAAToaaaAaa(String name) { 
		
		String buf = AAA_AAAToAaaAaa(name);

		return toLowerFirstChar(buf); 
	}
	
	// columnModel -> COLUMN_MODEL
//	public static String fieldToColumnName(String name) { 
//		
//		if ( name == null || name.isEmpty()){
//			return name;
//		}
//		
//		StringBuffer buf = new StringBuffer(name.replace('.', '_')); 
//		for (int i = 1; i < buf.length() - 1; i++) { 
//			if ('_' != buf.charAt(i - 1) && Character.isUpperCase(buf.charAt(i)) && !Character.isUpperCase(buf.charAt(i + 1))) { 
//				buf.insert(i++, '_'); 
//			} 
//		} 
//		return buf.toString().toUpperCase(); 
//	}
}

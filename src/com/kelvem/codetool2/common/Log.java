/**
 * 
 */
package com.kelvem.codetool2.common;

/**
 * @author kelvem
 *
 */
public class Log {

	public static void info(String msg){
		System.out.println(msg);
	}
	
	public static void debug(String msg){
		System.out.println(msg);
		
	}
	
	public static void error(String msg){
		System.err.println(msg);
	}
}

package com.kelvem.codetool2.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {

	private FileUtil() {

	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static String readFile(String fileName) {

		StringBuilder sb = new StringBuilder();

		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				sb.append(tempString + "\r\n");
				line++;
			}
			Log.debug("读取文件：" + fileName + " : 行" + line);

			reader.close();
			return sb.toString();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static boolean writeFile(String fileName, String content) {
		try {
//			FileWriter fw = new FileWriter(fileName);
//			String s = context;
//			fw.write(s, 0, s.length());
//			fw.flush();
			File file = new File(fileName);
			
			if ( !file.exists() ){
				
				boolean ret1 = file.getParentFile().mkdirs();
				boolean ret2 = file.createNewFile();
				
				Log.debug("创建文件：" + fileName + " : " + ret1 + " :" + ret2);
			}
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName));
			// ###
//			content = content.replace("[", "#{");
//			content = content.replace("]", "}");
			osw.write(content, 0, content.length());
			osw.flush();
//			PrintWriter pw = new PrintWriter(new OutputStreamWriter(
//					new FileOutputStream("hello3.txt")), true);
//			pw.println(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

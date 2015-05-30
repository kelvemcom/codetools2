package com.kelvem.codetool2.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.kelvem.codetool2.common.XFile.XFilenameFiter;

/**
 * xFile.java
 * 
 * @author
 * @version V1.00 2006/09/21
 */
public final class XFile {

	/**
	 * setCanWrite
	 * 
	 * @return
	 */
	public static boolean setWriteEnalbe(File file) {
		boolean flag = false;
		try {
			String os = System.getProperty("os.name").toLowerCase();

			if (os.indexOf("windows") != -1) {
				String cmd = "attrib " + "\"" + file.getAbsolutePath() + "\"" + " -R";
				Runtime.getRuntime().exec(cmd);
				flag = true;
			} else if (false) {
				flag = false;
			} else {
				flag = false;
			}
		} catch (IOException ex) {
			flag = false;
		}

		return flag;
	}

	public static String getExtension(File file) {
		String extension = "";
		if (file.isDirectory()) {
			return extension;
		}
		
		String fileName = file.getName();
		int index = fileName.lastIndexOf(".");
		if (index != -1 ) {
			extension = fileName.substring(index + 1, fileName.length());
		} else {
			extension = "";
		}
		
		return extension;
	}
	/**
	 * copy
	 * 
	 * @param srcFile
	 * @param dstFile
	 * @return
	 */
	public static boolean copy(File srcFile, File dstFile) {
		boolean flag = false;

		try {
			if (srcFile == null || dstFile == null) {
				return false;
			}

			if (!dstFile.exists()) {
				if (srcFile.isFile()) {
					dstFile.createNewFile();
				} else if (srcFile.isDirectory()) {
					dstFile.mkdir();
				}
			}

			if (srcFile.isFile() && dstFile.isFile()) {
				flag = fileCopy(srcFile, dstFile);
			} else if (srcFile.isDirectory() && dstFile.isDirectory()) {
				flag = directoryCopy(srcFile, dstFile);
			} else {
				flag = false;
			}
		} catch (IOException ex) {
			if (dstFile != null && dstFile.exists()) {
				dstFile.delete();
			}
			// �t�@�C���R�s�[�Ɏ��s���܂����B
			return false;
		}
		return flag;
	}

	/**
	 * copy
	 * 
	 * @param srcFile
	 * @param dstFile
	 * @param srcEncoding
	 * @param dstEncoding
	 * @return
	 */
	public static boolean copy(File srcFile, File dstFile, String srcEncoding, String dstEncoding) {
		boolean flag = false;

		try {
			if (srcFile == null || dstFile == null) {
				return false;
			}

			if (!dstFile.exists()) {
				if (srcFile.isFile()) {
					dstFile.createNewFile();
				} else if (srcFile.isDirectory()) {
					dstFile.mkdir();
				}
			}

			if (srcFile.isFile() && dstFile.isFile()) {
				flag = fileCopy(srcFile, dstFile, srcEncoding, dstEncoding);
			} else if (srcFile.isDirectory() && dstFile.isDirectory()) {
				flag = directoryCopy(srcFile, dstFile, srcEncoding, dstEncoding);
			} else {
				flag = false;
			}
		} catch (IOException ex) {
			if (dstFile != null && dstFile.exists()) {
				dstFile.delete();
			}
			// �t�@�C���R�s�[�Ɏ��s���܂����B
			return false;
		}
		return flag;
	}

	/**
	 * directoryCopyOnly
	 * 
	 * @param srcFile
	 * @param dstFile
	 * @param depth
	 * @return
	 */
	public static boolean directoryCopyOnly(File srcFile, File dstFile, int depth) {
		File tmpDstFile = dstFile;

		try {
			if (srcFile == null || dstFile == null) {
				return false;
			}
			if (depth <= 0) {
				return true;
			}
			if (!dstFile.exists()) {
				dstFile.mkdir();
			}
			if (!srcFile.isDirectory() || !dstFile.isDirectory()) {
				return false;
			}

			File[] fileList = srcFile.listFiles();
			for (File tmpSrcFile : fileList) {
				if (tmpSrcFile.isDirectory()) {
					tmpDstFile = new File(dstFile.getAbsolutePath() + "/" + tmpSrcFile.getName());
					// System.out.println(tmpDstFile.getAbsolutePath());

					if (!tmpDstFile.exists()) {
						tmpDstFile.mkdir();
					} else if (tmpDstFile.exists() && tmpDstFile.isFile()) {
						return false;
					} else if (tmpDstFile.exists() && tmpDstFile.isDirectory()) {
					}

					if (!directoryCopyOnly(tmpSrcFile, tmpDstFile, depth - 1)) {
						return false;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * directoryCopyOnly
	 * 
	 * @param srcFile
	 * @param dstFile
	 * @return
	 */
	public static boolean directoryCopyOnly(File srcFile, File dstFile) {

		return directoryCopyOnly(srcFile, dstFile, Integer.MAX_VALUE);
	}

	/**
	 * deleteAll
	 * 
	 * @param srcFile
	 * @return
	 */
	public static boolean deleteAll(File srcFile) {
		try {
			if (srcFile == null || !srcFile.exists()) {
				return false;
			}

			if (srcFile.exists() && srcFile.isDirectory()) {
				File[] fileList = srcFile.listFiles();
				for (File tmpSrcFile : fileList) {
					if (!deleteAll(tmpSrcFile)) {
						return false;
					}
				}
			}
			if (!srcFile.delete()) {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * deleteAllOnlyFile
	 * 
	 * @param srcFile
	 * @return
	 */
	public static boolean deleteAllOnlyFile(File srcFile) {
		try {
			if (srcFile == null || !srcFile.exists()) {
				return false;
			}

			if (!srcFile.exists()) {
				return false;
			}

			if (srcFile.isDirectory()) {
				File[] fileList = srcFile.listFiles();
				for (File tmpSrcFile : fileList) {
					if (!deleteAllOnlyFile(tmpSrcFile)) {
						return false;
					}
				}
			} else if (srcFile.isFile()) {
				if (!srcFile.delete()) {
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * fileCopy
	 * 
	 * @param srcPath
	 * @param destPath
	 * @return
	 */
	private static boolean fileCopy(File srcFile, File dstFile) {
		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			if (dstFile == null || srcFile == null) {
				return false;
			}
			if (!dstFile.exists()) {
				dstFile.createNewFile();
			}
			if (!srcFile.isFile() || !dstFile.isFile()) {
				return false;
			}
			if (!srcFile.exists() || !dstFile.canWrite()) {
				return false;
			}

			// IO
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(dstFile);

			// 
			byte[] bytes = new byte[4 * 1024];
			int size;
			while ((size = in.read(bytes)) != -1) {
				out.write(bytes, 0, size);
			}

			return true;
		} catch (IOException ex) {
			if (dstFile != null && dstFile.exists()) {
				dstFile.delete();
			}
			// 
			return false;
		} finally {
			// 
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				// �t�@�C���R�s�[�Ɏ��s���܂����B
				return false;
			}
		}
	}

	/**
	 * fileCopy
	 * 
	 * @param srcPath
	 * @param destPath
	 * @param srcEncoding
	 * @param dstEncoding
	 * @return
	 */
	private static boolean fileCopy(File srcFile, File dstFile, String srcEncoding, String dstEncoding) {
		BufferedReader in = null;
		BufferedWriter out = null;

		try {
			if (srcFile == null || dstFile == null) {
				return false;
			}
			if (dstFile == null || srcFile == null || srcEncoding == null || dstEncoding == null) {
				return false;
			}
			if (srcEncoding.trim().equals("") || dstEncoding.trim().equals("")) {
				return false;
			}

			if (!dstFile.exists()) {
				dstFile.createNewFile();
			}
			if (!srcFile.isFile() || !dstFile.isFile()) {
				return false;
			}
			if (!srcFile.exists() || !dstFile.canWrite()) {
				return false;
			}

			in = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), srcEncoding));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dstFile), dstEncoding));

			char[] chars = new char[4 * 1024];
			int size = 0;
			while ((size = in.read(chars)) > 0) {
				out.write(chars, 0, size);
			}

			return true;
		} catch (IOException ex) {
			if (dstFile != null && dstFile.exists()) {
				dstFile.delete();
			}
			return false;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				return false;
			}
		}
	}

	/**
	 * directoryCopy
	 * 
	 * @param srcFile
	 * @param dstFile
	 * @return
	 */
	private static boolean directoryCopy(File srcFile, File dstFile) {
		File tmpDstFile = null;

		try {
			if (srcFile == null || dstFile == null) {
				return false;
			}
			if (!dstFile.exists()) {
				dstFile.mkdir();
			}
			if (!srcFile.isDirectory() || !dstFile.isDirectory()) {
				return false;
			}

			File[] fileList = srcFile.listFiles();
			for (File tmpSrcFile : fileList) {
				tmpDstFile = new File(dstFile.getAbsolutePath() + "/" + tmpSrcFile.getName());

				if (!copy(tmpSrcFile, tmpDstFile)) {
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * directoryCopy
	 * 
	 * @param srcFile
	 * @param dstFile
	 * @param srcEncoding
	 * @param dstEncoding
	 * @return
	 */
	private static boolean directoryCopy(File srcFile, File dstFile, String srcEncoding, String dstEncoding) {
		File tmpDstFile = null;

		try {
			if (srcFile == null || dstFile == null) {
				return false;
			}
			if (!dstFile.exists()) {
				dstFile.mkdir();
			}
			if (!srcFile.isDirectory() || !dstFile.isDirectory()) {
				return false;
			}

			File[] fileList = srcFile.listFiles();
			for (File tmpSrcFile : fileList) {
				tmpDstFile = new File(dstFile.getAbsolutePath() + "/" + tmpSrcFile.getName());

				if (!copy(tmpSrcFile, tmpDstFile, srcEncoding, dstEncoding)) {
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * searchSameFilesByName
	 * 
	 * @param dir
	 * @param map
	 * @return
	 */
	public static boolean searchSameFiles(File dir, Map<String, List<File>> map) {
		if (dir == null || !dir.isDirectory()) {
			return false;
		}

		File[] fileList = dir.listFiles();
		if (fileList == null) {
			return false;
		}

		for (File file : fileList) {
			if (file.isDirectory()) {
				if (!searchSameFiles(file, map)) {
					return false;
				}
			} else if (file.isFile()) {
				String key = file.getName() + "[" + file.length() + "byte]";
				if (!map.containsKey(key)) {
					map.put(key, new ArrayList<File>(0));
				}
				List<File> list = map.get(key);
				list.add(file);
			}
		}

		return true;
	}

	/**
	 * searchSameFilesByName
	 * 
	 * @param dir
	 * @param map
	 * @return
	 */
	public static boolean searchSameFilesByName(File dir, Map<String, List<File>> map) {
		if (dir == null || !dir.isDirectory()) {
			return false;
		}

		File[] fileList = dir.listFiles();
		if (fileList == null) {
			return false;
		}

		for (File file : fileList) {
			if (file.isDirectory()) {
				if (!searchSameFilesByName(file, map)) {
					return false;
				}
			} else if (file.isFile()) {
				String key = file.getName();
				if (!map.containsKey(key)) {
					map.put(key, new ArrayList<File>(0));
				}
				List<File> list = map.get(key);
				list.add(file);
			}
		}

		return true;
	}

	/**
	 * searchSameFilesByName
	 * 
	 * @param dir
	 * @param map
	 * @return
	 */
	public static boolean searchSameFilesBySize(File dir, Map<String, List<File>> map) {
		if (dir == null || !dir.isDirectory()) {
			return false;
		}

		File[] fileList = dir.listFiles();
		if (fileList == null) {
			return false;
		}

		for (File file : fileList) {
			if (file.isDirectory()) {
				if (!searchSameFilesBySize(file, map)) {
					return false;
				}
			} else if (file.isFile()) {
				String key = "size " + file.length() + " byte";
				if (!map.containsKey(key)) {
					map.put(key, new ArrayList<File>(0));
				}
				List<File> list = map.get(key);
				list.add(file);
			}
		}

		return true;
	}

	/**
	 * searchFile
	 * 
	 * @param file
	 * @param filter
	 * @return
	 */
	public static List<Object> searchFiles(File file, FilenameFilter filter) {
		List<Object> result = new ArrayList<Object>(0);

		if (file == null || !file.exists()) {
			return result;
		}

		if (file.isDirectory()) {
			File[] fileList = null;
			if (filter == null) {
				fileList = file.listFiles();
			} else {
				fileList = file.listFiles(filter);
			}

			if (file == null) {
				return result;
			}

			for (File tmpFile : fileList) {
				if (tmpFile.isDirectory()) {
					result.add(searchFiles(tmpFile, filter));
				} else if (tmpFile.isFile()) {
					result.add(tmpFile);
				}
			}
		} else {
			if (filter == null) {
				result.add(file);
			} else if (filter.accept(file.getParentFile(), file.getName())) {
				result.add(file);
			} else {
			}
		}
		return result;
	}

	public static List<Object> searchFiles(File file) {

		return searchFiles(file, null);
	}

	/**
	 * searchFile
	 * 
	 * @param file
	 * @param filter
	 * @return
	 */
	public static List<File> searchAllFiles(File file, FilenameFilter filter) {
		List<File> result = new ArrayList<File>(0);
		
		if (file == null || !file.exists()) {
			return result;
		}
		
		if (file.isDirectory()) {
			File[] fileList = null;
			if (filter == null) {
				fileList = file.listFiles();
			} else {
				fileList = file.listFiles(filter);
			}
			
			for (File tmpFile : fileList) {
				result.addAll(searchAllFiles(tmpFile, filter));
			}
		} else {
			if (filter == null) {
				result.add(file);
			} else if (filter.accept(file.getParentFile(), file.getName())) {
				result.add(file);
			} else {
			}
		}
		return result;
	}
	
	public static List<File> searchAllFiles(File file) {

		return searchAllFiles(file, null);
	}
	
	public void ContractFile(File currentFile, File dstFile, FilenameFilter filter) {
		String result = "";

		try {
			if ( !dstFile.exists() ) {
				dstFile.createNewFile();
			}
			
			if (currentFile.isDirectory()) {
				
				if ( filter == null ) {
					System.out.println("FilenameFilter is Null ! ");
				}
				File file[] = currentFile.listFiles(filter);

				if (file == null || file.length == 0) {
					return;
				}

				FileOutputStream fos = new FileOutputStream(dstFile);
				// BufferedWriter out = new BufferedWriter(new OutputStreamWriter( fos, "GBK"));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

				long length = file.length;
				for (int i = 0; i < length; i++) {
					if (file[i].isDirectory()) {
						continue;
					}

					FileInputStream fin = new FileInputStream(file[i]);
					// BufferedReader in = new BufferedReader(new InputStreamReader(fin,"UTF-8"));
					BufferedReader in = new BufferedReader(new InputStreamReader(fin));

					String strLine = "";
					while ((strLine = in.readLine()) != null) {
						// System.out.println(strLine);
						out.write(strLine, 0, strLine.length());
						out.newLine();
						out.flush();
						result += strLine + "\r\n";
					}
				}
			} else { // File

			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static boolean renameFiles(File dir, FilenameFilter filter, String constantPrefix, String startNumberSuffix) {
		if (dir == null || constantPrefix == null || startNumberSuffix == null) {
			return false;
		}

		if (!dir.isDirectory()) {
			return false;
		}

		File[] fileList = null;
		if (filter == null) {
			fileList = dir.listFiles();
		} else {
			fileList = dir.listFiles(filter);
		}

		if (fileList == null) {
			return false;
		}

		if (startNumberSuffix.indexOf(".") != -1) {
			return false;
		}

		try {
			long suffix = Long.valueOf(startNumberSuffix);

			for (File file : fileList) {
				if (file.isDirectory()) {
					continue;
				}

				System.out.println(file.toString());

				String pattern = "";
				for (int i = 0; i < startNumberSuffix.length(); i++) {
					pattern += "0";
				}
				DecimalFormat df = new DecimalFormat(pattern);
				String numberSuffix = df.format(suffix);

				String newFileName = constantPrefix + numberSuffix;
				File dest = new File(file.getParent(), newFileName);
				file.renameTo(dest);

				suffix++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return true;
	}

	public static boolean renameFilesContain(File file, String filter, String replace) {
		if (file == null || filter == null || replace == null) {
			return false;
		}

		FilenameFilter fileFilter = (new XFile()).new XFilenameFiter(filter);

		if (file.isDirectory()) {
			File fileList[] = file.listFiles(fileFilter);

			if (file == null) {
				return false;
			}

			for (File tmpFile : fileList) {
				renameFilesContain(tmpFile, filter, replace);
			}
		} else {
			if (fileFilter.accept(file.getParentFile(), file.getName())) {
				System.out.println(file.toString());

				String newFileName = file.getName().replaceAll(filter, replace);
				File dest = new File(file.getParent(), newFileName);
				file.renameTo(dest);
			} else {
			}

		}
		return true;
	}

	public static boolean renameFilesPrefix(File file, String filter, String replace) {
		if (file == null || filter == null || replace == null) {
			return false;
		}

		FilenameFilter fileFilter = (new XFile()).new XFilenameFiter(filter, "");

		if (file.isDirectory()) {
			File fileList[] = file.listFiles(fileFilter);

			if (file == null) {
				return false;
			}

			for (File tmpFile : fileList) {
				renameFilesPrefix(tmpFile, filter, replace);
			}
		} else {
			if (fileFilter.accept(file.getParentFile(), file.getName())) {
				System.out.println(file.toString());

				String newFileName = file.getName().replaceFirst(filter, replace);
				File dest = new File(file.getParent(), newFileName);
				file.renameTo(dest);
			} else {
			}
		}
		return true;
	}

	public static boolean renameFilesSuffix(File file, String filter, String replace) {
		if (file == null || filter == null || replace == null) {
			return false;
		}

		FilenameFilter fileFilter = (new XFile()).new XFilenameFiter("", filter);

		if (file.isDirectory()) {
			File fileList[] = file.listFiles(fileFilter);

			if (file == null) {
				return false;
			}

			for (File tmpFile : fileList) {
				renameFilesSuffix(tmpFile, filter, replace);
			}
		} else {
			if (fileFilter.accept(file.getParentFile(), file.getName())) {
				System.out.println(file.toString());
				String newFileName = file.getName().replaceAll(filter, replace);
				File dest = new File(file.getParent(), newFileName);
				file.renameTo(dest);
			} else {
			}
		}
		return true;
	}

	public List<String> ReadContent(File file, FilenameFilter filter) {
		return ReadDicContent(file, filter);
	}
	
	private List<String> ReadDicContent(File dicSrc, FilenameFilter filter) {
		
		List<String> content = new ArrayList<String>(0);

		if (dicSrc.isDirectory() == true) {

			File[] files = dicSrc.listFiles(filter);

			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory() == true) {
					content.addAll(ReadDicContent(files[i], filter));
				} else {
					content.addAll(ReadFileContent(files[i], filter));
				}
			}
		} else {
			content.addAll(ReadFileContent(dicSrc, filter));
		}

		return content;
	}

	private List<String> ReadFileContent(File fileSrc, FilenameFilter filter) {
		// GetContent
		List<String> arrContent = new ArrayList<String>(0);
		
		if ( fileSrc.exists() == false ) {
//			Log.Error("java.io.FileNotFoundException: " + fileSrc.getAbsolutePath() + " isn't Exists!");
			return new ArrayList<String>(0);
		}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileSrc));
			String inLine = reader.readLine();

			while (inLine != null) {
				arrContent.add(inLine);

				inLine = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			return new ArrayList<String>(0);
		}

		return arrContent;
	}

	public File CreateFile(List<String> contents, File file) {
		try {
//			File newFile = new File(fileSrc.getParent() + "/" + "New " + file.getName());
			File newFile = file;

			if (newFile.exists() == true) {
				if (!newFile.delete()) {
//					Log.Error("##### " + newFile.getName() + " can't be Deleted #####");
				}
			}
			
			newFile.createNewFile();

			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));

			for (int i = 0; i < contents.size(); i++) {

				writer.write(contents.get(i) + System.getProperty("line.separator"));
			}

			writer.close();

			return newFile;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Deprecated
	public static List<String> map2List(Map<Object,Object> map) {
		List<String> result = new ArrayList<String>(0);
		Iterator<Object> it = map.values().iterator();

		while (it.hasNext()) {
			Object obj = it.next();
			result.add(obj.toString());
		}

		return result;
	}

	@Deprecated
	public static void viewFileList(List<Object> list) {
		for (Object obj : list) {
			if (obj instanceof ArrayList) {
				viewFileList((List) obj);
			} else if (obj instanceof File) {
				System.out.println(((File) obj).toString());
			} else {
				System.out.println(obj.getClass());
			}
		}
	}

	@Deprecated
	public static void viewFileMap(Map<String, List<File>> map) {
		Set<Entry<String, List<File>>> set = map.entrySet();

		Iterator<Entry<String, List<File>>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, List<File>> entry = it.next();
			List<File> list = entry.getValue();

			System.out.println();
			System.out.println("##### [" + list.size() + "] ############### " + entry.getKey() + " ############### ");
			for (File file : list) {
				System.out.println(file.getAbsolutePath());
			}
		}

	}
	
	
	/**
	 * 文件名前后限定??器。
	 * 
	 * @since 1.0
	 */
	
	public class XFilenameFiter implements FilenameFilter {
	
		private String start;
	
		private String end;
	
		private String contain;
		/**
		 * 根据指定的参数?造一个FileNameFilter。
		 * 
		 * @param contain 文件名包含的?束字符串
		 * @since 1.0
		 */
		public XFilenameFiter(String contain) {
			this.start = "";
			this.end = "";
			this.contain = contain;
		}
	
		/**
		 * 根据指定的参数?造一个FileNameFilter。
		 * 
		 * @param start 文件名的?始字符串
		 * @param end 文件名的?束字符串
		 * @since 1.0
		 */
		public XFilenameFiter(String start, String end) {
			this.start = start;
			this.end = end;
			this.contain = "";
		}
	
		/**
		 * 根据指定的参数?造一个FileNameFilter。
		 * 
		 * @param start 文件名的?始字符串
		 * @param end 文件名的?束字符串
		 * @since 1.0
		 */
		public XFilenameFiter(String start, String end, String contain) {
			this.start = start;
			this.end = end;
			this.contain = contain;
		}
	
		/**
		 * 判断指定的文件是否可以被接受。
		 * 
		 * @param file 需要判断的文件
		 * @return 在任何情况都返回true。
		 * @since 1.0
		 */
		public boolean accept(File file, String name) {
			String filename = name;
			File currentFile = new File(file.getPath() + "/" + filename);
	
			if (currentFile.isDirectory()) {
				return true;
			} else if (filename.startsWith(start) 
					&& filename.endsWith(end)
					&& filename.indexOf(contain) != -1) {
				return true;
			}
	
			return false;
		}
	
		/**
		 * 返回??器的描述字符串。
		 * 
		 * @return ??器的描述字符串
		 * @since 1.0
		 */
		public String getDescription() {
	
			return "The file start with:" + start + " and end with:" + end;
		}
	}

}

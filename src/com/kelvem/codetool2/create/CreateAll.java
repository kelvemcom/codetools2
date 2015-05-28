/**
 * 
 */
package com.kelvem.codetool2.create;

import java.io.File;
import java.util.List;

import com.kelvem.codetool2.common.StringUtil;
import com.kelvem.codetool2.common.XFile;

/**
 * @author kelvem
 * 
 */
public class CreateAll {

	public static void main(String[] args) throws Exception {

		CreateSimple creater = new CreateSimple("", "", "");

		File file = new File(creater.srcPath);

		List<File> list = XFile.searchAllFiles(file);

		// Java
		for (File f : list) {
			if (f.getCanonicalPath().contains(".java") == false) {
				continue;
			}

			String templateFile = f.getAbsolutePath();
			
			String folder = "";
			if (templateFile.toLowerCase().contains("dao") == true) {
				folder = "/dao";
			} else if (templateFile.toLowerCase().contains("service") == true) {
				folder = "/service";
			} else if (templateFile.toLowerCase().contains("action") == true) {
				folder = "/action";
			} else if (templateFile.toLowerCase().contains("model") == true) {
				folder = "/model";
			} else if (templateFile.toLowerCase().contains("queryvo") == true) {
				folder = "/queryvo";
			}
			
			String fileName = f.getName();
			fileName = fileName.replaceAll(".template", "");
			fileName = creater.params.table.tableName._AaaAaa + fileName;
			
			String targetFile = creater.dstPath + creater.params.javaPathBase + folder + "/" + fileName;
			
			creater = new CreateSimple(f.getName(), templateFile, targetFile);
			creater.createSourceFile();
		}

		// Xml
		for (File f : list) {
			if (f.getCanonicalPath().contains(".xml") == false) {
				continue;
			}

			String templateFile = f.getAbsolutePath();
			
			String filePath = "";
			if (templateFile.toLowerCase().contains("hbm") == true) {
				filePath = "/hibernate/" + creater.params.menuLevel2NameEn + "/" 
						+ creater.params.table.tableName._AaaAaa + ".hbm.xml";
			} else if (templateFile.toLowerCase().contains("spring") == true) {
				filePath = "/spring/spring-context-"+ creater.params.menuLevel2NameEn + ".xml";
			} else if (templateFile.toLowerCase().contains("struts") == true) {
				filePath = "//struts/struts-" + creater.params.menuLevel2NameEn + ".xml";
			}
			
			String targetFile = creater.dstPath + creater.params.resourcePathBase + filePath;
			
			creater = new CreateSimple(f.getName(), templateFile, targetFile);
			creater.createSourceFile();
		}

		// Jsp
		for (File f : list) {
			if (f.getCanonicalPath().contains(".jsp") == false) {
				continue;
			}

			String templateFile = f.getAbsolutePath();
			
			String folder = "";
			if (templateFile.toLowerCase().contains("dao") == true) {
				folder = "/dao";
			} else if (templateFile.toLowerCase().contains("service") == true) {
				folder = "/service";
			} else if (templateFile.toLowerCase().contains("action") == true) {
				folder = "/action";
			}
			
			String fileName = f.getName();
			fileName = fileName.replaceAll(".template", "");
			fileName = creater.params.table.tableName._aaaAaa + StringUtil.toUpperFirstChar(fileName);
			
			String targetFile = creater.dstPath + creater.params.jspPathBase + folder + "/" + fileName;
			
			creater = new CreateSimple(f.getName(), templateFile, targetFile);
			creater.createSourceFile();
		}
	}

}

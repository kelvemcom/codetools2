/**
 * 
 */
package com.kelvem.codetool2.create;

import java.io.File;
import java.util.List;

import com.kelvem.codetool2.common.TemplateMaker;
import com.kelvem.codetool2.common.XFile;

/**
 * @author kelvem
 * 
 */
public class CreateAll2 {

	public static void main(String[] args) throws Exception {

		CreateSimple creater = new CreateSimple("", "", "");

		File file = new File(creater.srcPath);

		List<File> list = XFile.searchAllFiles(file);

		for (File f : list) {

			String templateFile = f.getAbsolutePath();
			
			String targetFile = templateFile.replace(creater.params.templatePath.replace("/", ""), creater.params.targetPath.replace("/", ""));
			targetFile = TemplateMaker.parse(targetFile, creater.params);
			
			creater = new CreateSimple(f.getName(), templateFile, targetFile);
			creater.createSourceFile();
		}
	}

}

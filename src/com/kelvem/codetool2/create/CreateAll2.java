/**
 * 
 */
package com.kelvem.codetool2.create;

import java.io.File;
import java.util.List;

import com.kelvem.codetool2.common.TemplateMaker;
import com.kelvem.codetool2.common.XFile;
import com.kelvem.codetool2.model.RootModel;

/**
 * @author kelvem
 * 
 */
public class CreateAll2 {

	public static void main(String[] args) throws Exception {

		RootModel rootModel = RootModel.getInstance();

		File file = new File(rootModel.projectPath + "/" + rootModel.templatePath);

		List<File> list = XFile.searchAllFiles(file);

		for (File f : list) {

			String templateFile = f.getAbsolutePath();
			String targetFile = templateFile.replace(rootModel.templatePath, rootModel.targetPath);
			targetFile = TemplateMaker.parse(targetFile, rootModel);
			
			TemplateMaker.parse(templateFile, targetFile, rootModel);
		}
	}

}

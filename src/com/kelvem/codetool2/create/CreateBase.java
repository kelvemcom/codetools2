/**
 * 
 */
package com.kelvem.codetool2.create;

import java.io.File;

import com.kelvem.codetool2.common.FileUtil;
import com.kelvem.codetool2.common.Log;
import com.kelvem.codetool2.common.TemplateMaker;
import com.kelvem.codetool2.model.RootModel;


/**
 * @author kelvem
 *
 */
public abstract class CreateBase {

	
	
//	public abstract boolean create();

	public String createrName = "自动代码创建基类";
	public RootModel params = null;
	
	public File root = null;
	public String templateFile = null;
	public String targetFile = null;
	public String srcPath = null;
	public String dstPath = null;
	
	public CreateBase() throws Exception{
		params = RootModel.getInstance();
		
		String basePath = System.getProperty("user.dir");		
		File dic = new File(basePath);		
		root = dic.getParentFile();
		
//		srcPath = root.getAbsolutePath() + params.TemplatePath;
//		dstPath = root.getAbsolutePath() + params.TargetPath;
		srcPath = basePath + params.templatePath;
		dstPath = basePath + params.targetPath;
	}	
	
	// ###
	public boolean createSourceFile() throws Exception{
		
		Log.info(">>> >>> 处理模板文件开始：" + createrName);
		
		String fileContent = readTemplateFile();
		if ( fileContent == null || fileContent.isEmpty() ){
			Log.error("读取文件失败：" + templateFile);
			return false;
		} else {
			Log.info("读取文件成功");
		}
		
		fileContent = TemplateMaker.parse(fileContent, params);
		
		writeSourceFile(fileContent);
		if ( fileContent == null || fileContent.isEmpty() ){
			Log.error("创建文件失败：" + targetFile);
			return false;
		} else {
			Log.info("创建文件成功：" + targetFile);
		}	

		Log.info("<<< <<< 处理模板文件结束：" + createrName);
		Log.info("");
		Log.info("");
		Log.info("");
		
		return true;
	}
	
	public boolean checkFileExist(){
		
		File fileTemplate = new File(templateFile);
		if (!fileTemplate.exists()){
			Log.error("模板文件不存在：" + templateFile);
			return false;
		}
		
		File fileTarget = new File(targetFile);
		if (fileTarget.exists()){
			Log.error("目标文件已存在，请确认删除：" + targetFile);
			return false;
		}		
		
		return true;
	}
	
	public String readTemplateFile(){
		
		
		// 读取模板文件
		return FileUtil.readFile(templateFile);
	}
	
	public abstract String filterSpecialField(String fileContent);
	
	public boolean writeSourceFile(String fileContent){
		
		// 生成资源文件
		return FileUtil.writeFile(targetFile, fileContent);
	}
	
}

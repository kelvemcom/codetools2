package com.kelvem.codetool2.common;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.kelvem.codetool2.model.RootModel;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 模板解析
 * 
 * @author kelvem
 */

public class TemplateMaker implements TemplateLoader {
	
	private String template = null;

	private TemplateMaker(String template) {
		this.template = template;
	}

	@Override
	public Object findTemplateSource(String name) throws IOException {
		return this.template;
	}
	
	public void closeTemplateSource(Object templateSource) throws IOException {

	}

	public long getLastModified(Object templateSource) {
		return 0;
	}

	public Reader getReader(Object templateSource, String encoding) throws IOException {
		return new StringReader((String) templateSource);
	}

	
	public static String parse(String source, Object rootMap) {
		
		if (source == null ) {
			return "";
		}
		if(source.trim().equalsIgnoreCase("")) {
			return "";
		}
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(new TemplateMaker(source));
		cfg.setDefaultEncoding("UTF-8");

		StringWriter writer = new StringWriter();
		Template template = null;
	
		try {
			template = cfg.getTemplate("");
			template.process(rootMap, writer);
		} catch(Exception e) {
			System.out.println("Template formating error!");
			try {
				writer.close();
			} catch (IOException e1) {
				System.out.println("Template formating error!");
			}
			throw new RuntimeException("Template formating error!", e);
		}

		return writer.toString();
	}
	
	public static boolean parse(String templateFile, String targetFile, Object rootMap) {
		
		String createrName = "FreeMarkerParser";
		
		File fileTemplate = new File(templateFile);
		if (!fileTemplate.exists()){
			Log.error("模板文件不存在：" + templateFile);
			return false;
		}
		
		Log.info(">>> >>> 处理模板文件开始：" + createrName);
		
		// 读取模板文件
		String fileContent = FileUtil.readFile(templateFile);

		if ( fileContent == null || fileContent.isEmpty() ){
			Log.error("读取文件失败：" + templateFile);
			return false;
		} else {
			Log.info("读取文件成功");
		}
		
		fileContent = TemplateMaker.parse(fileContent, rootMap);
		
		FileUtil.writeFile(targetFile, fileContent);
		
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

	public static void main(String[] args) {

		Map<String, String> dataMap = new HashMap<String, String>();
		
		dataMap.put("name", "Rainy"); 
		dataMap.put("myname", "Dave");
		dataMap.put("date", "2011-03-31");
		 
		String templateString = "<html><body>Name=${name}<br><input type='hidden' name='myname' value='${myname}'/> Date:${date} {!None.Nothing} </body></html>";
		 
		String result = parse(templateString, dataMap);
		
		System.out.println(result);
		
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		RootModel rootModel = RootModel.getInstance();
		templateString = "<p>${moduleNameGb} Dao</p>";
		result = parse(templateString, rootModel);
		System.out.println(result);
		
		
	}
}
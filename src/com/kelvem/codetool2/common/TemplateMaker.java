package com.kelvem.codetool2.common;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

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

	public static void main(String[] args) {

		Map<String, String> dataMap = new HashMap<String, String>();
		
		dataMap.put("name", "Rainy"); 
		dataMap.put("myname", "Dave");
		dataMap.put("date", "2011-03-31");
		 
		String templateString = "<html><body>Name=${name}<br><input type='hidden' name='myname' value='${myname}'/> Date:${date} {!None.Nothing} </body></html>";
		 
		String result = parse(templateString, dataMap);
		
		System.out.println(result);
	}
}
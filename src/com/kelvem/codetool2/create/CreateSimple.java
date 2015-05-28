/**
 * 
 */
package com.kelvem.codetool2.create;



/**
 * @author kelvem
 *
 */
public class CreateSimple extends CreateBase {

//	public CreateSimple() throws Exception {
//		super();
//		
//		this.createrName = "Simple类";
//		
//		this.templateFile = srcPath + "/dao/Dao.java.template";
//		this.targetFile = dstPath + "/src/" + params.MenuLevel2NameEn + "/com/cslc/ump/" + params.MenuLevel2NameEn + "/action/" 
//				+ params.table.TableName.getName$AaaAaa() + "Action.java";
//
//	}

	public CreateSimple(String name, String templateFile, String targetFile) throws Exception {
		super();
		
		this.createrName = name;
		
		this.templateFile = templateFile;
		this.targetFile = targetFile;
//		this.templateFile = srcPath + templateFile;
//		this.targetFile = dstPath + targetFile;

	}

	@Override
	public String filterSpecialField(String fileContent) {
		// TODO Auto-generated method stub
		return fileContent;
	}
	
	
}

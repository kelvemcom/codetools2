/**
 * 
 */
package com.kelvem.codetool2.model;

import java.io.File;
import java.util.Date;

import com.kelvem.codetool2.common.DateUtils;
import com.kelvem.codetool2.common.StringUtil;
import com.kelvem.codetool2.database.DummyServer;

/**
 * @author kelvem
 * 
 */
public class RootModel {

	public static RootModel instance = null;
	// --------------------------
	// 环境变量
	// --------------------------
	public String projectName = "finance";
	public String packageBase = "cn.edaijia.finance";
	public String authorName = "kelvem";
	public String companyName = "edaijia.cn";
	public String createTime = DateUtils.getDateString(new Date());
	
	public String templatePath = "/code_template";
	public String targetPath = "/out";
	
	// --------------------------
	// 数据库变量
	// --------------------------
	public TableModel table = new TableModel();
	
	// --------------------------
	// 业务变量
	// --------------------------
	public String menuLevel2NameEn = "payment".toLowerCase();
	public String menuLevel2NameGb = "支付系统管理";

	public String tableNameEn = "t_finance_log".toLowerCase();
	public String tableNameGb = "资金流水记录";
	
	public String javaPathBase = "/src/com/kelvem/" + projectName.toLowerCase() + "/" + menuLevel2NameEn;
	public String resourcePathBase = "/conf/";
	public String jspPathBase = "/page/" + menuLevel2NameEn;

	public static RootModel getInstance() {
		if ( instance == null ){
			instance = new RootModel();
		}		
		return instance;
	}
	
	// --------------------------	
	// 构造函数
	// --------------------------	
	private RootModel() {

//		PropertiesUtil.properties2Model("codetool.ini", this);
		
		if ( check() == false ){
			throw new RuntimeException("ParamsModel类有成员设定初始值异常！");
		}
		
		File directory = new File("."); 
		System.out.println(directory.getAbsolutePath());
		
//		table = DatabaseServer.queryTableInfo(this.TableNameEn);
		table = DummyServer.queryTableInfo(this.tableNameEn);
		
		table.tableName = new StringNode(this.tableNameEn);
		
		ModuleName = StringUtil.toUpperFirstChar(this.tableNameEn);
		PackagePrefix = packageBase + "." + menuLevel2NameEn;
		
		ClassModelName = StringUtil.AAA_AAAToAaaAaa(this.tableNameEn);
		ClassModelFieldName= StringUtil.toLowerFirstChar(ClassModelName);
		
		ClassVoName = StringUtil.AAA_AAAToAaaAaa(this.tableNameEn);
		ClassVoFieldName= StringUtil.toLowerFirstChar(ClassVoName);
		
		KeyColumnModel = new ColumnModel();
		if ( table.listPK.size() == 1 ){
			KeyColumnModel = table.listPK.get(0);
		} else if ( table.listPK.size() > 1 ){
			KeyColumnModel.columnName = new StringNode(table.tableName._AAA_AAA + "_ID");
			KeyColumnModel.columnType = new TypeNode(table.tableName._AAA_AAA + "_ID");
			KeyColumnModel.isNullEnable = false;
			KeyColumnModel.columnDesc = "主键";
		} else {
			
		}
	}

	// --------------------------
	// 中间变量
	// --------------------------
	
	// from TableName
	public String ModuleName;
	public String PackagePrefix;
	public String ClassModelName;
	public String ClassModelFieldName;
	public String ClassVoName;
	public String ClassVoFieldName;
	public ColumnModel KeyColumnModel;
	
	// ###
	public boolean check() {
		
		if (menuLevel2NameEn == null 
				|| menuLevel2NameGb == null
				|| tableNameEn == null 
				|| tableNameGb == null
				) {
			return false;
		}
		
		return true;
	}
	
	// ###
	public void printAllParams(){
		
		
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPackageBase() {
		return packageBase;
	}

	public void setPackageBase(String packageBase) {
		this.packageBase = packageBase;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public TableModel getTable() {
		return table;
	}

	public void setTable(TableModel table) {
		this.table = table;
	}

	public String getMenuLevel2NameEn() {
		return menuLevel2NameEn;
	}

	public void setMenuLevel2NameEn(String menuLevel2NameEn) {
		this.menuLevel2NameEn = menuLevel2NameEn;
	}

	public String getMenuLevel2NameGb() {
		return menuLevel2NameGb;
	}

	public void setMenuLevel2NameGb(String menuLevel2NameGb) {
		this.menuLevel2NameGb = menuLevel2NameGb;
	}

	public String getTableNameEn() {
		return tableNameEn;
	}

	public void setTableNameEn(String tableNameEn) {
		this.tableNameEn = tableNameEn;
	}

	public String getTableNameGb() {
		return tableNameGb;
	}

	public void setTableNameGb(String tableNameGb) {
		this.tableNameGb = tableNameGb;
	}

	public String getJavaPathBase() {
		return javaPathBase;
	}

	public void setJavaPathBase(String javaPathBase) {
		this.javaPathBase = javaPathBase;
	}

	public String getResourcePathBase() {
		return resourcePathBase;
	}

	public void setResourcePathBase(String resourcePathBase) {
		this.resourcePathBase = resourcePathBase;
	}

	public String getJspPathBase() {
		return jspPathBase;
	}

	public void setJspPathBase(String jspPathBase) {
		this.jspPathBase = jspPathBase;
	}


	
}

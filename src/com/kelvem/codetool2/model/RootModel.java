/**
 * 
 */
package com.kelvem.codetool2.model;

import java.util.Date;

import com.kelvem.codetool2.common.DateUtils;
import com.kelvem.codetool2.common.PropertiesUtil;
import com.kelvem.codetool2.database.DatabaseServer;

/**
 * @author kelvem
 * 
 */
public class RootModel {

	private static RootModel instance = null;
	
	// --------------------------
	// 环境变量
	// --------------------------
	public String packageBase = null;
	public String authorName = null;
	public String companyName = null;
	
	public String templatePath = null;
	public String targetPath = null;
	
	// --------------------------
	// 业务变量
	// --------------------------
	public String projectName = null;
	
	public String moduleNameEn = null;
	public String moduleNameGb = null;

	public String tableNameEn = null;
	public String tableNameGb = null;

	// --------------------------
	// 数据库变量
	// --------------------------
	public TableModel table = null;

	// --------------------------
	// 其他变量
	// --------------------------
	public String createTime = DateUtils.getDateString(new Date());
	public String projectPath = System.getProperty("user.dir");
	
	public String javaPathBase = null;
	public String resourcePathBase = null;
	public String jspPathBase = null;
	
	
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

		PropertiesUtil.properties2Model("codetool2.properties", this);
		
		if ( check() == false ){
			throw new RuntimeException("RootModel类有成员设定初始值异常！");
		}
		
		table = DatabaseServer.queryTableInfo(this.tableNameEn);
		moduleNameEn = tableNameEn.toLowerCase();
		moduleNameEn = moduleNameEn.toLowerCase();
		tableNameEn = tableNameEn.toLowerCase();

		javaPathBase = "/src/com/kelvem/" + projectName.toLowerCase() + "/" + moduleNameEn;
		resourcePathBase = "/conf/";
		jspPathBase = "/page/" + moduleNameEn;
		
	}
	
	// ###
	public boolean check() {
		
		if (moduleNameEn == null 
				|| moduleNameGb == null
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

	public String getModuleNameEn() {
		return moduleNameEn;
	}

	public void setModuleNameEn(String moduleNameEn) {
		this.moduleNameEn = moduleNameEn;
	}

	public String getModuleNameGb() {
		return moduleNameGb;
	}

	public void setModuleNameGb(String moduleNameGb) {
		this.moduleNameGb = moduleNameGb;
	}

	public static void main(String[] args) {
		RootModel root = RootModel.getInstance();
		System.out.println(root.moduleNameGb);
	}
	
}

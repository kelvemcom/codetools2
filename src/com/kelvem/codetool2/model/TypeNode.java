package com.kelvem.codetool2.model;



public class TypeNode {

	// 1 int 2 varchar 3 time 4 tinyint  9 key_id
	public String type_db;
	public String type_java;
	public String type_mybatis;
	public String full = null;
	public String mini = null;
	
	public TypeNode(String type_db){

		if(type_db == null){
			return;
		}

		this.type_db = type_db.toUpperCase();
		this.type_mybatis = type_db.toUpperCase();
		
		if(type_db.equalsIgnoreCase("int") || type_db.equalsIgnoreCase("NUMBER")) {
			type_java = "Integer";
			mini = "Integer";
			full = "java.lang.Integer";
			type_mybatis = "Integer";
		} else if (type_db.equalsIgnoreCase("tinyint")) {
			type_java = "Integer";
			mini = "Integer";
			full = "java.lang.Integer";
		} else if (type_db.equalsIgnoreCase("varchar") || type_db.equalsIgnoreCase("VARCHAR2")) {
			type_java = "String";
			mini = "String";
			full = "java.lang.String";
		} else if (type_db.equalsIgnoreCase("bigint")) {
			type_java = "Long";
			mini = "Long";
			full = "java.lang.Long";
		} else if (type_db.equalsIgnoreCase("decimal")) {
			type_java = "BigDecimal";
			mini = "BigDecimal";
			full = "java.math.BigDecimal";
		} else if (type_db.equalsIgnoreCase("date") || type_db.equalsIgnoreCase("timestamp") || type_db.equalsIgnoreCase("datetime")) {
			type_java = "Date";
			mini = "Date";
			full = "java.util.Date";
			type_mybatis = "TIMESTAMP";
		} else {
			type_java = (new StringNode(type_db))._AaaAaa;
			mini = (new StringNode(type_db))._AaaAaa;
			full = (new StringNode(type_db))._AaaAaa;
		}

	}


	public static void main(String[] args) {
//		TypeNode node_xxx = new TypeNode("AAA_AAA");
//		System.out.println(node_xxx.AaaAaa);
//		System.out.println(node_xxx.aaaAaa);
//		System.out.println(node_xxx.aaaaaa);
//		System.out.println(node_xxx.AaaAaa);
//		System.out.println(node_xxx.aaa_aaa);
//		System.out.println(node_xxx.AAA_AAA);
	}


	public String getFull() {
		return full;
	}


	public void setFull(String full) {
		this.full = full;
	}


	public String getMini() {
		return mini;
	}


	public void setMini(String mini) {
		this.mini = mini;
	}


	public String getType_db() {
		return type_db;
	}


	public void setType_db(String type_db) {
		this.type_db = type_db;
	}


	public String getType_java() {
		return type_java;
	}


	public void setType_java(String type_java) {
		this.type_java = type_java;
	}


	public String getType_mybatis() {
		return type_mybatis;
	}


	public void setType_mybatis(String type_mybatis) {
		this.type_mybatis = type_mybatis;
	}
	
}

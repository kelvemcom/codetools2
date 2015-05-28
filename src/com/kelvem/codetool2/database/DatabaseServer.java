package com.kelvem.codetool2.database;

import java.sql.Connection;

import com.kelvem.codetool2.model.TableModel;

public abstract class DatabaseServer {

	//1. 读取Property文件
	//2. 确定使用哪种数据库
	//3. 创建Connection
	//4. 解析Table
		
	private static DatabaseServer server = null;
	private static ConnectionProperty property = null;
	
	static {
		init();
	}
	
	private Connection connection = null;
	
	protected abstract Connection createConnection(ConnectionProperty property);
	protected abstract TableModel getTableInfo(String schemaName, String tableName);
	
	protected static void init(){
		
		property = readProperty();
		
		try {
			server = (DatabaseServer)Class.forName(property.getServerClass()).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("[Error] ServerClass Property isn't Correct # " + property.getServerClass(), e);
		}

		server.setConnection(server.createConnection(property));		
		
	}
	
	private static ConnectionProperty readProperty(){

		ConnectionProperty property = new ConnectionProperty();
		property.setServerClass("com.kelvem.codetool.database.MySqlServer");
//		property.setServerClass("com.kelvem.codetool.database.DummyServer");
		property.setDriver("com.mysql.jdbc.Driver");
		property.setIp("localhost");
		property.setPort("3306");
		property.setSchemaName("blog");
		property.setUserName("root");
		property.setPassword("321456");
		
		return property;
	}
	
	public static TableModel queryTableInfo(String tableName){
		if (server == null || server.getConnection() == null) {
			init();
		}
		return server.getTableInfo(property.getSchemaName(), tableName);
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}

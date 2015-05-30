package com.kelvem.codetool2.database;

import java.sql.Connection;
import java.util.Properties;

import com.kelvem.codetool2.common.PropertiesUtil;
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

		Properties config = PropertiesUtil.readProperties("database.properties");
		ConnectionProperty property = new ConnectionProperty();
		property.setServerClass(config.getProperty("ServerClass"));
		property.setDriver(config.getProperty("Driver"));
		property.setIp(config.getProperty("Ip"));
		property.setPort(config.getProperty("Port"));
		property.setSchemaName(config.getProperty("SchemaName"));
		property.setUserName(config.getProperty("UserName"));
		property.setPassword(config.getProperty("Password"));
		
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

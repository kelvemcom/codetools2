package com.kelvem.codetool2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kelvem.codetool2.model.ColumnModel;
import com.kelvem.codetool2.model.StringNode;
import com.kelvem.codetool2.model.TableModel;
import com.kelvem.codetool2.model.TypeNode;

public class MySqlServer extends DatabaseServer {

	@Override
	protected Connection createConnection(ConnectionProperty property) {

		String url = "jdbc:mysql://" + property.getIp() + ":" + property.getPort() + "/" + property.getSchemaName() ;

		Connection conn = null;
		
		try { 
			
			// 加载驱动程序
			Class.forName(property.getDriver());
			
		} catch (Exception e) {
			throw new RuntimeException("[Error] Driver Property isn't Correct # " + property.getDriver(), e);
		}
		
		try { 
			
			// 连续数据库
			conn = DriverManager.getConnection(url, property.getUserName(), property.getPassword());

			if(!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
			}

		} catch (Exception e) {
			throw new RuntimeException("[Error] Connection can't be create # " + url, e);
		}
		
		return conn;
	}

	@Override
	protected TableModel getTableInfo(String schemaName, String tableName) {
		if(tableName==null || tableName.trim().equals("")) {
			return null;
		}
		
		TableModel table = new TableModel();
		table.tableName = new StringNode(tableName.toUpperCase());
		
		Connection con = this.getConnection();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try{
			
			//获取数据字典信息
			pstmt = con.prepareStatement("select * from information_schema.columns c where c.table_name = '" + tableName + "' and table_schema = '" + schemaName + "' order by c.ordinal_position;");

			rs = pstmt.executeQuery();

			// 获取信息
			while (rs.next()) {
				
				ColumnModel col = new ColumnModel();
				col.columnName = new StringNode(rs.getString("COLUMN_NAME"));
				col.columnType = new TypeNode(rs.getString("DATA_TYPE"));
				col.columnDesc = rs.getString("COLUMN_COMMENT");
				col.isNullEnable = (rs.getString("IS_NULLABLE").equals("NO")) ? false : true;
				if (col.columnType.mini.equals("String")) {
					col.columnSize = rs.getInt("CHARACTER_MAXIMUM_LENGTH");
				}				
				
				if (rs.getString("COLUMN_KEY").equals("PRI")) {
					table.listPK.add(col);
				} else {
					table.listColumn.add(col);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return table;
	}


}

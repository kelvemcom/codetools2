package com.kelvem.codetool2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.kelvem.codetool2.model.ColumnModel;
import com.kelvem.codetool2.model.StringNode;
import com.kelvem.codetool2.model.TableModel;
import com.kelvem.codetool2.model.TypeNode;

public class OracleServer extends DatabaseServer {

	@Override
	protected Connection createConnection(ConnectionProperty property) {

		//数据库连接信息
		String driver="oracle.jdbc.driver.OracleDriver";
		String driverUrl="jdbc:oracle:thin:@192.168.0.200:1521:orcl";
		String user="cheniwo";
		String password="cheniwo";
		
		//获取数据库连接
		Connection conn;
		try {
			//注册数据库驱动程序
			Class.forName(driver);
			conn = DriverManager.getConnection(driverUrl,user,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("创建连接失败");
		}
		
		return conn;
	}
	
	@Override
	protected TableModel getTableInfo(String schemaName, String tableName){
		if(tableName==null || tableName.trim().equals("")) {
			return null;
		}
		
		TableModel table = new TableModel();
		table.tableName = new StringNode(tableName.toUpperCase());
		
		ColumnModel col; 
		ColumnModel pkcol;
		
		
		Connection con = this.getConnection();
		
        ResultSet rst = null;
        PreparedStatement pstmt = null;

        ResultSetMetaData rsmd = null;
        
        Map<String, String> map = new HashMap<String, String>();
        try{
            
            
            //获取数据字典信息
            pstmt = con.prepareStatement("SELECT * FROM USER_COL_COMMENTS where table_name ='"+table.tableName+"'");

			rst = pstmt.executeQuery();
			//将数据字典信息写入到hashMap中 key 为 tablename+field value为comments
			 while(rst.next()){
			    String tmpKey = rst.getString("TABLE_NAME")+"_"+rst.getString("COLUMN_NAME");
			     map.put(tmpKey, rst.getString("COMMENTS"));
			 }
            
            //获取主键信息
            //rst = con.getMetaData().getPrimaryKeys(null,null,table.TableName);
			 pstmt = con.prepareStatement("select col.column_name from user_constraints con,user_cons_columns col where con.constraint_name=col.constraint_name and con.constraint_type='P' and col.table_name='"+table.tableName+"'");
			 rst = pstmt.executeQuery();
			//主键信息
			 while(rst.next()){
				 col = new ColumnModel();
	                col.columnName = new StringNode(rst.getString("COLUMN_NAME"));
	        		col.columnType = new TypeNode("Xxxxxxxxxxxxxxxxx");
	        		col.columnDesc = (String)map.get(table.tableName+"_"+col.columnName);
	        		table.listPK.add(col);
			 }
             //主键信息
//            if (!rst.isAfterLast()) {
//                rst.next();
//                col = new ColumnModel();
//                col.ColumnName = rst.getString("COLUMN_NAME");
//        		col.ColumnTypeID = 0;
//        		col.ColumnDesc = (String)map.get(table.TableName+"_"+col.ColumnName);
//        		table.listPK.add(col);
//            }

            
			pstmt = con.prepareStatement("SELECT * FROM " + table.tableName);

			rst = pstmt.executeQuery();
			

			rsmd = rst.getMetaData(); // 获取字段名
			if (rsmd != null) {
				//表列数
				int count = rsmd.getColumnCount();

				for (int i = 1; i <= count; i++) {
					/*
					System.out.println(tableName + "======"
							+ rsmd.getColumnName(i) + " size "
							+ rsmd.getColumnDisplaySize(i) + "type "
							+ rsmd.getColumnTypeName(i) + rsmd.isNullable(i));
					*/
					for (int m = 0; m < table.listPK.size(); m++) {
						pkcol = table.listPK.get(m);
						// primaryKey
						if (pkcol.columnName.equals(rsmd.getColumnName(i))) {
							pkcol.columnType = new TypeNode(rsmd.getColumnTypeName(i));
							if (rsmd.getColumnTypeName(i).equals("VARCHAR2"))
								pkcol.columnSize = rsmd.getColumnDisplaySize(i);
							break;
						} else if ((m + 1) == table.listPK.size()) {
							col = new ColumnModel();
							col.columnName = new StringNode(rsmd.getColumnName(i));
							col.columnType = new TypeNode(rsmd.getColumnTypeName(i));
							col.columnDesc = (String)map.get(table.tableName+"_"+col.columnName);
							col.isNullEnable = (rsmd.isNullable(i) == 0) ? false
									: true;
							if (rsmd.getColumnTypeName(i).equals("VARCHAR2"))
								col.columnSize = rsmd.getColumnDisplaySize(i);
							table.listColumn.add(col);
						}
					}
				}
			}
		}
        catch (Exception e){
        	e.printStackTrace();
        }
        finally{
            try{
                //关闭连接
                if (rst != null)
                    rst.close();
                if (con != null)
                    con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
		return table;
	}
}

package com.kelvem.codetool2.database;

import java.sql.Connection;

import com.kelvem.codetool2.model.ColumnModel;
import com.kelvem.codetool2.model.StringNode;
import com.kelvem.codetool2.model.TableModel;
import com.kelvem.codetool2.model.TypeNode;

public class DummyServer extends DatabaseServer {
	
	@Override
	protected TableModel getTableInfo(String schemaName, String tableName) {
	
		TableModel table = new TableModel();
		table.tableName = new StringNode("C_PERSON_MGR");
		
		ColumnModel col; 
		
		col = new ColumnModel();
		col.columnName = new StringNode("ID1");
		col.columnType = new TypeNode("int");
		col.columnDesc = "主键ID1";
		table.listPK.add(col);
		
		col = new ColumnModel();
		col.columnName = new StringNode("ID2");
		col.columnType = new TypeNode("varchar");
//		col.columnDesc = "主键ID2";
		table.listColumn.add(col);
		
		col = new ColumnModel();
		col.columnName = new StringNode("NAME");
		col.columnType = new TypeNode("varchar");
		col.columnSize = 10;
		col.columnDesc = "姓名";
		table.listColumn.add(col);
		
		col = new ColumnModel();
		col.columnName = new StringNode("DESC");
		col.columnType = new TypeNode("varchar");
		col.columnSize = 20;
		col.columnDesc = "描述";
		table.listColumn.add(col);
		
		col = new ColumnModel();
		col.columnName = new StringNode("UPDATE_TIME");
		col.columnType = new TypeNode("date");
		col.columnSize = 0;
		col.columnDesc = "更新时间";
		col.isNullEnable = true;
		table.listColumn.add(col);
				
		return table;
	}

	@Override
	protected Connection createConnection(ConnectionProperty property) {
		return null;
	}
}

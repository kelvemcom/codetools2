/**
 * 
 */
package com.kelvem.codetool2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kelvem
 *
 */
public class TableModel {

//	public Map<String, ColumnModel> mapPK = new TreeMap<String, ColumnModel>();
//	
//	public Map<String, ColumnModel> mapColumn = new TreeMap<String, ColumnModel>();

	public List<ColumnModel> listPK = new ArrayList<ColumnModel>();
	
	public List<ColumnModel> listColumn = new ArrayList<ColumnModel>();
	
	public ColumnModel keyColumn = null;
	
	public StringNode tableName = null;
	public StringNode forTest = null;
	
	
	public List<ColumnModel> getListPK() {
		return listPK;
	}
	public void setListPK(List<ColumnModel> listPK) {
		this.listPK = listPK;
	}
	public List<ColumnModel> getListColumn() {
		return listColumn;
	}
	public void setListColumn(List<ColumnModel> listColumn) {
		this.listColumn = listColumn;
	}
	public StringNode getTableName() {
		return tableName;
	}
	public void setTableName(StringNode tableName) {
		this.tableName = tableName;
	}
	public StringNode getForTest() {
		return forTest;
	}
	public void setForTest(StringNode forTest) {
		this.forTest = forTest;
	}
	public ColumnModel getKeyColumn() {

		if (keyColumn != null) {
			return keyColumn;
		}
		
		if (listPK.size() == 1) {
			keyColumn = listPK.get(0);
		} else if ( listPK.size() > 1 ){
			keyColumn = new ColumnModel();
			keyColumn.columnName = new StringNode(tableName._AAA_AAA + "_ID");
			keyColumn.columnType = new TypeNode(tableName._AAA_AAA + "_ID");
			keyColumn.isNullEnable = false;
			keyColumn.columnDesc = "主键";
		} else {
			// do nothing
		}
		return keyColumn;
	}
	public void setKeyColumn(ColumnModel keyColumn) {
		this.keyColumn = keyColumn;
	}

}

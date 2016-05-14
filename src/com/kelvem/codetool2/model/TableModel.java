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
	
	public List<ColumnModel> listModel = null;
	public List<ColumnModel> listQuery = null;
	
	public ColumnModel keyColumn = null;
	
	public StringNode tableName = null;
	private ColumnModel nameColumn = null;
	private ColumnModel enableColumn = null;
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
	
	public List<ColumnModel> getListModel() {
		if (listModel == null) {
			listModel = new ArrayList<ColumnModel>();
			for (ColumnModel column : this.getListColumn()) {
				if (column.getExtend().getIsModel()) {
					listModel.add(column);
				}
			}
		}
		return listModel;
	}
	public void setListModel(List<ColumnModel> listModel) {
		this.listModel = listModel;
	}
	public List<ColumnModel> getListQuery() {
		if (listQuery == null) {
			listQuery = new ArrayList<ColumnModel>();
			for (ColumnModel column : this.getListColumn()) {
				if (column.getExtend().getIsQuery()) {
					listQuery.add(column);
				}
			}
		}
		return listQuery;
	}
	public void setListQuery(List<ColumnModel> listQuery) {
		this.listQuery = listQuery;
	}
	public ColumnModel getNameColumn() {
		if (this.nameColumn == null) {
			for (ColumnModel column : listColumn) {
				if ("name".equals(column.columnName._aaa_aaa)
						|| (tableName + "_name").equals(column.columnName._aaa_aaa)
						|| "email".equals(column.columnName._aaa_aaa)
						|| "mail".equals(column.columnName._aaa_aaa)) {
					this.nameColumn = column;
					break;
				}
			}
		}
		return nameColumn;
	}
	public void setNameColumn(ColumnModel nameColumn) {
		this.nameColumn = nameColumn;
	}
	public ColumnModel getEnableColumn() {
		if (this.enableColumn == null) {
			for (ColumnModel column : listColumn) {
				if ("del_flag".equals(column.columnName._aaa_aaa)
						|| "enable".equals(column.columnName._aaa_aaa)
						|| "enable_enum".equals(column.columnName._aaa_aaa)
						|| "enable_flag".equals(column.columnName._aaa_aaa)) {
					this.nameColumn = column;
					break;
				}
			}
		}
		return enableColumn;
	}
	public void setEnableColumn(ColumnModel enableColumn) {
		this.enableColumn = enableColumn;
	}

}

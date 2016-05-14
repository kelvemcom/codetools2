/**
 * 
 */
package com.kelvem.codetool2.model;


/**
 * @author kelvem
 *
 */
public class ColumnModel {

	public StringNode columnName;
	public TypeNode columnType; // 1 int 2 varchar 3 time 4 tinyint  9 key_id
	public int columnSize;
	public String columnDesc;
	public boolean isNullEnable = false; // ###
	private ColumnExtend extend = null;
	
	
	
	public StringNode getColumnName() {
		return columnName;
	}
	public void setColumnName(StringNode columnName) {
		this.columnName = columnName;
	}
	public TypeNode getColumnType() {
		return columnType;
	}
	public void setColumnType(TypeNode columnType) {
		this.columnType = columnType;
	}
	public int getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}
	public String getColumnDesc() {
		return (columnDesc == null) ? "" : columnDesc;
	}
	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
		this.extend = new ColumnExtend(columnDesc);
	}
	public boolean isNullEnable() {
		return isNullEnable;
	}
	public void setNullEnable(boolean isNullEnable) {
		this.isNullEnable = isNullEnable;
	}
	public ColumnExtend getExtend() {
		if (this.extend == null) {
			this.extend = new ColumnExtend(columnDesc);
		}
		return extend;
	}
	public void setExtend(ColumnExtend extend) {
		this.extend = extend;
	}
	
	
}

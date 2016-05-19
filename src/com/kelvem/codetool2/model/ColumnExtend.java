package com.kelvem.codetool2.model;

public class ColumnExtend {

	// memo!isModel!isParam
	
	public static void main(String[] args) {
		ColumnExtend comment = new ColumnExtend("memo!Y!N");
		System.out.println(comment.origin);
		System.out.println(comment.memo);
		System.out.println(comment.isModel);
		System.out.println(comment.isQuery);
	}
	
	public ColumnExtend(String origin) {
		this.origin = origin;
		String[] buf = origin.split("!");
		
		if (buf.length > 0) {
			this.memo = buf[0];
		}
		
		if (buf.length > 1) {
			this.isModel = "Y".equals(buf[1]) ? true : false;
		} else {
			this.isModel = true;
		}
		
		if (buf.length > 2) {
			this.isQuery = "Y".equals(buf[2]) ? true : false;
		} else {
			this.isQuery = true;
		}
	}

	private String origin = null;
	private String memo = "";
	private Boolean isModel = null;
	private Boolean isQuery = null;
	
	@Override
	public String toString() {
		return memo;
	}

	
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getIsModel() {
		return isModel;
	}

	public void setIsModel(Boolean isModel) {
		this.isModel = isModel;
	}

	public Boolean getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(Boolean isQuery) {
		this.isQuery = isQuery;
	}
}

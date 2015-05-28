package com.kelvem.codetool2.database;

public class TestDatabase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatabaseServer.queryTableInfo("user");
		
		System.out.println("Test OK");
	}

}

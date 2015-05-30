package com.kelvem.codetool2.model;

import com.kelvem.codetool2.common.StringUtil;

public class StringNode {

	private String node_xxx;
	public String _AaaAaa = null;
	public String _aaaAaa = null;
	public String _aaaaaa = null;
	public String _aaa_aaa = null;
	public String _AAA_AAA = null;
	public String forTest = null;
	
	public StringNode(String node_xxx){
		this.node_xxx = node_xxx;
		this._AaaAaa = StringUtil.toUpperFirstChar(StringUtil.AAA_AAAToAaaAaa(node_xxx));
		this._aaaAaa = StringUtil.toLowerFirstChar(StringUtil.AAA_AAAToAaaAaa(node_xxx));
		this._aaaaaa = StringUtil.AAA_AAAToAaaAaa(node_xxx).toLowerCase();
		this._aaa_aaa = node_xxx.toLowerCase();
		this._AAA_AAA = node_xxx.toUpperCase();
	}

	@Override
	public String toString() {
		return node_xxx;
	}

	public static void main(String[] args) {
		StringNode node_xxx = new StringNode("AAA_AAA");
		System.out.println(node_xxx._AaaAaa);
		System.out.println(node_xxx._aaaAaa);
		System.out.println(node_xxx._aaaaaa);
		System.out.println(node_xxx._AaaAaa);
		System.out.println(node_xxx._aaa_aaa);
		System.out.println(node_xxx._AAA_AAA);
	}

	public String getForTest() {
		return "";
	}

	public void setForTest(String forTest) {
		
	}

	public String get_AaaAaa() {
		return _AaaAaa;
	}

	public void set_AaaAaa(String _AaaAaa) {
		this._AaaAaa = _AaaAaa;
	}

	public String get_aaaAaa() {
		return _aaaAaa;
	}

	public void set_aaaAaa(String _aaaAaa) {
		this._aaaAaa = _aaaAaa;
	}

	public String get_aaaaaa() {
		return _aaaaaa;
	}

	public void set_aaaaaa(String _aaaaaa) {
		this._aaaaaa = _aaaaaa;
	}

	public String get_aaa_aaa() {
		return _aaa_aaa;
	}

	public void set_aaa_aaa(String _aaa_aaa) {
		this._aaa_aaa = _aaa_aaa;
	}

	public String get_AAA_AAA() {
		return _AAA_AAA;
	}

	public void set_AAA_AAA(String _AAA_AAA) {
		this._AAA_AAA = _AAA_AAA;
	}
	
}

package ${packageBase}.${moduleNameEn}.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>${tableNameGb} Model</p>
 * 
 * @author ${authorName}
 * @version 1.0
 * @since ${createTime}
 */
public class ${table.tableName._AaaAaa} {
	
	private static final long serialVersionUID = -1L;

<#list table.listPK as column>
	/**
	 * ${column.columnDesc}
	 */
	private ${column.columnType.mini} ${column.columnName._aaaAaa};
	
</#list> 
<#list table.listColumn as column>
	/**
	 * ${column.columnDesc}
	 */
	private ${column.columnType.mini} ${column.columnName._aaaAaa};
	
</#list> 

<#list table.listPK as column>
	/**
	 * 获取${column.columnDesc}
	 */
	public ${column.columnType.mini} get${column.columnName._AaaAaa}() {
		return ${column.columnName._aaaAaa};
	}
	/**
	 * 设置${column.columnDesc}
	 */
	public void set${column.columnName._AaaAaa}(${column.columnType.mini} ${column.columnName._aaaAaa}) {
		this.${column.columnName._aaaAaa} = ${column.columnName._aaaAaa};
	}
	
</#list>
<#list table.listColumn as column>
	/**
	 * 获取${column.columnDesc}
	 */
	public ${column.columnType.mini} get${column.columnName._AaaAaa}() {
		return ${column.columnName._aaaAaa};
	}
	/**
	 * 设置${column.columnDesc}
	 */
	public void set${column.columnName._AaaAaa}(${column.columnType.mini} ${column.columnName._aaaAaa}) {
		this.${column.columnName._aaaAaa} = ${column.columnName._aaaAaa};
	}
	
</#list>

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("${table.tableName._AaaAaa} {");
<#list table.listPK as column>
		sb.append("  ${column.columnName._aaaAaa}=" + ${column.columnName._aaaAaa});
</#list>
<#list table.listColumn as column>
		sb.append(", ${column.columnName._aaaAaa}=" + ${column.columnName._aaaAaa});
</#list>
		sb.append("}");
		
		return sb.toString();
	}

}
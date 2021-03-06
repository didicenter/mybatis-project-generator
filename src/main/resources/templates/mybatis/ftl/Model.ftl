package ${packageName}.model;

<#list dataTypeList as dataType>
<#if dataType == "JSONObject">
import com.alibaba.fastjson.JSONObject;
</#if>
<#if dataType == "JSONObject[]">
import com.alibaba.fastjson.JSONObject;
</#if>
</#list>
import lombok.Data;
import org.hddframework.jdbc.annotation.Column;
<#if containsPrimaryKey >
import org.hddframework.jdbc.annotation.ID;
</#if>
import org.hddframework.jdbc.annotation.Table;

<#list dataTypeList as dataType>
<#if dataType == "BigDecimal">
import java.math.BigDecimal;
</#if>
<#if dataType == "BigInteger">
import java.math.BigInteger;
</#if>
<#if dataType == "Date">
import java.sql.Date;
</#if>
<#if dataType == "Time">
import java.sql.Time;
</#if>
<#if dataType == "Timestamp">
import java.sql.Timestamp;
</#if>
<#if dataType == "BigDecimal[]">
import java.math.BigDecimal;
</#if>
<#if dataType == "BigInteger[]">
import java.math.BigInteger;
</#if>
<#if dataType == "Date[]">
import java.sql.Date;
</#if>
<#if dataType == "Time[]">
import java.sql.Time;
</#if>
<#if dataType == "Timestamp[]">
import java.sql.Timestamp;
</#if>
</#list>

@Data
@Table("${tableName}")
public class ${className} {

<#list propertyList as property>
    <#if property.primaryKey>
    @ID("${property.columnName}")
    </#if>
    @Column("${property.columnName}")
    private ${property.propertyType} ${property.propertyName};
</#list>

}
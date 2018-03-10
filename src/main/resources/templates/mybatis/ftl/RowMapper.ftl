package ${packageName}.dao.mapper;

<#list dataTypeList as dataType>
<#if dataType == "JSONObject">
import com.alibaba.fastjson.JSONObject;
</#if>
<#if dataType == "JSONObject[]">
import com.alibaba.fastjson.JSONObject;
</#if>
</#list>
import ${packageName}.model.${className};
import org.springframework.jdbc.core.RowMapper;

<#list dataTypeList as dataType>
<#if dataType == "BigDecimal">
import java.math.BigDecimal;
</#if>
<#if dataType == "BigDecimal[]">
import java.math.BigDecimal;
</#if>
</#list>
<#list dataTypeList as dataType>
<#if dataType?string?contains('[]')>
import java.sql.Array;
</#if>
</#list>
import java.sql.ResultSet;
import java.sql.SQLException;
<#list dataTypeList as dataType>
<#if dataType?string?contains('[]')>
import java.util.Objects;
</#if>
</#list>

public class ${className}RowMapper implements RowMapper<${className}> {

    @Override
    public ${className} mapRow(ResultSet rs, int rowNum) throws SQLException {
        ${className} ${className?uncap_first} = new ${className}();
        <#list propertyList as property>
        <#if property.propertyType =='Integer'>
        ${className?uncap_first}.set${property.propertyName?cap_first}(rs.getInt("${property.columnName}"));
        <#elseif property.propertyType =='JSONObject'>
        ${className?uncap_first}.set${property.propertyName?cap_first}(JSONObject.parseObject(rs.getString("${property.columnName}")));
        <#elseif property.propertyType?contains('[]')>
        Array array${property.propertyName?cap_first} = rs.getArray("${property.columnName}");
        if(Objects.nonNull(array${property.propertyName?cap_first})){
            Object data${property.propertyName?cap_first} = array${property.propertyName?cap_first}.getArray();
            if(Objects.nonNull(data${property.propertyName?cap_first})){
                ${className?uncap_first}.set${property.propertyName?cap_first}((${property.propertyType?cap_first})data${property.propertyName?cap_first});
            }
        }
        <#else>
        ${className?uncap_first}.set${property.propertyName?cap_first}(rs.get${property.propertyType?cap_first}("${property.columnName}"));
        </#if>
        </#list>
        return ${className?uncap_first};
    }

}
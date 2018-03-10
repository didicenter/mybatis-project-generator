package ${packageName}.dao.impl;

import ${packageName}.dao.${className}Dao;
import ${packageName}.dao.mapper.${className}RowMapper;
import ${packageName}.model.${className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ${className}DaoImpl implements ${className}Dao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertObject(${className} ${className?uncap_first}) {
        String sql_insert = "INSERT INTO ${tableName} ";
        String sql_value = " VALUES ";
        String sql1="", sql2="";
        List<Object> objectList = new ArrayList<>();
        <#list propertyList as property>
        if (Objects.nonNull(${className?uncap_first}.get${property.propertyName?cap_first}())) {
            sql1 += ", ${property.columnName}";
            sql2 += ", ?";
            objectList.add(${className?uncap_first}.get${property.propertyName?cap_first}());
        }
        </#list>
        sql1 = sql1.replaceFirst(",", "");
        sql2 = sql2.replaceFirst(",", "");
        String sql = sql_insert + "(" + sql1 + ")" + sql_value + "(" + sql2 + ")";
        jdbcTemplate.update(sql, objectList.toArray());

//        final KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            return ps;
//        }, keyHolder);
//        ${className?uncap_first}.setId(keyHolder.getKey().intValue());

    }

    @Override
    public void updateObject(${className} ${className?uncap_first}) {
        String sql_update = "UPDATE ${tableName} SET ";
        String sql1 = "";
        List<Object> objectList = new ArrayList<>();
        <#list propertyList as property>
        if (Objects.nonNull(${className?uncap_first}.get${property.propertyName?cap_first}())) {
            sql1 += ", ${property.columnName} = ? ";
            objectList.add(${className?uncap_first}.get${property.propertyName?cap_first}());
        }
        </#list>
        String sql_where = " WHERE 1=1 ";
        <#list propertyList as property>
        <#if property.primaryKey >
        sql_where += "and ${property.columnName}=? ";
        objectList.add(${className?uncap_first}.get${property.propertyName?cap_first}());
        </#if>
        </#list>
        sql1 = sql1.replaceFirst(",", "");
        String sql = sql_update + sql1 + sql_where;
        jdbcTemplate.update(sql, objectList.toArray());

    }

    @Override
    public void deleteObject(${className} ${className?uncap_first}) {
        String sql = "DELETE FROM ${tableName} WHERE 1=1 ";
        List<Object> objectList = new ArrayList<>();
        <#list propertyList as property>
        if (Objects.nonNull(${className?uncap_first}.get${property.propertyName?cap_first}())) {
            sql += "and ${property.columnName} = ? ";
            objectList.add(${className?uncap_first}.get${property.propertyName?cap_first}());
        }
        </#list>
        jdbcTemplate.update(sql, objectList.toArray());
    }

    @Override
    public ${className} queryForObject(${className} ${className?uncap_first}) {
        String sql = "SELECT * FROM ${tableName} WHERE 1=1 ";
        List<Object> objectList = new ArrayList<>();
        <#list propertyList as property>
        <#if property.primaryKey >
        sql += "and ${property.columnName}=? ";
        objectList.add(${className?uncap_first}.get${property.propertyName?cap_first}());
        </#if>
        </#list>
        return jdbcTemplate.queryForObject(sql, objectList.toArray(), new ${className}RowMapper());
    }

    @Override
    public List<${className}> queryForList(${className} ${className?uncap_first}) {
        String sql = "SELECT * FROM ${tableName} WHERE 1=1 ";
        List<Object> objectList = new ArrayList<>();
        <#list propertyList as property>
        <#if property.primaryKey >
        sql += "and ${property.columnName}=? ";
        objectList.add(${className?uncap_first}.get${property.propertyName?cap_first}());
        </#if>
        </#list>
        return jdbcTemplate.query(sql, objectList.toArray(), new ${className}RowMapper());
    }

    @Override
    public Integer count() {
        String sql = "SELECT count(1) FROM ${tableName}";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Integer count(${className} ${className?uncap_first}) {
        String sql = "SELECT count(1) FROM ${tableName} WHERE 1=1 ";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
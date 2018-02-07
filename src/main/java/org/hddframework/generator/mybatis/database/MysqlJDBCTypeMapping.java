package org.hddframework.generator.mybatis.database;

/**
 * Created by SongWei on 06/02/2018.
 */
public class MysqlJDBCTypeMapping extends JDBCTypeMapping {

    @Override
    public String getJavaType(String sqlTypeName) {
        assert sqlTypeName != null;
        sqlTypeName = sqlTypeName.toUpperCase();
        if ("VARCHAR".equals(sqlTypeName)) return "String";
        if ("CHAR".equals(sqlTypeName)) return "String";
        if ("TEXT".equals(sqlTypeName)) return "String";
        if ("BLOB".equals(sqlTypeName)) return "byte[]";
        if ("INT".equals(sqlTypeName)) return "Long";
        if ("INTEGER".equals(sqlTypeName)) return "Long";
        if ("TINYINT".equals(sqlTypeName)) return "Integer";
        if ("SMALLINT".equals(sqlTypeName)) return "Integer";
        if ("MEDIUMINT".equals(sqlTypeName)) return "Integer";
        if ("BIT".equals(sqlTypeName)) return "Boolean";
        if ("FLOAT".equals(sqlTypeName)) return "Float";
        if ("DOUBLE".equals(sqlTypeName)) return "Double";
        if ("BIGINT".equals(sqlTypeName)) return "BigInteger";
        if ("DECIMAL".equals(sqlTypeName)) return "BigDecimal";
        if ("BOOLEAN".equals(sqlTypeName)) return "Integer";
        if ("ID".equals(sqlTypeName)) return "Long";
        if ("DATE".equals(sqlTypeName)) return "Date";
        if ("TIME".equals(sqlTypeName)) return "Time";
        if ("DATETIME".equals(sqlTypeName)) return "Timestamp";
        if ("TIMESTAMP".equals(sqlTypeName)) return "Timestamp";
        if ("YEAR".equals(sqlTypeName)) return "Date";
        if ("JSON".equals(sqlTypeName)) return "JSONObject";
        if ("JSONB".equals(sqlTypeName)) return "JSONObject";
        return "String";
    }


}

package org.hddframework.generator.mybatis.database;

/**
 * Created by SongWei on 10/01/2018.
 */
public abstract class JDBCTypeMapping {

    public static String getJavaType(String databaseType) {
        assert databaseType != null;
        databaseType = databaseType.toUpperCase();
        if ("VARCHAR".equals(databaseType)) return "String";
        if ("CHAR".equals(databaseType)) return "String";
        if ("TEXT".equals(databaseType)) return "String";
        if ("BLOB".equals(databaseType)) return "byte[]";
        if ("INT".equals(databaseType)) return "Long";
        if ("INTEGER".equals(databaseType)) return "Long";
        if ("TINYINT".equals(databaseType)) return "Integer";
        if ("SMALLINT".equals(databaseType)) return "Integer";
        if ("MEDIUMINT".equals(databaseType)) return "Integer";
        if ("BIT".equals(databaseType)) return "Boolean";
        if ("FLOAT".equals(databaseType)) return "Float";
        if ("DOUBLE".equals(databaseType)) return "Double";
        if ("BIGINT".equals(databaseType)) return "BigInteger";
        if ("DECIMAL".equals(databaseType)) return "BigDecimal";
        if ("BOOLEAN".equals(databaseType)) return "Integer";
        if ("ID".equals(databaseType)) return "Long";
        if ("DATE".equals(databaseType)) return "Date";
        if ("TIME".equals(databaseType)) return "Time";
        if ("DATETIME".equals(databaseType)) return "Timestamp";
        if ("TIMESTAMP".equals(databaseType)) return "Timestamp";
        if ("YEAR".equals(databaseType)) return "Date";
        if ("JSON".equals(databaseType)) return "JSONObject";
        if ("JSONB".equals(databaseType)) return "JSONObject";
        return "String";
    }

}

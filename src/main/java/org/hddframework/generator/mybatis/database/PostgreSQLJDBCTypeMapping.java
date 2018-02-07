package org.hddframework.generator.mybatis.database;

/**
 * Created by SongWei on 06/02/2018.
 */
public class PostgreSQLJDBCTypeMapping extends JDBCTypeMapping {

    @Override
    public String getJavaType(String sqlTypeName) {
        assert sqlTypeName != null;
        sqlTypeName = sqlTypeName.toLowerCase();
        if ("int8".equals(sqlTypeName)) return "Long";
        if ("bigint".equals(sqlTypeName)) return "Long";
        if ("serial8".equals(sqlTypeName)) return "Long";
        if ("bigserial".equals(sqlTypeName)) return "Long";
        if ("bit".equals(sqlTypeName)) return "String";
        if ("varbit".equals(sqlTypeName)) return "String";
        if ("boolean".equals(sqlTypeName)) return "Boolean";
        if ("bool".equals(sqlTypeName)) return "Boolean";
        if ("character".equals(sqlTypeName)) return "String";
        if ("char".equals(sqlTypeName)) return "String";
        if ("varchar".equals(sqlTypeName)) return "String";
        if ("date".equals(sqlTypeName)) return "Date";
        if ("float8".equals(sqlTypeName)) return "BigDecimal";
        if ("double".equals(sqlTypeName)) return "BigDecimal";
        if ("integer".equals(sqlTypeName)) return "Integer";
        if ("int4".equals(sqlTypeName)) return "Integer";
        if ("int".equals(sqlTypeName)) return "Integer";
        if ("json".equals(sqlTypeName)) return "JSONObject";
        if ("jsonb".equals(sqlTypeName)) return "JSONObject";
        if ("numeric".equals(sqlTypeName)) return "BigDecimal";
        if ("pg_lsn".equals(sqlTypeName)) return "BigInteger";
        if ("real".equals(sqlTypeName)) return "BigDecimal";
        if ("float4".equals(sqlTypeName)) return "BigDecimal";
        if ("smallint".equals(sqlTypeName)) return "Integer";
        if ("int2".equals(sqlTypeName)) return "Integer";
        if ("smallserial".equals(sqlTypeName)) return "Integer";
        if ("serial2".equals(sqlTypeName)) return "Integer";
        if ("serial".equals(sqlTypeName)) return "Integer";
        if ("serial4".equals(sqlTypeName)) return "Integer";
        if ("text".equals(sqlTypeName)) return "String";
        if ("time".equals(sqlTypeName)) return "Time";
        if ("timestamp".equals(sqlTypeName)) return "Timestamp";
        if ("uuid".equals(sqlTypeName)) return "String";

        if ("_int8".equals(sqlTypeName)) return "Long[]";
        if ("_bigint".equals(sqlTypeName)) return "Long[]";
        if ("_serial8".equals(sqlTypeName)) return "Long[]";
        if ("_bigserial".equals(sqlTypeName)) return "Long[]";
        if ("_bit".equals(sqlTypeName)) return "String[]";
        if ("_varbit".equals(sqlTypeName)) return "String[]";
        if ("_boolean".equals(sqlTypeName)) return "Boolean[]";
        if ("_bool".equals(sqlTypeName)) return "Boolean[]";
        if ("_character".equals(sqlTypeName)) return "String[]";
        if ("_char".equals(sqlTypeName)) return "String[]";
        if ("_varchar".equals(sqlTypeName)) return "String[]";
        if ("_date".equals(sqlTypeName)) return "Date[]";
        if ("_float8".equals(sqlTypeName)) return "BigDecimal[]";
        if ("_double".equals(sqlTypeName)) return "BigDecimal[]";
        if ("_integer".equals(sqlTypeName)) return "Integer[]";
        if ("_int4".equals(sqlTypeName)) return "Integer[]";
        if ("_int".equals(sqlTypeName)) return "Integer[]";
        if ("_json".equals(sqlTypeName)) return "JSONObject[]";
        if ("_jsonb".equals(sqlTypeName)) return "JSONObject[]";
        if ("_numeric".equals(sqlTypeName)) return "BigDecimal[]";
        if ("_pg_lsn".equals(sqlTypeName)) return "BigInteger[]";
        if ("_real".equals(sqlTypeName)) return "BigDecimal[]";
        if ("_float4".equals(sqlTypeName)) return "BigDecimal[]";
        if ("_smallint".equals(sqlTypeName)) return "Integer[]";
        if ("_int2".equals(sqlTypeName)) return "Integer[]";
        if ("_smallserial".equals(sqlTypeName)) return "Integer[]";
        if ("_serial2".equals(sqlTypeName)) return "Integer[]";
        if ("_serial".equals(sqlTypeName)) return "Integer[]";
        if ("_serial4".equals(sqlTypeName)) return "Integer[]";
        if ("_text".equals(sqlTypeName)) return "String[]";
        if ("_time".equals(sqlTypeName)) return "Time[]";
        if ("_timestamp".equals(sqlTypeName)) return "Timestamp[]";
        if ("_uuid".equals(sqlTypeName)) return "String[]";

        return "String";
    }

}


/**
 * box	 	rectangular box on a plane
 * bytea	 	binary data ("byte array")
 * cidr	 	IPv4 or IPv6 network address
 * circle	 	circle on a plane
 * inet	 	IPv4 or IPv6 host address
 * interval [ fields ] [ (p) ]	 	time span
 * line	 	infinite line on a plane
 * lseg	 	line segment on a plane
 * macaddr	 	MAC (Media Access Control) address
 * money	 	currency amount
 * path	 	geometric path on a plane
 * pg_lsn	 	PostgreSQL Log Sequence Number
 * point	 	geometric point on a plane
 * polygon	 	closed geometric path on a plane
 * tsquery	 	text search query
 * tsvector	 	text search document
 * txid_snapshot	 	user-level transaction ID snapshot
 * uuid	 	universally unique identifier
 * xml
 */

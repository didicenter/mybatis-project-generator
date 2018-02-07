package org.hddframework.generator.mybatis.database;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SongWei on 10/01/2018.
 */
public class DatabaseType {

    private final String type;

    private DatabaseType(String type) {
        this.type = type;
    }

    public static List<DatabaseType> valueOf() {
        return Arrays.asList(MYSQL, POSTGRESQL, SQL_SERVER, ORACLE, DB2, SYBASE, H2, DERBY, SQLITE, SQLITE3, HSQLDB, HIVE, REDSHIFT, INFORMIX, TERADATA, PHOENIX);
    }

    public static DatabaseType MYSQL = new DatabaseType("mysql");
    public static DatabaseType POSTGRESQL = new DatabaseType("postgresql");
    public static DatabaseType SQL_SERVER = new DatabaseType("sqlserver");
    public static DatabaseType ORACLE = new DatabaseType("oracle");
    public static DatabaseType DB2 = new DatabaseType("db2");
    public static DatabaseType SYBASE = new DatabaseType("sybase");
    public static DatabaseType H2 = new DatabaseType("h2");
    public static DatabaseType DERBY = new DatabaseType("derby");
    public static DatabaseType SQLITE = new DatabaseType("sqlite");
    public static DatabaseType SQLITE3 = new DatabaseType("sqlite3");
    public static DatabaseType HSQLDB = new DatabaseType("hsqldb");
    public static DatabaseType HIVE = new DatabaseType("hive");
    public static DatabaseType REDSHIFT = new DatabaseType("redshift");
    public static DatabaseType INFORMIX = new DatabaseType("informix-sqli");

    public static DatabaseType TERADATA = new DatabaseType("teradata");
    public static DatabaseType PHOENIX = new DatabaseType("phoenix");

    public String getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object obj) {
        if (DatabaseType.class.isInstance(obj)) {
            return ((DatabaseType) obj).getType().equals(this.type);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }
}

package org.hddframework.generator.mybatis.database;

/**
 * Created by SongWei on 06/02/2018.
 */
public class JDBCTypeMappingFactory {

    public static JDBCTypeMapping getJDBCTypeMapping(DatabaseType databaseType) {
        if (databaseType.equals(DatabaseType.POSTGRESQL)) {
            return new PostgreSQLJDBCTypeMapping();
        }
        if (databaseType.equals(DatabaseType.MYSQL)) {
            return new MysqlJDBCTypeMapping();
        }
        return null;
    }

}

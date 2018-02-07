package org.hddframework.generator.mybatis.database;

/**
 * Created by SongWei on 10/01/2018.
 */
public abstract class JDBCTypeMapping {

    public abstract String getJavaType(String sqlTypeName);

}

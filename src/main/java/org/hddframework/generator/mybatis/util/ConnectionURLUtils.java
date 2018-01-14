package org.hddframework.generator.mybatis.util;

import org.apache.commons.lang3.StringUtils;
import org.hddframework.generator.mybatis.database.DatabaseType;
import org.hddframework.generator.mybatis.exception.UnknownDatabaseException;
import org.hddframework.generator.mybatis.exception.UnsupportedDatabaseException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SongWei on 10/01/2018.
 */
public class ConnectionURLUtils {

    //"jdbc:sqlserver://127.0.0.1:1433;databasename=qqhrlgzysyweb"

    //"jdbc:oracle:thin:@192.168.8.1:1521:yuewei"
    //"jdbc:db2://localhost:50000/content"
    //"jdbc:postgresql://localhost:5432/testdb"
    //"jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8"
    //"jdbc:sybase:Tds:192.168.0.168:5000/master"
    //"jdbc:h2:tcp://localhost/mem:testmemdb";
    //"jdbc:derby://localhost:1527/ij_cmd_test_db"
    //"jdbc:sqlite:test.db"
    //"jdbc:hsqldb:hsql://localhost:port/database"
    //"jdbc:redshift://endpoint:port/database"

    //"jdbc:hive://localhost:10002/default"
    //"jdbc:phoenix:master,node1,node2:2181"

    //"jdbc:informix-sqli://127.0.0.1:1533/testDB:INFORMIXSERVER=myserver;user=testuser;password=testpassword"
    //"jdbc:teradata://gwhost:port/DatabaseServerName"

    //"jdbc:odbc:driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB="+(你的DBF数据表的全路径名，包括文件名);
    //"jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+"e://student.mdb ";

    private static final Pattern databaseTypeNamePattern = Pattern.compile("(?<=:)(.+?)(?=:)");

    public static DatabaseType getDatabaseTypeFromUrl(String url) {
        Matcher matcher = databaseTypeNamePattern.matcher(url);
        if (matcher.find()) {
            String dbType = matcher.group(0);
            List<DatabaseType> databaseTypeList = DatabaseType.valueOf();
            for (DatabaseType databaseType : databaseTypeList) {
                if (databaseType.getType().equalsIgnoreCase(dbType)) {
                    return databaseType;
                }
            }
            throw new UnsupportedDatabaseException(String.format("%s is not supported", dbType));
        }
        throw new UnknownDatabaseException(String.format("%s is an unknown database", url));
    }

    public static String getCurrentDatabaseFromUrl(String url) {
        DatabaseType type = getDatabaseTypeFromUrl(url);
        if (DatabaseType.SQL_SERVER.equals(type)) {
            int index = StringUtils.indexOfIgnoreCase(url, "databasename=");
            if (index > 0) {
                String databaseInfo = StringUtils.substring(url, (index + 13));
                return databaseInfo.split("\\?")[0];
            }
        }
        if (DatabaseType.ORACLE.equals(type)) {
            int index = StringUtils.lastIndexOf(url, ":");
            if (index > 0) {
                String databaseInfo = StringUtils.substring(url, (index + 1));
                return databaseInfo.split("\\?")[0];
            }
        }
        if (DatabaseType.DB2.equals(type) || DatabaseType.MYSQL.equals(type) || DatabaseType.POSTGRESQL.equals(type) ||
                DatabaseType.SYBASE.equals(type) || DatabaseType.DERBY.equals(type) || DatabaseType.HSQLDB.equals(type) ||
                DatabaseType.REDSHIFT.equals(type) || DatabaseType.HIVE.equals(type) || DatabaseType.INFORMIX.equals(type) ||
                DatabaseType.TERADATA.equals(type)) {
            int index = StringUtils.lastIndexOf(url, "/");
            String databaseInfo = StringUtils.substring(url, (index + 1));
            return databaseInfo.split("\\?")[0];
        }

        return null;

    }


}

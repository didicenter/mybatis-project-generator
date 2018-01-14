package hdd.framwork.mybatis.generator;

import com.google.common.base.CaseFormat;
import hdd.framwork.mybatis.generator.core.mybatis.ConnectionURLUtils;
import hdd.framwork.mybatis.generator.core.mybatis.model.ClassModel;
import hdd.framwork.mybatis.generator.core.mybatis.model.PropertyModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MybatisGeneratorApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://rm-2ze40fc78k7alk449o.mysql.rds.aliyuncs.com:3306/lsj_test?useUnicode=true&characterEncoding=utf8";
    private static final String userName = "xhclsj";
    private static final String password = "LSJ@xhc!";

    @Test
    public void test1() throws Exception {


        Class.forName(driverClassName);
        Connection connection = DriverManager.getConnection(this.url, this.userName, this.password);
//		String currentSchema = ConnectionURLUtils.getCurrentSchemaFromUrl(this.url);
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println(connection.getSchema());
        System.out.println(connection.getCatalog());
        System.out.println(connection.getClientInfo());

//		ResultSet  resultSet = databaseMetaData.getSchemas();
//		while(resultSet.next()){
//
//			ResultSetMetaData data = resultSet.getMetaData();
//
//			System.out.println(data);
//
//		}
//		databaseMetaData.getCatalogs();


        ResultSet tableResultSet = databaseMetaData.getTables(connection.getCatalog(), connection.getSchema(), "%%", new String[]{"TABLE"});
        int i = 1;
        while (tableResultSet.next()) {
            String tableName = tableResultSet.getString("TABLE_NAME");
            System.out.println((i++) + "=" + "tableName: " + tableName);

//			ResultSetMetaData tableMetaData = tableResultSet.getMetaData();
            ResultSet columnSet = databaseMetaData.getColumns(connection.getCatalog(), connection.getSchema(), tableName, "%");
//
//			int  columnSet000 = tableMetaData.getColumnCount();
//			if(tableName.equalsIgnoreCase("user_disclosure_collections")){
//				System.out.println("~~~~~~~~~~~");
//			}
            while (columnSet.next()) {
                if (tableName.equalsIgnoreCase("user_disclosure_collections")) {
                    String columnName = columnSet.getString("COLUMN_NAME");
                    System.out.println("columnName: "+ columnName);
                }
            }
//			columnSet.close();

        }

        tableResultSet.close();

        connection.close();

    }

}

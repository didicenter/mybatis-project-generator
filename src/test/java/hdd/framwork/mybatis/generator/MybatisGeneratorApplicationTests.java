package hdd.framwork.mybatis.generator;

import com.google.common.base.CaseFormat;
import org.hddframework.generator.mybatis.model.PropertyModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MybatisGeneratorApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

//    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
//    private static final String url = "jdbc:mysql://rm-2ze40fc78k7alk449o.mysql.rds.aliyuncs.com:3306/lsj_test?useUnicode=true&characterEncoding=utf8";
//    private static final String userName = "xhclsj";
//    private static final String password = "LSJ@xhc!";

    private static final String driverClassName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://rm-2zey95rn7ib6heqmp.pg.rds.aliyuncs.com:3433/dev_shop";
    private static final String userName = "xhc_test";
    private static final String password = "Xhc_test";

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


        ResultSet tableResultSet = databaseMetaData.getTables(connection.getCatalog(), connection.getSchema(), "%orders_goods%", new String[]{"TABLE"});
        int i = 1;
        while (tableResultSet.next()) {
            String tableName = tableResultSet.getString("TABLE_NAME");
            System.out.println((i++) + "=" + "tableName: " + tableName);

            ResultSet primaryKeysSet = databaseMetaData.getPrimaryKeys(connection.getCatalog(), connection.getSchema(), tableName);
            ResultSet columnSet = databaseMetaData.getColumns(connection.getCatalog(), connection.getSchema(), tableName, "%");

            while (primaryKeysSet.next()) {
                System.err.println("****** Comment ******");
                System.err.println("TABLE_NAME : " + primaryKeysSet.getString("TABLE_NAME"));
                System.err.println("COLUMN_NAME : " + primaryKeysSet.getString("COLUMN_NAME"));
//                System.err.println("TABLE_CAT : " + primaryKeysSet.getString(1));
//                System.err.println("TABLE_SCHEM: " + primaryKeysSet.getObject(2));
//                System.err.println("TABLE_NAME : " + primaryKeysSet.getObject(3));
//                System.err.println("COLUMN_NAME: " + primaryKeysSet.getObject(4));
//                System.err.println("KEY_SEQ : " + primaryKeysSet.getObject(5));
//                System.err.println("PK_NAME : " + primaryKeysSet.getObject(6));
                System.err.println("****** ******* ******");
            }

        }

        Statement statement = connection.createStatement();

        ResultSet rs11 = statement.executeQuery("SELECT * from activity where a_id = 366");
        while (rs11.next()) {
//            Array array = rs11.getArray("a_price");
            Array array = rs11.getArray("a_free_gid");
            Object data = array.getArray();
            if (Objects.nonNull(data)) {

            }
            BigDecimal[] a = (BigDecimal[]) data;

            System.out.println(Arrays.toString(a));
        }


        tableResultSet.close();

        connection.close();

        PropertyModel propertyModel = new PropertyModel();
        propertyModel.isPrimaryKey();

    }

}

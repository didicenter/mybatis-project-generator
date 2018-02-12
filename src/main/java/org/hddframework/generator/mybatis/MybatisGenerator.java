package org.hddframework.generator.mybatis;

import com.google.common.base.CaseFormat;
import org.hddframework.generator.mybatis.configuration.MBGConfiguration;
import org.hddframework.generator.mybatis.database.JDBCTypeMapping;
import org.hddframework.generator.mybatis.database.JDBCTypeMappingFactory;
import org.hddframework.generator.mybatis.interal.*;
import org.hddframework.generator.mybatis.interal.fastjson.*;
import org.hddframework.generator.mybatis.interal.gson.*;
import org.hddframework.generator.mybatis.interal.mvc.*;
import org.hddframework.generator.mybatis.model.*;
import org.hddframework.generator.mybatis.util.ConnectionURLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SongWei on 09/01/2018.
 */
public class MybatisGenerator {
    private static final Logger logger = LoggerFactory.getLogger(MybatisGenerator.class.getSimpleName());

    private final MBGConfiguration mbgConfiguration;

    public MybatisGenerator(MBGConfiguration mbgConfiguration) {
        this.mbgConfiguration = mbgConfiguration;
        this.projectRootPath = mbgConfiguration.getProjectConfiguration().getRootAbsolutePath();
        this.rootPackageName = mbgConfiguration.getProjectConfiguration().getRootPackageName();
        this.url = mbgConfiguration.getDatabaseConfiguration().getUrl();
        this.driverClassName = mbgConfiguration.getDatabaseConfiguration().getDriverClassName();
        this.userName = mbgConfiguration.getDatabaseConfiguration().getUserName();
        this.password = mbgConfiguration.getDatabaseConfiguration().getPassword();
        this.tableNamePattern = mbgConfiguration.getDatabaseConfiguration().getTableNamePattern();
        this.excludeTableList = Arrays.asList(mbgConfiguration.getDatabaseConfiguration().getExcludeTable());
        this.mapUnderscoreToCamelCase = mbgConfiguration.getGenerationConfiguration().isMapUnderscoreToCamelCase();

        logger.info("user configuration: " + mbgConfiguration);
    }

    private String projectRootPath;
    private String rootPackageName;
    private String url;
    private String driverClassName;
    private String userName;
    private String password = "";
    private String tableNamePattern = "";
    private List<String> excludeTableList;

    private Boolean mapUnderscoreToCamelCase = true;

    private List<DataModel> getDataModelList() {
        List<String> newExcludeTableList = excludeTableList.stream().map(String::toUpperCase).collect(Collectors.toList());
        try {
            List<DataModel> dataModelList = new ArrayList<>();
            Class.forName(this.driverClassName);
            Connection connection = DriverManager.getConnection(this.url, this.userName, this.password);

            JDBCTypeMapping jdbcTypeMapping = JDBCTypeMappingFactory.getJDBCTypeMapping(ConnectionURLUtils.getDatabaseTypeFromUrl(this.url));

            String currentSchema = connection.getSchema();
            String currentCatalog = connection.getCatalog();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet tableSet = databaseMetaData.getTables(currentCatalog, currentSchema, "%" + this.tableNamePattern + "%", new String[]{"TABLE"});
            while (tableSet.next()) {
                String tableName = tableSet.getString("TABLE_NAME");
                if (newExcludeTableList.contains(tableName.toUpperCase())) {
                    continue;
                }
                logger.info("start to read " + tableName + " table information...");
                ClassModel dataModel = new ClassModel();
                dataModel.setTableName(tableName);
                dataModel.setClassName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName));
                dataModel.setPackageName(this.rootPackageName);
                ResultSet columnSet = databaseMetaData.getColumns(currentCatalog, currentSchema, tableName, "%");
                List<PropertyModel> propertyList = new ArrayList<>();
                while (columnSet.next()) {
                    String columnName = columnSet.getString("COLUMN_NAME");
                    String sqlType = columnSet.getString("DATA_TYPE");
                    String sqlTypeName = columnSet.getString("TYPE_NAME");

                    PropertyModel propertyDataModel = new PropertyModel();

                    propertyDataModel.setColumnType(sqlTypeName);
                    propertyDataModel.setColumnName(columnName);
                    String propertyName = mapUnderscoreToCamelCase ? CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName) : columnName;
                    propertyDataModel.setPropertyName(propertyName);
                    propertyDataModel.setPropertyType(jdbcTypeMapping.getJavaType(sqlTypeName));

                    propertyList.add(propertyDataModel);
                }
                columnSet.close();
                dataModel.setPropertyList(propertyList);
                dataModelList.add(dataModel);
            }
            tableSet.close();
            connection.close();
            return dataModelList;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DatabaseModel getDatabaseModel() {
        DatabaseModel dbModel = new DatabaseModel();

        dbModel.setDriverClassName(this.driverClassName);
        dbModel.setPassword(this.password);
        dbModel.setUrl(this.url);
        dbModel.setUserName(this.userName);

        return dbModel;
    }

    private BuildModel getBuildModel() {
        BuildModel buildModel = new BuildModel();
        buildModel.setArtifact(mbgConfiguration.getProjectConfiguration().getProjectName());
        buildModel.setGroup(this.rootPackageName);
        return buildModel;
    }

    public void generateFile() {

        Generator controllerGenerator = new ControllerGenerator(mbgConfiguration);
        Generator mapperGenerator = new MapperGenerator(mbgConfiguration);
        Generator mapperXmlGenerator = new MapperXmlGenerator(mbgConfiguration);
        Generator modelExampleGenerator = new ModelExampleGenerator(mbgConfiguration);
        Generator modelGenerator = new ModelGenerator(mbgConfiguration);
        Generator serviceGenerator = new ServiceGenerator(mbgConfiguration);
        Generator serviceImplGenerator = new ServiceImplGenerator(mbgConfiguration);

        List<DataModel> dataModelList = getDataModelList();

        for (DataModel dataModel : dataModelList) {
            if (mbgConfiguration.getGeneratorConfiguration().isGenerateController()) {
                logger.info("start to generate " + dataModel.getModelName() + " controller...");
                controllerGenerator.generate(dataModel);
            }
            if (mbgConfiguration.getGeneratorConfiguration().isGenerateService()) {
                logger.info("start to generate " + dataModel.getModelName() + " service...");
                serviceGenerator.generate(dataModel);
            }
            if (mbgConfiguration.getGeneratorConfiguration().isGenerateServiceImpl()) {
                logger.info("start to generate " + dataModel.getModelName() + " serviceImpl...");
                serviceImplGenerator.generate(dataModel);
            }
            if (mbgConfiguration.getGeneratorConfiguration().isGenerateMapper()) {
                logger.info("start to generate " + dataModel.getModelName() + " mapper...");
                mapperGenerator.generate(dataModel);
            }
            if (mbgConfiguration.getGeneratorConfiguration().isGenerateMapperXml()) {
                logger.info("start to generate " + dataModel.getModelName() + " mapperXml...");
                mapperXmlGenerator.generate(dataModel);
            }
            if (mbgConfiguration.getGeneratorConfiguration().isGenerateModel()) {
                logger.info("start to generate " + dataModel.getModelName() + " model...");
                modelGenerator.generate(dataModel);
            }
            if (mbgConfiguration.getGeneratorConfiguration().isGenerateModelExample()) {
                logger.info("start to generate " + dataModel.getModelName() + " modelExample...");
                modelExampleGenerator.generate(dataModel);
            }
        }

        List<String> dataTypeList = new ArrayList<>();
        for (DataModel dataModel : dataModelList) {
            if (dataModel instanceof ClassModel) {
                ClassModel classModel = (ClassModel) dataModel;
                dataTypeList.addAll(classModel.getSqlTypeList());
            }
        }

        PluginModel pluginModel = new PluginModel();
        if (dataTypeList.stream().anyMatch(item -> "json".equalsIgnoreCase(item))) {
            logger.info("start to generate json handler...");
            Generator jsonTypeHandlerGenerator = new JSONTypeHandlerGenerator(mbgConfiguration);
            jsonTypeHandlerGenerator.generate(pluginModel);
        }
        if (dataTypeList.stream().anyMatch(item -> "jsonb".equalsIgnoreCase(item))) {
            logger.info("start to generate jsonb handler...");
            Generator jsonBinaryTypeHandlerGenerator = new JSONBinaryTypeHandlerGenerator(mbgConfiguration);
            jsonBinaryTypeHandlerGenerator.generate(pluginModel);
        }
        if (dataTypeList.stream().anyMatch(item -> item.contains("_") || item.contains("[]"))) {
            logger.info("start to generate array handler...");
            Generator arrayTypeHandlerGenerator = new ArrayTypeHandlerGenerator(mbgConfiguration);
            arrayTypeHandlerGenerator.generate(pluginModel);
        }

        if (mbgConfiguration.getGeneratorConfiguration().isGenerateMybatisCriterion()) {
            logger.info("start to generate mybatis criterion...");
            Generator pluginGenerator = new CriterionGenerator(mbgConfiguration);
            pluginGenerator.generate(pluginModel);
        }

        if (mbgConfiguration.getGeneratorConfiguration().isGenerateDataSourceConfigurationFile()) {
            logger.info("start to generate datasource configuration...");
            Generator dataSourceConfigurationGenerator = new DataSourceConfigurationGenerator(mbgConfiguration);
            DataSourceModel dataSourceModel = new DataSourceModel();
            dataSourceModel.setMybatisXmlDirectoryName(mbgConfiguration.getProjectConfiguration().getMybatisXmlDirectoryName());
            dataSourceModel.setPackageName(this.rootPackageName);
            dataSourceConfigurationGenerator.generate(dataSourceModel);
        }

        if (mbgConfiguration.getGeneratorConfiguration().isGenerateProjectPropertiesFile()) {
            logger.info("start to generate project properties...");
            Generator projectPropertiesFileGenerator = new ProjectPropertiesFileGenerator(mbgConfiguration);
            projectPropertiesFileGenerator.generate(getDatabaseModel());
        }


        ClassModel classModel = new ClassModel();
        classModel.setClassName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, mbgConfiguration.getProjectConfiguration().getProjectName()));
        classModel.setPackageName(this.rootPackageName);

        if (mbgConfiguration.getGeneratorConfiguration().isGenerateBuildFile()) {
            logger.info("start to generate build file...");
            Generator buildFileGenerator = new BuildFileGenerator(mbgConfiguration);
            buildFileGenerator.generate(getBuildModel());
        }
        if (mbgConfiguration.getGeneratorConfiguration().isGenerateStartupFile()) {
            logger.info("start to generate startup file include main method...");
            Generator startupFileGenerator = new StartupFileGenerator(mbgConfiguration);
            startupFileGenerator.generate(classModel);
        }
        if (mbgConfiguration.getGeneratorConfiguration().isGenerateExceptionHandler()) {
            logger.info("start to generate exception handler...");
            Generator exceptionHandlerConfigurationGenerator = new ExceptionHandlerConfigurationGenerator(mbgConfiguration);
            exceptionHandlerConfigurationGenerator.generate(classModel);
        }
        if (mbgConfiguration.getGeneratorConfiguration().isGenerateWebMvcConfiguration()) {
            logger.info("start to generate spring webmvc configuration...");
            Generator webMvcConfigurationGenerator = new WebMvcConfigurationGenerator(mbgConfiguration);
            webMvcConfigurationGenerator.generate(classModel);
        }


//        if (mbgConfiguration.getGeneratorConfiguration().isGenerateWebMvcConfiguration()) {
        logger.info("start to generate spring webmvc configuration...");
        Generator redisCachingConfigurationGenerator = new RedisCachingConfigurationGenerator(mbgConfiguration);
        redisCachingConfigurationGenerator.generate(classModel);

        Generator systemConstantGenerator = new SystemConstantGenerator(mbgConfiguration);
        systemConstantGenerator.generate(classModel);

        Generator threadPoolConfigurationGenerator = new ThreadPoolConfigurationGenerator(mbgConfiguration);
        threadPoolConfigurationGenerator.generate(classModel);

        ///////////mvc start ////////////
        Generator jsonReturnValueHandlerGenerator = new JsonReturnValueHandlerGenerator(mbgConfiguration);
        jsonReturnValueHandlerGenerator.generate(classModel);

        Generator xmlReturnValueHandlerGenerator = new XmlReturnValueHandlerGenerator(mbgConfiguration);
        xmlReturnValueHandlerGenerator.generate(classModel);

        Generator plainValueGenerator = new PlainValueGenerator(mbgConfiguration);
        plainValueGenerator.generate(classModel);

        Generator resultDataGenerator = new ResultDataGenerator(mbgConfiguration);
        resultDataGenerator.generate(classModel);

        Generator wrapResultGenerator = new WrapResultGenerator(mbgConfiguration);
        wrapResultGenerator.generate(classModel);

        Generator xStreamUtilsGenerator = new XStreamUtilsGenerator(mbgConfiguration);
        xStreamUtilsGenerator.generate(classModel);
        ///////////mvc end ////////////

        ///////////fastjson start ////////////
        Generator fastJsonUtilsGenerator = new FastJsonUtilsGenerator(mbgConfiguration);
        fastJsonUtilsGenerator.generate(classModel);

        Generator bigDecimalObjectSerializerGenerator = new BigDecimalObjectSerializerGenerator(mbgConfiguration);
        bigDecimalObjectSerializerGenerator.generate(classModel);

        Generator dateObjectSerializerGenerator = new DateObjectSerializerGenerator(mbgConfiguration);
        dateObjectSerializerGenerator.generate(classModel);

        Generator doubleObjectSerializerGenerator = new DoubleObjectSerializerGenerator(mbgConfiguration);
        doubleObjectSerializerGenerator.generate(classModel);

        Generator floatObjectSerializerGenerator = new FloatObjectSerializerGenerator(mbgConfiguration);
        floatObjectSerializerGenerator.generate(classModel);

        Generator sqlDateObjectSerializerGenerator = new SqlDateObjectSerializerGenerator(mbgConfiguration);
        sqlDateObjectSerializerGenerator.generate(classModel);

        Generator timestampObjectSerializerGenerator = new TimestampObjectSerializerGenerator(mbgConfiguration);
        timestampObjectSerializerGenerator.generate(classModel);

        Generator utilDateObjectSerializerGenerator = new UtilDateObjectSerializerGenerator(mbgConfiguration);
        utilDateObjectSerializerGenerator.generate(classModel);
        ///////////fastjson end ////////////

        ///////////gson start ////////////
        Generator gsonJsonUtilsGenerator = new GsonJsonUtilsGenerator(mbgConfiguration);
        gsonJsonUtilsGenerator.generate(classModel);

        Generator jsonSerializer4BigDecimalGenerator = new JsonSerializer4BigDecimalGenerator(mbgConfiguration);
        jsonSerializer4BigDecimalGenerator.generate(classModel);

        Generator jsonSerializer4DateGenerator = new JsonSerializer4DateGenerator(mbgConfiguration);
        jsonSerializer4DateGenerator.generate(classModel);

        Generator jsonSerializer4DoubleGenerator = new JsonSerializer4DoubleGenerator(mbgConfiguration);
        jsonSerializer4DoubleGenerator.generate(classModel);

        Generator jsonSerializer4FloatGenerator = new JsonSerializer4FloatGenerator(mbgConfiguration);
        jsonSerializer4FloatGenerator.generate(classModel);

        Generator jsonSerializer4SqlDateGenerator = new JsonSerializer4SqlDateGenerator(mbgConfiguration);
        jsonSerializer4SqlDateGenerator.generate(classModel);

        Generator jsonSerializer4TimestampGenerator = new JsonSerializer4TimestampGenerator(mbgConfiguration);
        jsonSerializer4TimestampGenerator.generate(classModel);

        Generator jsonSerializer4UtilDateGenerator = new JsonSerializer4UtilDateGenerator(mbgConfiguration);
        jsonSerializer4UtilDateGenerator.generate(classModel);
        ///////////fastjson end ////////////
//        }

        logger.info("generate your project success...");

    }


}

package org.hddframework.generator.mybatis.configuration;

import lombok.Data;

/**
 * Created by SongWei on 10/01/2018.
 */
@Data
public class GeneratorConfiguration {

    public GeneratorConfiguration() {
        this.generateBuildFile = true;
        this.generateProjectPropertiesFile = true;
        this.generateDataSourceConfigurationFile = true;
        this.generateMybatisCriterion = true;
        this.generateController = true;
        this.generateMapper = true;
        this.generateMapperXml = true;
        this.generateModel = true;
        this.generateModelExample = true;
        this.generateService = true;
        this.generateServiceImpl = true;
        this.generateStartupFile = true;
        this.generateResultData = true;
        this.generateWebMvcConfiguration = true;
    }

    private boolean generateController = true;
    private boolean generateService = true;
    private boolean generateServiceImpl = true;
    private boolean generateMapper = true;
    private boolean generateMapperXml = true;
    private boolean generateModel = true;
    private boolean generateModelExample = true;
    private boolean generateDataSourceConfigurationFile = true;
    private boolean generateProjectPropertiesFile = true;
    private boolean generateMybatisCriterion = true;
    private boolean generateResultData = true;
    private boolean generateExceptionHandler = true;
    private boolean generateWebMvcConfiguration = true;
    private boolean generateStartupFile = true;
    private boolean generateBuildFile = true;

}

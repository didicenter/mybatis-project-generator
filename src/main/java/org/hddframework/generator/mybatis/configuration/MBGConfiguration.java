package org.hddframework.generator.mybatis.configuration;

import freemarker.template.Configuration;
import lombok.Data;
import org.hddframework.generator.mybatis.MybatisGenerator;

/**
 * Created by SongWei on 10/01/2018.
 */
@Data
public class MBGConfiguration {

    private DatabaseConfiguration databaseConfiguration;
    private GenerationConfiguration generationConfiguration;
    private GeneratorConfiguration generatorConfiguration;
    private ProjectConfiguration projectConfiguration;

    private final Configuration ftlConfiguration;
    private static final String templatePath = "/templates/mybatis/ftl/";

    public MBGConfiguration() {
        this.ftlConfiguration = new Configuration(Configuration.VERSION_2_3_23);
        this.ftlConfiguration.setClassForTemplateLoading(MybatisGenerator.class, templatePath);
        this.databaseConfiguration = new DatabaseConfiguration();
        this.generationConfiguration = new GenerationConfiguration();
        this.generatorConfiguration = new GeneratorConfiguration();
    }


}

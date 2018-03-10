package org.hddframework.generator.mybatis.configuration;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
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
    private static final String templatePath_framework = "/templates/framework/ftl/";
    private static final String templatePath_fastjson = "/templates/mybatis/ftl/fastjson/";
    private static final String templatePath_gson = "/templates/mybatis/ftl/gson/";
    private static final String templatePath_mvc = "/templates/mybatis/ftl/mvc/";

    public MBGConfiguration() {

        ClassTemplateLoader root_classTemplateLoader = new ClassTemplateLoader(MybatisGenerator.class, templatePath);
        ClassTemplateLoader fastjson_classTemplateLoader = new ClassTemplateLoader(MybatisGenerator.class, templatePath_fastjson);
        ClassTemplateLoader gson_classTemplateLoader = new ClassTemplateLoader(MybatisGenerator.class, templatePath_gson);
        ClassTemplateLoader mvc_classTemplateLoader = new ClassTemplateLoader(MybatisGenerator.class, templatePath_mvc);
        ClassTemplateLoader framework_classTemplateLoader = new ClassTemplateLoader(MybatisGenerator.class, templatePath_framework);
        TemplateLoader[] templateLoaders = {root_classTemplateLoader, fastjson_classTemplateLoader, gson_classTemplateLoader, mvc_classTemplateLoader, framework_classTemplateLoader};
        MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(templateLoaders);

        this.ftlConfiguration = new Configuration(Configuration.VERSION_2_3_23);
//        this.ftlConfiguration.setClassForTemplateLoading(MybatisGenerator.class, templatePath);
        this.ftlConfiguration.setTemplateLoader(multiTemplateLoader);
        this.databaseConfiguration = new DatabaseConfiguration();
        this.generationConfiguration = new GenerationConfiguration();
        this.generatorConfiguration = new GeneratorConfiguration();
    }


}

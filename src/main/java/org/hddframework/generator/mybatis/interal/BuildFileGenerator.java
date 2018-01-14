package org.hddframework.generator.mybatis.interal;

import freemarker.template.Template;
import org.hddframework.generator.mybatis.configuration.MBGConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by SongWei on 10/01/2018.
 */
public class BuildFileGenerator extends GeneratorTemplate {

    private final String mavenTemplateName = "Maven.ftl";
    private final String gradleTemplateName = "Gradle.ftl";
    private final String mavenSuffix = "pom.xml";
    private final String gradleSuffix = "build.gradle";

    public BuildFileGenerator(MBGConfiguration mbgConfiguration) {
        super(mbgConfiguration);
    }

    @Override
    protected File getDirectory() {
        String projectRootPath = mbgConfiguration.getProjectConfiguration().getRootAbsolutePath();
        File modelDirectory = new File(projectRootPath);
        return modelDirectory;
    }

    @Override
    protected Template getTemplate() {
        String template = mbgConfiguration.getProjectConfiguration().isUseGradle() ? gradleTemplateName : mavenTemplateName;
        try {
            return mbgConfiguration.getFtlConfiguration().getTemplate(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getFileNameSuffix() {
        String suffix = mbgConfiguration.getProjectConfiguration().isUseGradle() ? gradleSuffix : mavenSuffix;
        return suffix;
    }

    @Override
    protected Boolean needModelClassName() {
        return false;
    }
}

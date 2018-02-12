package org.hddframework.generator.mybatis.interal.gson;

import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.hddframework.generator.mybatis.configuration.MBGConfiguration;
import org.hddframework.generator.mybatis.interal.GeneratorTemplate;

import java.io.File;
import java.io.IOException;


public class JsonSerializer4BigDecimalGenerator extends GeneratorTemplate {

    private final String templateName = "JsonSerializer4BigDecimal.ftl";
    private final String fileNameSuffix = "JsonSerializer4BigDecimal.java";
    private final String modelPathName = "configuration/gson";

    public JsonSerializer4BigDecimalGenerator(MBGConfiguration mbgConfiguration) {
        super(mbgConfiguration);
    }

    @Override
    protected File getDirectory() {
        String modelPath = (mbgConfiguration.getProjectConfiguration().getBasePackageDirectory().getAbsolutePath() + File.separator + this.modelPathName)
                .replaceAll(File.separator + File.separator + "1+", File.separator);
        File modelDirectory = new File(modelPath);
        try {
            FileUtils.forceMkdir(modelDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelDirectory;
    }

    @Override
    protected Template getTemplate() {
        try {
            return this.mbgConfiguration.getFtlConfiguration().getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getFileNameSuffix() {
        return this.fileNameSuffix;
    }

    @Override
    protected Boolean needModelClassName() {
        return false;
    }


}

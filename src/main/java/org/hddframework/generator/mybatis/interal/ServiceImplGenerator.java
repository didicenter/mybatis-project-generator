package org.hddframework.generator.mybatis.interal;

import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.hddframework.generator.mybatis.configuration.MBGConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by SongWei on 09/01/2018.
 */
public class ServiceImplGenerator extends GeneratorTemplate {

    private final String templateName = "ServiceImpl.ftl";
    private final String fileNameSuffix = "ServiceImpl.java";
    private final String modelPathName = "service" + File.separator + "impl";

    public ServiceImplGenerator(MBGConfiguration mbgConfiguration) {
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
        return true;
    }

}
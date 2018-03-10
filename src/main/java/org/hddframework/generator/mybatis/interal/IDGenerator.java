package org.hddframework.generator.mybatis.interal;

import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.hddframework.generator.mybatis.configuration.MBGConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by SongWei on 13/01/2018.
 */
public class IDGenerator extends FrameworkGenerator {

    private final String templateName = "ID.ftl";
    private final String fileNameSuffix = "ID.java";
    private final String modelPathName = "jdbc" + File.separator + "annotation";

    public IDGenerator(MBGConfiguration mbgConfiguration) {
        super(mbgConfiguration);
    }

    @Override
    protected File getDirectory() {
        File file = super.getDirectory();

        String modelPath = (file.getAbsolutePath() + File.separator + modelPathName)
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

package org.hddframework.generator.mybatis.interal;

import org.apache.commons.io.FileUtils;
import org.hddframework.generator.mybatis.configuration.MBGConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by SongWei on 09/01/2018.
 */
public abstract class PluginGenerator extends GeneratorTemplate {

    private final String pluginDirectoryPath = new String("org" + File.separator + "mybatis" + File.separator + "plugin");

    public PluginGenerator(MBGConfiguration mbgConfiguration) {
        super(mbgConfiguration);
    }

    @Override
    protected File getDirectory() {
        String modelPath = (mbgConfiguration.getProjectConfiguration().getJavaDirectory().getAbsolutePath() + File.separator + this.pluginDirectoryPath)
                .replaceAll(File.separator + File.separator + "1+", File.separator);
        File modelDirectory = new File(modelPath);
        try {
            FileUtils.forceMkdir(modelDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelDirectory;
    }

}

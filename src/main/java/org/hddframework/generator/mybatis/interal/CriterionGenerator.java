package org.hddframework.generator.mybatis.interal;

import freemarker.template.Template;
import org.hddframework.generator.mybatis.configuration.MBGConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by SongWei on 13/01/2018.
 */
public class CriterionGenerator extends PluginGenerator {

    private final String templateName = "Criterion.ftl";
    private final String fileNameSuffix = "Criterion.java";

    public CriterionGenerator(MBGConfiguration mbgConfiguration) {
        super(mbgConfiguration);
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

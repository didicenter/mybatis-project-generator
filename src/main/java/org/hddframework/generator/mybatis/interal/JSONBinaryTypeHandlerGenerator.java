package org.hddframework.generator.mybatis.interal;

import freemarker.template.Template;
import org.hddframework.generator.mybatis.configuration.MBGConfiguration;

import java.io.IOException;

/**
 * Created by SongWei on 13/01/2018.
 */
public class JSONBinaryTypeHandlerGenerator extends JSONTypeHandlerGenerator {

    private final String templateName = "JSONBinaryTypeHandler.ftl";
    private final String fileNameSuffix = "JSONBinaryTypeHandler.java";

    public JSONBinaryTypeHandlerGenerator(MBGConfiguration mbgConfiguration) {
        super(mbgConfiguration);
    }

    @Override
    protected Template getTemplate() {
        try {
            return this.mbgConfiguration.getFtlConfiguration().getTemplate(this.templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getFileNameSuffix() {
        return this.fileNameSuffix;
    }

}

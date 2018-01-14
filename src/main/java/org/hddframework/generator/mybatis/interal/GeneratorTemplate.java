package org.hddframework.generator.mybatis.interal;

import freemarker.template.Template;
import org.hddframework.generator.mybatis.Generator;
import org.hddframework.generator.mybatis.configuration.MBGConfiguration;
import org.hddframework.generator.mybatis.model.DataModel;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/**
 * Created by SongWei on 09/01/2018.
 */
public abstract class GeneratorTemplate implements Generator {

    protected final MBGConfiguration mbgConfiguration;

    public GeneratorTemplate(MBGConfiguration mbgConfiguration) {
        this.mbgConfiguration = mbgConfiguration;
    }

    protected abstract File getDirectory();

    protected abstract Template getTemplate();

    protected abstract String getFileNameSuffix();

    protected abstract Boolean needModelClassName();

//    protected abstract DataModel getDataModel();

    @Override
    public void generate(DataModel dataModel) {
        try {
            String modelClassName = needModelClassName() ? dataModel.getModelName() : "";
            String filePath = new String(getDirectory().getAbsoluteFile() + File.separator + modelClassName + getFileNameSuffix())
                    .replaceAll(File.separator + File.separator + "1+", File.separator);
            File output = new File(filePath);
            Writer writer = new FileWriter(output);
            getTemplate().process(dataModel, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

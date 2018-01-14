package org.hddframework.generator.mybatis.model;

import lombok.Data;

/**
 * Created by SongWei on 11/01/2018.
 */
@Data
public class BuildModel implements DataModel {

    private String group;
    private String artifact;
    private String version = "0.0.1-SNAPSHOT";

    @Override
    public String getModelName() {
        return "";
    }

}

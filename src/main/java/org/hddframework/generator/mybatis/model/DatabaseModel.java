package org.hddframework.generator.mybatis.model;

import lombok.Data;

/**
 * Created by SongWei on 10/01/2018.
 */
@Data
public class DatabaseModel implements DataModel {

    private String userName;
    private String password = "";
    private String driverClassName;
    private String url;

    @Override
    public String getModelName() {
        return "";
    }
}

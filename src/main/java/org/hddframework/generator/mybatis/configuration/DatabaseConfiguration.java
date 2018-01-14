package org.hddframework.generator.mybatis.configuration;

import lombok.Data;
import org.hddframework.generator.mybatis.util.ConnectionURLUtils;
import org.hddframework.generator.mybatis.database.DatabaseType;

/**
 * Created by SongWei on 10/01/2018.
 */
@Data
public class DatabaseConfiguration {

    private String url;
    private String driverClassName;
    private String userName;
    private String password = "";
    private String tableNamePattern = "";
    private String[] excludeTable;

    public DatabaseConfiguration() {
        this.password = "";
        this.tableNamePattern = "";
        this.excludeTable = new String[]{};
    }

    public DatabaseType getDbType() {
        return ConnectionURLUtils.getDatabaseTypeFromUrl(url);
    }





}

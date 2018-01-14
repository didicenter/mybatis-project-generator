package org.hddframework.generator.mybatis.configuration;

import lombok.Data;

/**
 * Created by SongWei on 10/01/2018.
 */
@Data
public class GenerationConfiguration {

    private boolean mapUnderscoreToCamelCase = true;

    public GenerationConfiguration() {
        mapUnderscoreToCamelCase = true;
    }

}

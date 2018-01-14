package org.hddframework.generator.mybatis.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by SongWei on 11/01/2018.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataSourceModel extends ClassModel {

    private String mybatisXmlDirectoryName;

}

package org.hddframework.generator.mybatis.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SongWei on 09/01/2018.
 */
@Data
public class ClassModel implements DataModel {

    private String packageName;
    private String className;
    private String tableName;
    private List<PropertyModel> propertyList;

    public List<String> getDataTypeList() {
        return new ArrayList<>(propertyList.stream().map(item -> item.getPropertyType().replaceAll("\\[\\]", "")).collect(Collectors.toSet()));
    }

    public List<String> getSqlTypeList() {
        return new ArrayList<>(propertyList.stream().map(PropertyModel::getColumnType).collect(Collectors.toSet()));
    }

    @Override
    public String getModelName() {
        return this.className;
    }
}

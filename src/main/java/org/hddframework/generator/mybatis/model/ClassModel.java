package org.hddframework.generator.mybatis.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
        return new ArrayList<>(propertyList.stream().map(PropertyModel::getPropertyType).collect(Collectors.toSet()));
    }

    @Override
    public String getModelName() {
        return this.className;
    }
}

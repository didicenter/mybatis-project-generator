package ${packageName}.dao;

import ${packageName}.model.${className};

import java.util.List;


public interface ${className}Dao {


    void insertObject(${className} ${className?uncap_first});

    void updateObject(${className} ${className?uncap_first});

    void deleteObject(${className} ${className?uncap_first});

    ${className} queryForObject(${className} ${className?uncap_first});

    List<${className}> queryForList(${className} ${className?uncap_first});

    Integer count();

    Integer count(${className} ${className?uncap_first});


}

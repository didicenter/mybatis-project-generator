package ${packageName}.service;

import ${packageName}.model.${className};

import java.io.Serializable;
import java.util.List;

public interface ${className}Service {

    ${className} findById(Serializable id);

    List<${className}> findByAll();

    ${className} create(${className} ${className?uncap_first});

    void deleteById(Serializable id);

    ${className} update(Serializable id, ${className} ${className?uncap_first});

}

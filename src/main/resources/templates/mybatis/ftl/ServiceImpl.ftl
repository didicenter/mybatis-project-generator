package ${packageName}.service.impl;

import ${packageName}.mapper.${className}Mapper;
import ${packageName}.model.${className};
import ${packageName}.model.${className}Example;
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Service
@Transactional
public class ${className}ServiceImpl implements ${className}Service {

    @Autowired
    private ${className}Mapper ${className?uncap_first}Mapper;

    @Override
    public ${className} findById(Serializable id) {
        ${className}Example example = new ${className}Example();
        // example.setId((Long)id)
        return ${className?uncap_first}Mapper.selectByExample(example).get(0);
    }

    @Override
    public List<${className}> findByAll() {
        return ${className?uncap_first}Mapper.selectByExample(new ${className}Example());
    }

    @Override
    public ${className} create(${className} ${className?uncap_first}) {
        ${className?uncap_first}Mapper.insertSelective(${className?uncap_first});
        return ${className?uncap_first};
    }

    @Override
    public void deleteById(Serializable id) {
        ${className}Example example = new ${className}Example();
        // example.setId((Long)id)
        ${className?uncap_first}Mapper.deleteByExample(example);
    }

    @Override
    public ${className} update(Serializable id, ${className} ${className?uncap_first}) {
        ${className}Example example = new ${className}Example();
        // example.setId((Long)id)
        ${className?uncap_first}Mapper.updateByExampleSelective(${className?uncap_first},example);
        return ${className?uncap_first};
    }

}


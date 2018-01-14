package ${packageName}.controller;

import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/${className?uncap_first}")
public class  ${className}Controller {

    @Autowired
    private ${className}Service ${className?uncap_first}Service;

    @RequestMapping(value = "/", method = RequestMethod.GET, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<${className}> findAll() throws Exception {
        List<${className}> ${className?uncap_first}List = ${className?uncap_first}Service.findByAll();
        Assert.notEmpty(${className?uncap_first}List, "${className} is not found");
        return ${className?uncap_first}List;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ${className} findById(@PathVariable("id") Serializable id) throws Exception {
        ${className} ${className?uncap_first} = ${className?uncap_first}Service.findById(id);
        Assert.notNull(${className?uncap_first}, "${className} is not found");
        return ${className?uncap_first};
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ${className} create(@RequestBody ${className} ${className?uncap_first}) throws Exception {
        return ${className?uncap_first}Service.create(${className?uncap_first});
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void deleteById(@PathVariable("id") Serializable id) throws Exception {
        ${className?uncap_first}Service.deleteById(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ${className} update(@PathVariable("id") Serializable id, @RequestBody ${className} ${className?uncap_first}) throws Exception {
        return ${className?uncap_first}Service.update(id, ${className?uncap_first});
    }

}


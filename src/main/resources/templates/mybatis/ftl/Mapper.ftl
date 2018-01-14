package ${packageName}.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ${packageName}.model.${className};
import ${packageName}.model.${className}Example;


@Mapper
public interface ${className}Mapper {

    long countByExample(@Param("example") ${className}Example example);

    int deleteByExample(@Param("example") ${className}Example example);

    int insertSelective(${className} record);

    List<${className}> selectByExample(@Param("example") ${className}Example example);

    int updateByExampleSelective(@Param("record") ${className} record, @Param("example") ${className}Example example);

}

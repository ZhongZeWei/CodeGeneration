package ${projectData.basePackage}.${modelData.daoPackage};

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import ${projectData.basePackage}.${modelData.pojoPackage}.${className};

/**
 * ${className}Mapper 数据访问接口
 */
@Component
public interface ${className}Dao extends Mapper<${className}>{



}
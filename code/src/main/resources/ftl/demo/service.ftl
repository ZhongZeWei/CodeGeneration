package ${projectData.basePackage}.${modelData.servicePackage};

import ${projectData.basePackage}.${modelData.pojoPackage}.${className};
import ${projectData.basePackage}.${modelData.daoPackage}.${className}Dao;

import java.util.List;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.io.Serializable;
import java.util.Arrays;

/**
 * ${className}Service
 */
@Service
public class ${className}Service {

	@Autowired
	private ${className}Dao ${lowerClassName}Dao;

	/** 添加方法 */
	public void save(${className} ${lowerClassName}){
		try {
			${lowerClassName}Dao.insertSelective(${lowerClassName});
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 修改方法 */
	public void update(${className} ${lowerClassName}){
		try {
			${lowerClassName}Dao.updateByPrimaryKeySelective(${lowerClassName});
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id删除 */
	public void delete(Serializable id){
		try {
			${lowerClassName}Dao.deleteByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 批量删除 */
	public void deleteAll(Serializable[] ids){
		try {
			// 创建示范对象
			Example example = new Example(${className}.class);
			// 创建条件对象
			Example.Criteria criteria = example.createCriteria();
			// 创建In条件
			criteria.andIn("id", Arrays.asList(ids));
			// 根据示范对象删除
			${lowerClassName}Dao.deleteByExample(example);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据主键id查询 */
	public ${className} findOne(Serializable id){
		try {
			return ${lowerClassName}Dao.selectByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 查询全部 */
	public List<${className}> findAll(){
    try {
    return ${lowerClassName}Dao.selectAll();
    }catch (Exception ex){
    throw new RuntimeException(ex);
    }
    }

    /** 多条件分页查询 */
    public List<${className}> findByPage(${className} ${lowerClassName}, int page, int rows){
        try {
        PageInfo<${className}> pageInfo = PageHelper.startPage(page, rows)
            .doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
            ${lowerClassName}Dao.selectAll();
            }
            });
            return pageInfo.getList();
            }catch (Exception ex){
            throw new RuntimeException(ex);
		}
	}

}
package ${projectData.basePackage}.${modelData.controllerPackage};

import ${projectData.basePackage}.${modelData.pojoPackage}.${className};
import ${projectData.basePackage}.${modelData.servicePackage}.${className}Service;
import ${projectData.basePackage}.utils.Result;
import ${projectData.basePackage}.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ${className}--controller
 */
@RestController
// @RestController= @Controller+@ResponseBody
@RequestMapping("/${lowerClassName}")
@CrossOrigin //解决跨域问题 jsonp
public class ${className}Controller {

    @Autowired
    private ${className}Service ${lowerClassName}Service;


    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",${lowerClassName}Service.findAll());
    }

    /**
     * 查询一个
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",${lowerClassName}Service.findOne(id));
    }

    /**
     * 添加
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody ${className} ${lowerClassName}){
        ${lowerClassName}Service.save(${lowerClassName});
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable int id,@RequestBody ${className} ${lowerClassName}){
        //设置id
        ${lowerClassName}.setId(id);
        ${lowerClassName}Service.update(${lowerClassName});
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        ${lowerClassName}Service.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}

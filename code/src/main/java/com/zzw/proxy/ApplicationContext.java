package com.zzw.proxy;



import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zzw.codeGeneration.MySQLCodeGeneration;
import com.zzw.invocationHandler.MyMethodInterceptor;
import com.zzw.metadata.ModelData;
import com.zzw.metadata.MySQLMetaData;
import com.zzw.metadata.ProjectData;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.ArrayList;

public class ApplicationContext<T> {
    //代码生成类
    private Class<T> codeGenerationClass;
    //增强功能方法类
    private MethodInterceptor myMethodInterceptor;

    //初始化
    public ApplicationContext() {
    }

    //初始化
    public ApplicationContext(Class<T> codeGenerationClass, MethodInterceptor myMethodInterceptor) {
        this.codeGenerationClass = codeGenerationClass;
        this.myMethodInterceptor = myMethodInterceptor;
    }

    public  <T> T getBean(Class<T> codeGenerationClass, MethodInterceptor myMethodInterceptor) {
        return (T) Enhancer.create(codeGenerationClass, myMethodInterceptor);
    }


    public  <T> T getBean() {
        return (T) Enhancer.create(codeGenerationClass, myMethodInterceptor);
    }


    public static void main(String[] args) {
        //设置projectData
        ProjectData projectData = new ProjectData();
        projectData.setSavePath("D:\\forStu\\test");
        //设置modelData
        ModelData modelData = new ModelData();
        modelData.setDatabase("test");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("user");
        modelData.setTables(strings);
        //设置mySQLMetaData
        MySQLMetaData mySQLMetaData = new MySQLMetaData();
        MySQLCodeGeneration mySQLCodeGeneration = new MySQLCodeGeneration(projectData,modelData,mySQLMetaData);
        ApplicationContext<MySQLCodeGeneration> applicationContext = new ApplicationContext<>(MySQLCodeGeneration.class, new MyMethodInterceptor(mySQLCodeGeneration));
        MySQLCodeGeneration bean = applicationContext.getBean();
        bean.run();
    }
}

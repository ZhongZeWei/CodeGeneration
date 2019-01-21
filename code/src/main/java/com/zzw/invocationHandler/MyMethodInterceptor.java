package com.zzw.invocationHandler;

import com.zzw.annotation.Generation;
import com.zzw.codeGeneration.MySQLCodeGeneration;
import com.zzw.metadata.ModelData;
import com.zzw.metadata.MySQLMetaData;
import com.zzw.metadata.ProjectData;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

// InvocationHandler实现类
public class MyMethodInterceptor implements MethodInterceptor {

    private MySQLCodeGeneration codeGeneration;

    public MyMethodInterceptor(MySQLCodeGeneration codeGeneration) {
        this.codeGeneration = codeGeneration;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        ProjectData projectData = codeGeneration.getProjectData();
        ModelData modelData = codeGeneration.getModelData();
        MySQLMetaData mySQLMetaData = codeGeneration.getMySQLMetaData();
        Class<? extends MySQLCodeGeneration> codeGenerationClass = codeGeneration.getClass();
        //代理run方法
        if (method.getName().equals("run")) { // 对于是要增强的方法进行增强
            //获取codeGenerationClass所有方法
            Method[] codeGenerationMethods = codeGenerationClass.getDeclaredMethods();
            //遍历每个方法，查找是否有CodeGeneration注解，如果有，则执行反射
            for (Method codeGenerationMethod : codeGenerationMethods) {
                if (codeGenerationMethod.isAnnotationPresent(Generation.class)) {
                    invoke(codeGenerationMethod, projectData, modelData, mySQLMetaData);
                }
            }
            return null;
        } else if (!method.getName().equals("run") && method.isAnnotationPresent(Generation.class)) {
            return invoke(method, projectData, modelData, mySQLMetaData);
        } else { // 不需要增强的方法直接调用目标对象的方法
            return method.invoke(codeGenerationClass.newInstance(), objects);
        }
    }

    private Object invoke(Method method, Object... args) throws Exception {
        // 根据注解Class对象获取注解对象
        Generation generation = (Generation) method.getAnnotation(Generation.class);
        // 输出Generation注解属性值
        Class applicationData = generation.value();
        Object applicationDataInstance = applicationData.newInstance();
        //执行注解的方法，并且返回对象invokeResult
        String methodName = generation.methodName();
        Method applicationDataMethod = applicationData.getMethod(methodName, ProjectData.class, ModelData.class, MySQLMetaData.class);
        //暴力反射
        applicationDataMethod.setAccessible(true);
        LinkedHashMap<String, Object> invokeResult = null;
        if (null == args) {
            invokeResult = (LinkedHashMap<String, Object>) applicationDataMethod.invoke(applicationDataInstance);
        } else {
            ProjectData projectData = (ProjectData) args[0];
            ModelData modelData = (ModelData) args[1];
            MySQLMetaData mySQLMetaData = (MySQLMetaData) args[2];
            invokeResult = (LinkedHashMap<String, Object>) applicationDataMethod.invoke(applicationDataInstance, projectData, modelData, mySQLMetaData);
        }
        //用invokeResult作为参数，去调用codeGenerationMethod
        Object object = method.invoke(codeGeneration, invokeResult);
        return object;
    }

}
package com.zzw.codeGeneration;

import com.alibaba.fastjson.JSONObject;
import com.zzw.annotation.Generation;
import com.zzw.applicationData.MySQLApplicationData;
import com.zzw.metadata.ColumnPoJo;
import com.zzw.metadata.ModelData;
import com.zzw.metadata.MySQLMetaData;
import com.zzw.metadata.ProjectData;
import com.zzw.utils.FreeMarkerUtils;
import com.zzw.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySQLCodeGeneration implements CodeGeneration {

    private ProjectData projectData;
    private ModelData modelData;
    private MySQLMetaData mySQLMetaData;

    @Generation(value = MySQLApplicationData.class, methodName = "findApplicationData")
    public void javaGeneration(Object object) {
        //得到数据
        LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) object;
        ModelData modelData = (ModelData) data.get("modelData");
        ProjectData projectData = (ProjectData) data.get("projectData");
        MySQLMetaData mySQLMetaData = (MySQLMetaData) data.get("mySQLMetaData");
        String savePath = projectData.getSavePath();
        String basePackage = projectData.getBasePackage();
        String pojoPackage = modelData.getPojoPackage();
        String fileDir = savePath+"\\"+ projectData.getModelName()+"\\"+"src\\main\\java\\"+StringUtils.transferPackagePath2FilePath(basePackage+"."+pojoPackage);
        //获取表的列表
        List<String> tables = modelData.getTables();
        for (String table : tables) {
            LinkedHashMap<String, ArrayList<ColumnPoJo>> columns = mySQLMetaData.getColumns();
            ArrayList<ColumnPoJo> columnPoJos = columns.get(table);
            String fileName = StringUtils.transferUpper(table)+".java";
            //安照这五个要素操作
            data.put("columns",columnPoJos);
            data.put("className",StringUtils.transferUpper(table));
            FreeMarkerUtils.ouPutFile( "demo",  "pojo.ftl",  fileDir, fileName,data);
        }
        //开始渲染

        String s = JSONObject.toJSONString(data);
        System.out.println(s);
    }

    @Generation(value = MySQLApplicationData.class, methodName = "findApplicationData")
    public void daoGeneration(Object object) {
        //得到数据
        LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) object;
        ModelData modelData = (ModelData) data.get("modelData");
        ProjectData projectData = (ProjectData) data.get("projectData");
        MySQLMetaData mySQLMetaData = (MySQLMetaData) data.get("mySQLMetaData");
        String savePath = projectData.getSavePath();
        String basePackage = projectData.getBasePackage();
        String daoPackage = modelData.getDaoPackage();
        String fileDir = savePath+"\\"+ projectData.getModelName()+"\\"+"src\\main\\java\\"+StringUtils.transferPackagePath2FilePath(basePackage+"."+daoPackage);
        //获取表的列表
        List<String> tables = modelData.getTables();
        for (String table : tables) {
            LinkedHashMap<String, ArrayList<ColumnPoJo>> columns = mySQLMetaData.getColumns();
            ArrayList<ColumnPoJo> columnPoJos = columns.get(table);
            String fileName = StringUtils.transferUpper(table)+"Dao.java";
            //安照这五个要素操作
            data.put("columns",columnPoJos);
            data.put("className",StringUtils.transferUpper(table));
            FreeMarkerUtils.ouPutFile( "demo",  "dao.ftl",  fileDir, fileName,data);
        }
        //开始渲染

        String s = JSONObject.toJSONString(data);
        System.out.println(s);
    }

    @Generation(value = MySQLApplicationData.class, methodName = "findApplicationData")
    public void serviceGeneration(Object object) {
        //得到数据
        LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) object;
        ModelData modelData = (ModelData) data.get("modelData");
        ProjectData projectData = (ProjectData) data.get("projectData");
        MySQLMetaData mySQLMetaData = (MySQLMetaData) data.get("mySQLMetaData");
        String savePath = projectData.getSavePath();
        String basePackage = projectData.getBasePackage();
        String servicePackage = modelData.getServicePackage();
        String fileDir = savePath+"\\"+ projectData.getModelName()+"\\"+"src\\main\\java\\"+StringUtils.transferPackagePath2FilePath(basePackage+"."+servicePackage);
        //获取表的列表
        List<String> tables = modelData.getTables();
        for (String table : tables) {
            LinkedHashMap<String, ArrayList<ColumnPoJo>> columns = mySQLMetaData.getColumns();
            ArrayList<ColumnPoJo> columnPoJos = columns.get(table);
            String fileName = StringUtils.transferUpper(table)+"Service.java";
            //安照这五个要素操作
            data.put("columns",columnPoJos);
            data.put("className",StringUtils.transferUpper(table));
            data.put("lowerClassName",StringUtils.transferLower(table));
            FreeMarkerUtils.ouPutFile( "demo",  "service.ftl",  fileDir, fileName,data);
        }
        //开始渲染

        String s = JSONObject.toJSONString(data);
        System.out.println(s);
    }

    @Generation(value = MySQLApplicationData.class, methodName = "findApplicationData")
    public void controllerGeneration(Object object) {
        //得到数据
        LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) object;
        ModelData modelData = (ModelData) data.get("modelData");
        ProjectData projectData = (ProjectData) data.get("projectData");
        MySQLMetaData mySQLMetaData = (MySQLMetaData) data.get("mySQLMetaData");
        String savePath = projectData.getSavePath();
        String basePackage = projectData.getBasePackage();
        String controllerPackage = modelData.getControllerPackage();
        String fileDir = savePath+"\\"+ projectData.getModelName()+"\\"+"src\\main\\java\\"+StringUtils.transferPackagePath2FilePath(basePackage+"."+controllerPackage);
        //获取表的列表
        List<String> tables = modelData.getTables();
        for (String table : tables) {
            LinkedHashMap<String, ArrayList<ColumnPoJo>> columns = mySQLMetaData.getColumns();
            ArrayList<ColumnPoJo> columnPoJos = columns.get(table);
            String fileName = StringUtils.transferUpper(table)+"Controller.java";
            //安照这五个要素操作
            data.put("columns",columnPoJos);
            data.put("className",StringUtils.transferUpper(table));
            data.put("lowerClassName",StringUtils.transferLower(table));
            FreeMarkerUtils.ouPutFile( "demo",  "controller.ftl",  fileDir, fileName,data);
        }
        //开始渲染

        String s = JSONObject.toJSONString(data);
        System.out.println(s);
    }

    @Generation(value = MySQLApplicationData.class, methodName = "findApplicationData")
    public void appGeneration(Object object) {
        //得到数据
        LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) object;
        ModelData modelData = (ModelData) data.get("modelData");
        ProjectData projectData = (ProjectData) data.get("projectData");
        MySQLMetaData mySQLMetaData = (MySQLMetaData) data.get("mySQLMetaData");
        String basePackage = projectData.getBasePackage();
        String savePath = projectData.getSavePath();
        String fileDir = savePath+"\\"+ projectData.getModelName()+"\\"+"src\\main\\java\\"+StringUtils.transferPackagePath2FilePath(basePackage);
        FreeMarkerUtils.ouPutFile( "demo",  "app.ftl",  fileDir, "Application.java",data);
        //开始渲染
        String s = JSONObject.toJSONString(data);
        System.out.println(s);
    }

    @Generation(value = MySQLApplicationData.class, methodName = "findApplicationData")
    public void pomGeneration(Object object) {
        //得到数据
        LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) object;
        ModelData modelData = (ModelData) data.get("modelData");
        ProjectData projectData = (ProjectData) data.get("projectData");
        MySQLMetaData mySQLMetaData = (MySQLMetaData) data.get("mySQLMetaData");
        String savePath = projectData.getSavePath();
        String fileDir = savePath+"\\"+ projectData.getModelName();
        FreeMarkerUtils.ouPutFile( "demo",  "pom.ftl",  fileDir, "pom.xml",data);
        //开始渲染
        String s = JSONObject.toJSONString(data);
        System.out.println(s);
    }

    @Generation(value = MySQLApplicationData.class, methodName = "findApplicationData")
    public void ymlGeneration(Object object) {
        //得到数据
        LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) object;
        ModelData modelData = (ModelData) data.get("modelData");
        ProjectData projectData = (ProjectData) data.get("projectData");
        MySQLMetaData mySQLMetaData = (MySQLMetaData) data.get("mySQLMetaData");
        String savePath = projectData.getSavePath();
        String basePackage = projectData.getBasePackage();
        String controllerPackage = modelData.getControllerPackage();
        String fileDir = savePath+"\\"+ projectData.getModelName()+"\\"+"src\\main\\resources";
        FreeMarkerUtils.ouPutFile( "demo",  "yml.ftl",  fileDir, "application.yml",data);
        //开始渲染
        String s = JSONObject.toJSONString(data);
        System.out.println(s);
    }

    @Generation(value = MySQLApplicationData.class, methodName = "findApplicationData")
    public void utilsGeneration(Object object) {
        //得到数据
        LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) object;
        ModelData modelData = (ModelData) data.get("modelData");
        ProjectData projectData = (ProjectData) data.get("projectData");
        MySQLMetaData mySQLMetaData = (MySQLMetaData) data.get("mySQLMetaData");
        String savePath = projectData.getSavePath();
        String basePackage = projectData.getBasePackage();
        String controllerPackage = modelData.getControllerPackage();
        String fileDir = savePath+"\\"+ projectData.getModelName()+"\\"+"src\\main\\java\\"+StringUtils.transferPackagePath2FilePath(basePackage+"."+"utils");
        FreeMarkerUtils.ouPutFile( "demo\\utils",  "IdWorker.ftl",  fileDir, "IdWorker.java",data);
        FreeMarkerUtils.ouPutFile( "demo\\utils",  "PageResult.ftl",  fileDir, "PageResult.java",data);
        FreeMarkerUtils.ouPutFile( "demo\\utils",  "Result.ftl",  fileDir, "Result.java",data);
        FreeMarkerUtils.ouPutFile( "demo\\utils",  "StatusCode.ftl",  fileDir, "StatusCode.java",data);
        //开始渲染
        String s = JSONObject.toJSONString(data);
        System.out.println(s);
    }

    @Override
    public void run() {

    }
}

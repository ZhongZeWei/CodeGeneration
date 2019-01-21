package com.zzw.utils;



import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * FreeMarker模版工具类
 */
public class FreeMarkerUtils {
    /**
     * 定义模版配置信息对象
     */
    private static Configuration configuration;


    public static void ouPutFile(String ftlDir, String ftlName, String pathDir,String fileName,Map map) {
        try {
            configuration = new Configuration(Configuration.VERSION_2_3_26);
            configuration.setDefaultEncoding("UTF-8");
            configuration.setClassForTemplateLoading(FreeMarkerUtils.class, "/ftl/" + ftlDir);
            // 获得包的模板
            Template template = configuration.getTemplate(ftlName);
            // 指定文件输出的路径

            //生成目录
            new File(pathDir).mkdirs();
            //文件名字
            File file = new File(pathDir+"\\"+fileName);
            // 定义输出流，注意的必须指定编码
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file)));
            // 生成模板
            template.process(map, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void CopyOuPutFile(String ftlDir, String ftlName,String pathDir,String fileName) {
        try {
            configuration = new Configuration(Configuration.VERSION_2_3_26);
            configuration.setDefaultEncoding("UTF-8");
            configuration.setClassForTemplateLoading(FreeMarkerUtils.class, "/ftl/" + ftlDir);
            // 获得包的模板
            Template template = configuration.getTemplate(ftlName);
            // 指定文件输出的路径

            //生成目录
            new File(pathDir).mkdirs();
            //文件名字
            File file = new File(pathDir+"\\"+fileName);
            // 定义输出流，注意的必须指定编码
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file)));
            // 生成模板
            Map<String, Object> configMap = new HashMap<String, Object>();
            template.process(configMap, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}





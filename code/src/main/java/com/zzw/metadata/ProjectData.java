package com.zzw.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectData {


    private String group ="com.example";
    private String artifact="demo";
    private String projectName =this.artifact;
    private String modelName=this.artifact;
    private String type="Maven Project";
    private String language="java";
    private String packaging="Jar";
    private String javaVersion="8";
    private String version="0.0.1-SNAPSHOT";
    private String name=this.artifact;
    private String description =this.artifact+" project from codeGeneration";
    private String basePackage=this.group+"."+this.artifact;
    private String savePath;

}

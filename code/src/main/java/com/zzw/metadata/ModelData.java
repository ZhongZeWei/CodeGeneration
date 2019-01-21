package com.zzw.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelData {

    private String pojoPackage ="pojo";
    private String daoPackage ="dao";
    private String servicePackage="service";
    private String controllerPackage ="controller";
    private String applicationName="demo";
    private String serverPort="9000";
    private String database;
    private List<String> tables;


}

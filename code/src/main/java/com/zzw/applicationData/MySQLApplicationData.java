package com.zzw.applicationData;

import com.zzw.metadata.ColumnPoJo;
import com.zzw.metadata.ModelData;
import com.zzw.metadata.MySQLMetaData;
import com.zzw.metadata.ProjectData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class MySQLApplicationData implements ApplicationData {

    private ProjectData projectData;
    private ModelData modelData;
    private MySQLMetaData mySQLMetaData;





    public LinkedHashMap<String, Object> findApplicationData(ProjectData projectData,ModelData modelData,MySQLMetaData mySQLMetaData) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
        linkedHashMap.put("projectData", projectData);
        linkedHashMap.put("modelData", modelData);
        //根据projectData、modelData查询mySQLMetaData
        //获取连接对象
        String database = modelData.getDatabase();
        List<String> tables = modelData.getTables();
        LinkedHashMap<String, ArrayList<ColumnPoJo>> columnsFromMany = mySQLMetaData.findColumnsFromMany(database, tables);
        mySQLMetaData.setColumns(columnsFromMany);
        linkedHashMap.put("mySQLMetaData", mySQLMetaData);
        return linkedHashMap;
    }


}

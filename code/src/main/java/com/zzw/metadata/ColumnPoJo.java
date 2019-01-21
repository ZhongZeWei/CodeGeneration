package com.zzw.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnPoJo {

    private String sqlField;
    private String upperClassField;
    private String lowerClassField;
    private String sqlType;
    private String classType;
    private String collation;
    private String canNull;
    private String isKey;
    private String defaultValue;
    private String extra;
    private String privileges;
    private String comment;
    //是否有外键约束
    private String isFR;
    private String fRTableName;
    private String fRColumnName;




}


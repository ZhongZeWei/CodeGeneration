package ${projectData.basePackage}.${modelData.pojoPackage};

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${className} implements Serializable {

<#list mySQLMetaData.columns.user as columnPoJo>
    <#if columnPoJo.comment != '' && columnPoJo.isKey == "true">
    //${columnPoJo.comment} 为主键
    @Id
    @Column(name="${columnPoJo.lowerClassField}")
    <#elseif  columnPoJo.isKey == "true">
    //${columnPoJo.comment} 为外键
    <#elseif columnPoJo.comment != ''>
    //${columnPoJo.comment}
    @Column(name="${columnPoJo.lowerClassField}")
    <#else>
    @Column(name="${columnPoJo.lowerClassField}")
    </#if>
    private ${columnPoJo.classType} ${columnPoJo.lowerClassField};
</#list>

}


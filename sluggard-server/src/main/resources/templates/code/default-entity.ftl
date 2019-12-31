package ${project.basePackage}.common.entity;

import java.io.Serializable;
<#if javaTypeImports??>
<#list javaTypeImports as javaTypeImport>
<#if !(javaTypeImport ? starts_with("java.lang."))>
import ${javaTypeImport};
</#if>
</#list>
</#if>

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${table.tableComment}实体类
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ${table.moduleName}Entity implements Serializable {

<#if columns??>
<#list columns as column>
    /**
     * ${column.columnComment}
     */
    private ${column.columnType} ${column.fieldName};
</#list>
</#if>
}
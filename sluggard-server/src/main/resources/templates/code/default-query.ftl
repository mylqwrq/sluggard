package ${project.basePackage}.common.query;

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
 * ${table.tableComment}数据查询对象
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ${table.moduleName}Query implements Serializable {

<#if columns??>
<#list columns as column>
    /**
     * ${column.columnComment}
     */
    private ${column.columnType} ${column.fieldName};
</#list>
</#if>
    /**
     * 排序字符串
     */
    private String sortString;
}
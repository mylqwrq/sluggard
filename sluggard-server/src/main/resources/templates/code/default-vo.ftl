package ${project.basePackage}.vo;

import java.io.Serializable;
<#if javaTypeImports??>
    <#list javaTypeImports as javaTypeImport>
        <#if !(javaTypeImport ? starts_with("java.lang."))>
            import ${javaTypeImport};
        </#if>
    </#list>
</#if>

import com.mylq.core.base.vo.BaseVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${table.tableComment}视图对象
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel(value = "${table.moduleName}VO", description = "${table.tableComment}视图对象")
public class ${table.moduleName}VO extends BaseVO {

<#if columns??>
<#list columns as column>
    /**
     * ${column.columnComment}
     */
    @ApiModelProperty(value = "${column.columnComment}", name = "${column.fieldName}")
    private ${column.columnType} ${column.fieldName};
</#list>
</#if>
}
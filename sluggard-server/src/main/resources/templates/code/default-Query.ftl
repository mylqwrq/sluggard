package ${project.basePackage}.common.query;

import java.io.Serializable;
<#if javaTypeImports??>
<#list javaTypeImports as javaTypeImport>
<#if !(javaTypeImport ? starts_with("java.lang."))>
import ${javaTypeImport};
</#if>
</#list>
</#if>

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ${table.tableComment}查询对象
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel(value = "${table.moduleName}Query", description = "${table.tableComment}查询对象")
public class ${table.moduleName}Query implements Serializable {

<#if columns??>
<#list columns as column>
    /**
     * ${column.columnComment}
     */
    @ApiModelProperty(value = "${column.columnComment}", name = "${column.fieldName}")
    private ${column.columnType} ${column.fieldName};
</#list>
</#if>
    /**
     * 分页参数-页序号
     */
    @ApiModelProperty(value = "分页参数-页序号", name = "pageNum")
    private int pageNum = 0;
    /**
     * 分页参数-页大小
     */
    @ApiModelProperty(value = "分页参数-页大小", name = "pageSize")
    private int pageSize = 10;
    /**
     * 排序参数
     */
    @ApiModelProperty(value = "排序参数", name = "sortString")
    private String sortString;
}
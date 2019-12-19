package ${basePackage}.common.entity;

import com.gw.cloud.common.core.base.entity.AbstractBaseUpdateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
<#if javaTypes??>
<#list javaTypes as javaType>
<#if !(javaType ? starts_with("java.lang."))>
import ${javaType};
</#if>
</#list>
</#if>

/**
 * ${tableComment}实体类
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@ApiModel(value = "${moduleName}Entity", description = "${tableComment}实体类")
public class ${moduleName}Entity extends AbstractBaseUpdateEntity<${primary["columnType"]}> {

<#if columns??>
<#list columns as column>
    /**
     * ${column["columnComment"]}
     */
    @ApiModelProperty(value = "${column["columnComment"]}", name = "${column["fieldName"]}")
    private ${column["columnType"]} ${column["fieldName"]};
</#list>
</#if>

}
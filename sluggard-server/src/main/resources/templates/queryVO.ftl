package ${basePackage}.common.vo;

import com.gw.cloud.common.core.base.entity.AbstractBaseQueryEntity;
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
 * ${tableComment}查询视图对象
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@ApiModel(value = "${moduleName}QueryVO", description = "${tableComment}查询视图对象")
public class ${moduleName}QueryVO extends AbstractBaseQueryEntity<${primary["columnType"]}> {

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
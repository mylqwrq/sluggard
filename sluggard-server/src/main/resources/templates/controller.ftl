package ${packageUrl}.controller;

import com.gw.cloud.common.core.base.controller.AbstractBaseController;
import ${packageUrl}.common.entity.${moduleName}Entity;
import ${packageUrl}.common.vo.${moduleName}QueryVO;
import ${packageUrl}.common.vo.${moduleName}UpdateVO;
import ${packageUrl}.mapper.${moduleName}Mapper;
import ${packageUrl}.service.api.${moduleName}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${tableComment}Controller接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/${lowerModuleName}")
@Api(value = "/${lowerModuleName}", description = "${tableComment}Controller接口")
public interface ${moduleName}Controller extends AbstractBaseController<${primary["columnType"]}, ${moduleName}Entity, ${moduleName}UpdateVO, ${moduleName}QueryVO> {

    @Autowired
    private ${moduleName}Service ${lowerModuleName}Service;

    @Override
    public ${moduleName}Service getService() {
        return this.${lowerModuleName}Service;
    }
}
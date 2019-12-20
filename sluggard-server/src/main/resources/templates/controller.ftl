package ${project.basePackage}.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${project.basePackage}.entity.${table.moduleName}Entity;
import ${project.basePackage}.service.${table.moduleName}Service;
import ${project.basePackage}.vo.${table.moduleName}QueryVO;
import ${project.basePackage}.vo.${table.moduleName}UpdateVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ${table.tableComment}Controller接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/${table.moduleName ? uncap_first}")
@Api(value = "/${table.moduleName ? uncap_first}", description = "${table.tableComment}Controller接口")
public class ${table.moduleName}Controller {

    @Autowired
    private ${table.moduleName}Service ${table.moduleName ? uncap_first}Service;
}
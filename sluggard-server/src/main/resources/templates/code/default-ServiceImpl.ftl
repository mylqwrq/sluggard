package ${project.basePackage}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${project.basePackage}.common.entity.${table.moduleName}Entity;
import ${project.basePackage}.common.query.${table.moduleName}Query;
import ${project.basePackage}.core.base.service.AbstractBaseService;
import ${project.basePackage}.service.api.${table.moduleName}Service;
import ${project.basePackage}.service.dao.${table.moduleName}Mapper;

/**
 * ${table.tableComment}Service实现
 *
 * @author ${author}
 * @version 1.0.0
 * @since ${date}
 */
@Service
public class ${table.moduleName}ServiceImpl extends AbstractBaseService<${table.moduleName}Query, ${table.moduleName}Entity> implements ${table.moduleName}Service {

    @Autowired
    private ${table.moduleName}Mapper ${table.moduleName ? uncap_first}Mapper;

    @Override
    public ${table.moduleName}Mapper getMapper() {
        return this.${table.moduleName ? uncap_first}Mapper;
    }
}
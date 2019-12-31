package ${project.basePackage}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylq.core.base.service.AbstractBaseService;
import ${project.basePackage}.common.dto.${table.moduleName}QueryDTO;
import ${project.basePackage}.common.entity.${table.moduleName}Entity;
import ${project.basePackage}.service.api.${table.moduleName}Service;
import ${project.basePackage}.service.dao.${table.moduleName}Mapper;

/**
 * ${table.tableComment}Service实现
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@Service
public class ${table.moduleName}ServiceImpl extends AbstractBaseService<${table.moduleName}QueryDTO, ${table.moduleName}Entity> implements ${table.moduleName}Service {

    @Autowired
    private ${table.moduleName}Mapper ${table.moduleName ? uncap_first}Mapper;

    @Override
    public ${table.moduleName}Mapper getMapper() {
        return this.${table.moduleName ? uncap_first}Mapper;
    }
}
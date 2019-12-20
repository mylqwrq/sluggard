package ${project.basePackage}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${project.basePackage}.dao.${table.moduleName}Mapper;
import ${project.basePackage}.entity.${table.moduleName}Entity;
import ${project.basePackage}.service.${table.moduleName}Service;
import ${project.basePackage}.vo.${table.moduleName}QueryVO;
import ${project.basePackage}.vo.${table.moduleName}UpdateVO;

/**
 * ${table.tableComment}Service实现
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@Service
public class ${table.moduleName}ServiceImpl implements ${table.moduleName}Service {

    @Autowired
    private ${table.moduleName}Mapper ${table.moduleName ? uncap_first}Mapper;
}
package ${project.basePackage}.service.api;

import ${project.basePackage}.common.entity.${table.moduleName}Entity;
import ${project.basePackage}.common.query.${table.moduleName}Query;
import ${project.basePackage}.core.base.service.BaseService;

/**
 * ${table.tableComment}Service接口
 *
 * @author ${author}
 * @version 1.0.0
 * @since ${date}
 */
public interface ${table.moduleName}Service extends BaseService<${table.moduleName}Query, ${table.moduleName}Entity> {
}
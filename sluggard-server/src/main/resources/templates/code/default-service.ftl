package ${project.basePackage}.service.api;

import com.mylq.core.base.service.BaseService;
import ${project.basePackage}.common.entity.${table.moduleName}Entity;
import ${project.basePackage}.common.query.${table.moduleName}Query;

/**
 * ${table.tableComment}Service接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
public interface ${table.moduleName}Service extends BaseService<${table.moduleName}Query, ${table.moduleName}Entity> {
}
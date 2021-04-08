package ${project.basePackage}.service.dao;

import org.apache.ibatis.annotations.Mapper;

import ${project.basePackage}.common.entity.${table.moduleName}Entity;
import ${project.basePackage}.common.query.${table.moduleName}Query;
import ${project.basePackage}.core.base.dao.BaseMapper;

/**
 * ${table.tableComment}Mapper接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@Mapper
public interface ${table.moduleName}Mapper extends BaseMapper<${table.moduleName}Query, ${table.moduleName}Entity> {
}
package ${project.basePackage}.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mylq.core.base.dao.BaseMapper;
import ${project.basePackage}.entity.${table.moduleName}Entity;

import ${project.basePackage}.entity.${table.moduleName}Entity;

/**
 * ${table.tableComment}Mapper接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@Mapper
public interface ${table.moduleName}Mapper extends BaseMapper<${table.moduleName}Entity> {
}
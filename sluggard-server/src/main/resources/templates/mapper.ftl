package ${project.basePackage}.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ${project.basePackage}.entity.${table.moduleName}Entity;
import ${project.basePackage}.vo.${table.moduleName}QueryVO;
import ${project.basePackage}.vo.${table.moduleName}UpdateVO;

/**
 * ${table.tableComment}Mapper接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@Mapper
public interface ${table.moduleName}Mapper {

   /**
   * 根据参数查询结果行数
   *
   * @param query 查询实体
   * @return 结果行数
   */
   Long selectCount(${table.moduleName}QueryVO query);

   /**
   * 根据参数查询结果列表
   *
   * @param query 查询实体
   * @return 结果列表
   */
   List<${table.moduleName}Entity> select(${table.moduleName}QueryVO query);

    /**
    * 根据主键查询结果实体
    *
    * @param id 主键
    * @return 结果实体
    */
    ${table.moduleName}Entity selectById(${primary.columnType} id);

    /**
    * 根据主键删除数据行
    *
    * @param id 主键
    * @return 受影响的行数
    */
    int deleteById(${primary.columnType} id);

    /**
    * 根据参数更新数据行
    *
    * @param update 更新实体
    * @return 受影响的行数
    */
    int updateById(${table.moduleName}UpdateVO update);

    /**
    * 根据参数插入数据行
    *
    * @param update 更新实体
    */
    int insert(${table.moduleName}UpdateVO update);
}
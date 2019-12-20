package ${project.basePackage}.service;

import java.util.List;

import ${project.basePackage}.entity.${table.moduleName}Entity;
import ${project.basePackage}.vo.${table.moduleName}QueryVO;
import ${project.basePackage}.vo.${table.moduleName}UpdateVO;

/**
 * ${tableComment}Service接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
public interface ${table.moduleName}Service  {

    /**
    * 根据参数查询结果行数
    *
    * @param query 查询实体
    * @return 结果行数
    */
    Long queryCount(${table.moduleName}QueryVO query);

    /**
    * 根据参数查询结果列表
    *
    * @param query 查询实体
    * @return 结果列表
    */
    List<${table.moduleName}Entity> queryList(${table.moduleName}QueryVO query);

    /**
    * 根据参数分页查询结果列表
    *
    * @param query 查询实体
    * @return 分页结果
    */
    PageResult<${table.moduleName}Entity> queryPage(${table.moduleName}QueryVO query);

    /**
    * 根据主键查询结果实体
    *
    * @param id 主键
    * @return 结果实体
    */
    ${table.moduleName}Entity queryById(${primary.columnType} id);

    /**
    * 根据主键删除数据行
    *
    * @param id 主键
    * @return 受影响的行数
    */
    int deleteById(K id);

    /**
    * 根据主键批量删除数据行
    *
    * @param ids 主键
    * @return 受影响的行数
    */
    int deleteBatch(String ids);

    /**
    * 根据参数更新数据行
    *
    * @param update 更新实体
    * @return 受影响的行数
    */
    int updateById(${table.moduleName}UpdateVO update);

    /**
    * 根据参数批量更新数据行
    *
    * @param updateList 更新实体列表
    * @return 受影响的行数
    */
    int updateBatch(List<${table.moduleName}UpdateVO> updateList);

    /**
    * 根据参数插入数据行
    *
    * @param update 更新实体
    */
    int save(${table.moduleName}UpdateVO update);

    /**
    * 根据参数批量插入数据行
    *
    * @param updateList 更新实体列表
    */
    int saveBatch(List<${table.moduleName}UpdateVO> updateList);
}
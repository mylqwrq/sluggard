package ${project.basePackage}.core.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper<Q, T> {

    Long selectCount(Q query);

    List<T> selectList(Q query);

    T selectById(Serializable id);

    List<T> selectByIds(@Param("ids") Collection<Serializable> ids);

    int deleteById(Serializable id);

    int deleteByIds(@Param("ids") Collection<Serializable> ids);

    int updateById(T entity);

    int updateBatch(@Param("entities") Collection<T> entities);

    int insert(T entity);

    int insertBatch(@Param("entities") Collection<T> entities);
}

package ${project.basePackage}.core.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import ${project.basePackage}.core.base.result.ServiceResult;

public interface BaseService<Q, T> {

ServiceResult<List<T>> queryPage(Q query, int pageNum, int pageSize);

    Long queryCount(Q query);

    List<T> queryList(Q query);

    T queryById(Serializable id);

    List<T> queryByIds(Collection<Serializable> ids);

    int deleteById(Serializable id);

    int deleteByIds(Collection<Serializable> ids);

    int updateById(T entity);

    int updateBatch(Collection<T> entities);

    int save(T entity);

    int saveBatch(Collection<T> entities);
}

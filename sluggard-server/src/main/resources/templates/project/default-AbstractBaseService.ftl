package ${project.basePackage}.core.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${project.basePackage}.core.base.dao.BaseMapper;
import ${project.basePackage}.core.base.result.ServiceResult;

public abstract class AbstractBaseService<Q, T> implements BaseService<Q, T> {

    protected abstract BaseMapper<Q, T> getMapper();

    @Override
    public ServiceResult<List<T>> queryPage(Q query, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> resultList = getMapper().selectList(query);
        return ServiceResult.success(resultList, new PageInfo<>(resultList).getTotal(), pageNum, pageSize);
    }

    @Override
    public Long queryCount(Q query) {
        return getMapper().selectCount(query);
    }

    @Override
    public List<T> queryList(Q query) {
        return getMapper().selectList(query);
    }

    @Override
    public T queryById(Serializable id) {
        return getMapper().selectById(id);
    }

    @Override
    public List<T> queryByIds(Collection<Serializable> ids) {
        return getMapper().selectByIds(ids);
    }

    @Override
    public int deleteById(Serializable id) {
        return getMapper().deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Serializable> ids) {
        return getMapper().deleteByIds(ids);
    }

    @Override
    public int updateById(T entity) {
        return getMapper().updateById(entity);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int updateBatch(Collection<T> entities) {
        return getMapper().updateBatch(entities);
    }

    @Override
    public int save(T entity) {
        return getMapper().insert(entity);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int saveBatch(Collection<T> entities) {
        return getMapper().insertBatch(entities);
    }
}

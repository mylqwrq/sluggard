package com.mylq.sluggard.core.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylq.sluggard.core.basic.service.MappingService;
import com.mylq.sluggard.core.common.util.StringUtil;
import com.mylq.sluggard.core.db.dao.DbDao;
import com.mylq.sluggard.core.db.entity.ColumnEntity;
import com.mylq.sluggard.core.db.entity.TableEntity;
import com.mylq.sluggard.core.db.vo.DbVO;

import lombok.NonNull;

/**
 * 数据库服务
 *
 * @author WangRunQian
 * @date 2019/11/29
 * @since 1.0.0
 */
@Service
public class DbService {

    @Autowired
    private DbDao dao;
    @Autowired
    private MappingService mappingService;

    /**
     * 根据参数得到表信息列表
     *
     * @param dbVO 数据库信息
     * @param tableName 表名称
     * @return 表信息列表
     */
    public List<TableEntity> getTablesList(@NonNull DbVO dbVO, String tableName) {
        // 获得所有表的基本信息
        List<TableEntity> list = dao.selectTableList(dbVO, tableName);
        // 遍历表获得对应的模块名称
        for (TableEntity tableEntity : list) {
            tableEntity.setModuleName(StringUtil.underlineToHump(tableEntity.getTableName(), true));
        }
        return list;
    }

    /**
     * 根据参数得到列信息列表
     *
     * @param dbVO 数据库信息
     * @param tableName 表名称
     * @return 列信息列表
     */
    public List<ColumnEntity> getColumnsList(@NonNull DbVO dbVO, String tableName) {
        // 获得所有列的基本信息
        List<ColumnEntity> list = dao.selectColumnList(dbVO, tableName);
        // 遍历列获得对应的列类型、Java类型和Jdbc类型
        for (ColumnEntity columnEntity : list) {
            columnEntity.setFieldName(StringUtil.underlineToHump(columnEntity.getColumnName(), false));
            columnEntity.setJavaType(mappingService.getJavaType(dbVO.getDbType().getName(), columnEntity.getDataType()));
            columnEntity.setJdbcType(mappingService.getJdbcType(dbVO.getDbType().getName(), columnEntity.getDataType()));
        }
        return list;
    }

    /**
     * 测试DB连接
     *
     * @param dbVO 数据库信息
     * @return 是否连接成功
     */
    public Boolean test(@NonNull DbVO dbVO) {
        return dao.testConnection(dbVO);
    }
}

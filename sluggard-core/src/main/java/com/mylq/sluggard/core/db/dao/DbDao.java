package com.mylq.sluggard.core.db.dao;

import com.google.common.base.Strings;
import com.mylq.sluggard.core.common.base.exception.SluggardBusinessException;
import com.mylq.sluggard.core.db.entity.ColumnEntity;
import com.mylq.sluggard.core.db.entity.TableEntity;
import com.mylq.sluggard.core.db.provider.DbSqlProvider;
import com.mylq.sluggard.core.db.util.DBUtil;
import com.mylq.sluggard.core.db.vo.DbVO;
import lombok.NonNull;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库DAO
 *
 * @author WangRunQian
 * @date 2019/11/29
 * @since 1.0.0
 */
@Repository
public class DbDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbDao.class);
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    /**
     * 根据表名称模糊查询表信息列表
     *
     * @param dbVO 数据库信息
     * @param tableName 表名称
     * @return 表信息列表
     */
    public List<TableEntity> selectTableList(@NonNull DbVO dbVO, String tableName) {
        try (Connection connection = DBUtil.openConnection(dbVO)) {
            // 得到SQL语句
            String sql = DbSqlProvider.getSelectTableListSqlStr(dbVO.getDbType());
            // 执行SQL语句
            return QUERY_RUNNER.query(connection, sql, new BeanListHandler<>(TableEntity.class), Strings.nullToEmpty(tableName));
        } catch (SQLException e) {
            LOGGER.error("Failed to get table list. Db: {}, tableName: {}.", dbVO, tableName, e);
            throw new SluggardBusinessException("Failed to get table list.");
        }
    }

    /**
     * 根据表名称查询列信息列表
     *
     * @param dbVO 数据库信息
     * @param tableName 表名称
     * @return 列信息列表
     */
    public List<ColumnEntity> selectColumnList(@NonNull DbVO dbVO, String tableName) {
        try (Connection connection = DBUtil.openConnection(dbVO)) {
            // 得到SQL语句
            String sql = DbSqlProvider.getSelectColumnListSqlStr(dbVO.getDbType());
            // 执行SQL语句
            return QUERY_RUNNER.query(connection, sql, new BeanListHandler<>(ColumnEntity.class), Strings.nullToEmpty(tableName));
        } catch (SQLException e) {
            LOGGER.error("Failed to get column list. Db: {}, tableName: {}.", dbVO, tableName, e);
            throw new SluggardBusinessException("Failed to get column list.");
        }
    }
}

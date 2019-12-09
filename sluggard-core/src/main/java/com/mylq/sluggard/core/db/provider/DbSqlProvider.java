package com.mylq.sluggard.core.db.provider;

import com.mylq.sluggard.core.common.base.exception.SluggardCoreException;
import com.mylq.sluggard.core.common.enums.DbTypeEnum;
import lombok.NonNull;

/**
 * 数据库SQL语句提供类
 *
 * @author WangRunQian
 * @date 2019/11/29
 * @since 1.0.0
 */
public class DbSqlProvider {

    /**
     * 根据数据库类型提供查询表信息的SQL语句
     *
     * @param dbType 数据库类型
     * @return 查询表信息的SQL语句
     */
    public static String getSelectTableListSqlStr(@NonNull DbTypeEnum dbType) {
        if (DbTypeEnum.MYSQL.equals(dbType)) {
            return "select table_name as tableName, " + "table_comment as tableComment "
                    + "from information_schema.tables " + "where table_schema = (select database()) "
                    + "and table_name like concat('%', ?, '%') " + "order by table_name";
        } else if (DbTypeEnum.ORACLE.equals(dbType)) {
            return "SELECT TABLE_NAME AS tableName, " + "COMMENTS AS tableComment " + "FROM USER_TAB_COMMENTS "
                    + "WHERE TABLE_TYPE = 'TABLE' " + "AND TABLE_NAME LIKE CONCAT('%', CONCAT(?, '%')) "
                    + "ORDER BY TABLE_NAME";
        } else {
            throw new SluggardCoreException("Unsupported dbType: {0}.", dbType);
        }
    }

    /**
     * 根据数据库类型提供查询列信息的SQL语句
     *
     * @param dbType 数据库类型
     * @return 查询列信息的SQL语句
     */
    public static String getSelectColumnListSqlStr(@NonNull DbTypeEnum dbType) {
        if (DbTypeEnum.MYSQL.equals(dbType)) {
            return "select column_name as columnName, " + "data_type as dataType, "
                    + "column_default as columnDefault, " + "is_nullable as isNullable, "
                    + "column_type as columnLength, " + "column_key as columnKey, " + "column_comment as columnComment "
                    + "from information_schema.columns " + "where table_schema = (select database()) "
                    + "and table_name = ? " + "order by ordinal_position";
        } else if (DbTypeEnum.ORACLE.equals(dbType)) {
            return "SELECT T1.COLUMN_NAME AS columnName, " + "T1.DATA_TYPE AS dataType, "
                    + "T1.DATA_DEFAULT AS columnDefault, " + "T1.NULLABLE AS isNullable, "
                    + "T1.DATA_LENGTH AS columnLength, " + "T5.CONSTRAINT_TYPE AS columnKey, "
                    + "T2.COMMENTS AS columnComment " + "FROM USER_TAB_COLUMNS T1 " + "LEFT JOIN USER_COL_COMMENTS T2 "
                    + "ON T1.TABLE_NAME = T2.TABLE_NAME " + "AND T1.COLUMN_NAME = T2.COLUMN_NAME " + "LEFT JOIN "
                    + "(SELECT " + "T3.COLUMN_NAME, " + "T4.CONSTRAINT_TYPE, " + "T4.TABLE_NAME "
                    + "FROM USER_CONS_COLUMNS T3 " + "LEFT JOIN USER_CONSTRAINTS T4 "
                    + "ON T3.CONSTRAINT_NAME = T4.CONSTRAINT_NAME " + "WHERE T4.CONSTRAINT_TYPE = 'P' "
                    + ") T5 ON T1.COLUMN_NAME = T5.COLUMN_NAME " + "AND T1.TABLE_NAME = T5.TABLE_NAME "
                    + "WHERE T1.TABLE_NAME = ? " + "ORDER BY T1.COLUMN_ID";
        } else {
            throw new SluggardCoreException("Unsupported dbType: {0}.", dbType);
        }
    }
}

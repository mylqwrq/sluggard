package com.mylq.sluggard.core.db.util;

import com.mylq.sluggard.core.common.base.exception.SluggardCoreException;
import com.mylq.sluggard.core.db.vo.DbVO;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * DB工具类
 *
 * @author WangRunQian
 * @date 2019/12/4
 * @since 1.0.0
 */
public class DBUtil {

    private DBUtil() {
    }

    /**
     * 打开数据库连接
     *
     * @param dbVO 数据库信息
     * @return 数据库连接
     * @throws SQLException SQLException
     */
    public static Connection openConnection(@NonNull DbVO dbVO) throws SQLException {
        if (Objects.isNull(dbVO.getDbType())) {
            throw new NullPointerException("Field dbType must not be null.");
        }
        try {
            Class.forName(dbVO.getDbType().getDriver());
        } catch (ClassNotFoundException e) {
            throw new SluggardCoreException("Unsupported db driver: {0}.", dbVO.getDbType().getDriver());
        }
        return DriverManager.getConnection(dbVO.getUrl(), dbVO.getUser(), dbVO.getPwd());
    }
}

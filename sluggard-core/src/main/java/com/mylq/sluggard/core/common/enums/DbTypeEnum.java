package com.mylq.sluggard.core.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mylq.sluggard.core.common.base.enums.BaseEnum;
import com.mylq.sluggard.core.common.base.exception.SluggardCoreException;
import com.mylq.sluggard.core.common.base.serializer.BaseEnumSerializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 数据库类型枚举类
 *
 * @author WangRunQian
 * @date 2019/11/29
 * @since 1.0.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@JsonSerialize(using = BaseEnumSerializer.class)
public enum DbTypeEnum implements BaseEnum<Integer> {

    /**
     * MySQL
     */
    MYSQL(0, "mysql", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://{0}:{1}/{2}"),
    /**
     * Oracle
     */
    ORACLE(1, "oracle", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@{0}:{1}:{2}");

    private Integer id;
    private String name;
    private String driver;
    private String url;

    @JsonCreator
    public static DbTypeEnum get(Integer id) {
        for (DbTypeEnum item : values()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new SluggardCoreException("Unsupported db type: {0}.", id);
    }

    public static DbTypeEnum get(String name) {
        for (DbTypeEnum item : values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new SluggardCoreException("Unsupported db type: {0}.", name);
    }
}

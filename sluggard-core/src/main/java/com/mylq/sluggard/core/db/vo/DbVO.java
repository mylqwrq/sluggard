package com.mylq.sluggard.core.db.vo;

import com.mylq.sluggard.core.common.enums.DbTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * DB视图对象
 *
 * @author WangRunQian
 * @date 2019/11/29
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class DbVO implements Serializable {

    /**
     * 类型
     */
    private DbTypeEnum dbType;
    /**
     * 地址
     */
    private String ip;
    /**
     * 端口
     */
    private String port;
    /**
     * 名称/实例
     */
    private String name;
    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String pwd;

    public String getUrl() {
        if (Objects.isNull(dbType)) {
            return null;
        }
        return MessageFormat.format(dbType.getUrl(), ip, port, name);
    }
}

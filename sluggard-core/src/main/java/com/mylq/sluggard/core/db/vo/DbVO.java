package com.mylq.sluggard.core.db.vo;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Objects;

import com.mylq.sluggard.core.common.enums.DbTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    private DbTypeEnum dbType;

    private String ip;

    private String port;

    private String name;

    private String user;

    private String pwd;

    private String ignorePrefix;

    private String ignoreSuffix;

    public String getUrl() {
        if (Objects.isNull(dbType)) {
            return null;
        }
        return MessageFormat.format(dbType.getUrl(), ip, port, name);
    }
}

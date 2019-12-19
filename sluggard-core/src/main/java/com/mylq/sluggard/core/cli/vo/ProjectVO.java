package com.mylq.sluggard.core.cli.vo;

import java.io.Serializable;
import java.util.List;

import com.mylq.sluggard.core.db.vo.DbVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Project视图对象
 *
 * @author WangRunQian
 * @date 2019/12/12
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ProjectVO implements Serializable {

    private String name;

    private String description;

    private String groupId;

    private String artifactId;

    private String version;

    private String packaging;

    private DbVO dbVO;

    private List<String> templateNames;

    private List<ConfigVO> configs;
}

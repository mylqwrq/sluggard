package com.mylq.sluggard.core.cli.vo;

import java.util.List;

import com.mylq.sluggard.core.db.vo.DbVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 项目信息视图对象
 *
 * @author WangRunQian
 * @date 2019/12/12
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectVO extends ProjectBasicVO {

    private DbVO dbVO;

    private List<String> templateNames;

    private List<TemplateConfigVO> templateConfigs;
}

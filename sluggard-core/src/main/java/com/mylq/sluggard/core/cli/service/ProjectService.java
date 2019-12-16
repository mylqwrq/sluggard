package com.mylq.sluggard.core.cli.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.mylq.sluggard.core.basic.util.BasicUtil;
import com.mylq.sluggard.core.cli.factory.ProjectPropertyFactory;
import com.mylq.sluggard.core.cli.vo.ProjectVO;
import com.mylq.sluggard.core.common.util.JsonUtil;

import lombok.NonNull;

/**
 * Project服务
 *
 * @author WangRunQian
 * @date 2019/12/12
 * @since 1.0.0
 */
@Service
public class ProjectService {

    public List<ProjectVO> getList(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return ProjectPropertyFactory.getList();
        } else {
            return ProjectPropertyFactory.getList().stream().filter(t -> (t.getName().contains(name)))
                    .collect(Collectors.toList());
        }
    }

    public ProjectVO getByName(@NonNull String name) {
        return ProjectPropertyFactory.get(name);
    }

    public void save(@NonNull ProjectVO projectVO, boolean isAbsent) {
        BasicUtil.requireNotNullOrBlank("name", projectVO.getName());
        BasicUtil.requireNotNull("templateNames", projectVO.getTemplateNames());
        BasicUtil.requireNotNull("dbVO", projectVO.getDbVO());
        BasicUtil.requireNotNull("configs", projectVO.getConfigs());

        ProjectPropertyFactory.set(projectVO.getName(), JsonUtil.toJsonString(projectVO), isAbsent);
    }

    public void delete(String name) {
        BasicUtil.requireNotNullOrBlank("name", name);
        ProjectPropertyFactory.remove(name);
    }
}

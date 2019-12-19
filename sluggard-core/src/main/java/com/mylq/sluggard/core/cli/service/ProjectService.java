package com.mylq.sluggard.core.cli.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mylq.sluggard.core.basic.util.BasicUtil;
import com.mylq.sluggard.core.cli.factory.ProjectPropertyFactory;
import com.mylq.sluggard.core.cli.vo.ProjectVO;

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
        return ProjectPropertyFactory.getList(name);
    }

    public ProjectVO get(String name) {
        return ProjectPropertyFactory.get(name);
    }

    public void save(@NonNull ProjectVO projectVO, boolean isAbsent) {
        BasicUtil.requireNotNullOrBlank("name", projectVO.getName());
        BasicUtil.requireNotNullOrBlank("description", projectVO.getDescription());
        BasicUtil.requireNotNullOrBlank("groupId", projectVO.getGroupId());
        BasicUtil.requireNotNullOrBlank("artifactId", projectVO.getArtifactId());
        BasicUtil.requireNotNullOrBlank("version", projectVO.getVersion());
        BasicUtil.requireNotNullOrBlank("packaging", projectVO.getPackaging());
        BasicUtil.requireNotNull("dbVO", projectVO.getDbVO());
        BasicUtil.requireNotNull("templateNames", projectVO.getTemplateNames());
        BasicUtil.requireNotNull("configs", projectVO.getConfigs());
        ProjectPropertyFactory.set(projectVO, isAbsent);
    }

    public void delete(String name) {
        ProjectPropertyFactory.remove(name);
    }
}

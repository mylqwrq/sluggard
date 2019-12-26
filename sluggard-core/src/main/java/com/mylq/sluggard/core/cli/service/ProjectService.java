package com.mylq.sluggard.core.cli.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mylq.sluggard.core.basic.util.BasicUtil;
import com.mylq.sluggard.core.cli.factory.ProjectPropertyFactory;
import com.mylq.sluggard.core.cli.vo.ProjectBasicVO;
import com.mylq.sluggard.core.cli.vo.ProjectVO;
import com.mylq.sluggard.core.cli.vo.TemplateConfigVO;
import com.mylq.sluggard.core.db.entity.TableEntity;
import com.mylq.sluggard.core.db.vo.DbVO;

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
        BasicUtil.requireNotNullOrBlank("groupId", projectVO.getGroupId());
        BasicUtil.requireNotNullOrBlank("artifactId", projectVO.getArtifactId());
        BasicUtil.requireNotNullOrBlank("version", projectVO.getVersion());
        BasicUtil.requireNotNullOrBlank("packaging", projectVO.getPackaging());
        BasicUtil.requireNotNullOrBlank("description", projectVO.getDescription());
        BasicUtil.requireNotNullOrBlank("basePackage", projectVO.getBasePackage());
        BasicUtil.requireNotNullOrBlank("serverPort", projectVO.getServerPort());
        BasicUtil.requireNotNullOrBlank("serverPath", projectVO.getServerPath());
        BasicUtil.requireNotNull("db type", projectVO.getDbVO().getDbType());
        BasicUtil.requireNotNullOrBlank("db ip", projectVO.getDbVO().getIp());
        BasicUtil.requireNotNullOrBlank("db port", projectVO.getDbVO().getPort());
        BasicUtil.requireNotNullOrBlank("db name", projectVO.getDbVO().getName());
        BasicUtil.requireNotNullOrBlank("db user", projectVO.getDbVO().getUser());
        BasicUtil.requireNotNullOrBlank("db pwd", projectVO.getDbVO().getPwd());
        ProjectPropertyFactory.set(projectVO, isAbsent);
    }

    public void delete(String name) {
        ProjectPropertyFactory.remove(name);
    }

    public String generator(@NonNull List<String> templateNames, @NonNull List<TemplateConfigVO> templateConfigs,
            @NonNull ProjectBasicVO project, @NonNull DbVO dataSource, @NonNull List<TableEntity> tables) {

        String folderDir = UUID.randomUUID().toString().replaceAll("-", "");

        return folderDir;
    }
}

package com.mylq.sluggard.core.cli.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.mylq.sluggard.core.basic.service.TemplateService;
import com.mylq.sluggard.core.basic.vo.TemplateVO;
import com.mylq.sluggard.core.cli.vo.ProjectBasicVO;
import com.mylq.sluggard.core.cli.vo.TemplateConfigVO;
import com.mylq.sluggard.core.common.enums.FileTypeEnum;
import com.mylq.sluggard.core.common.enums.TemplateTypeEnum;
import com.mylq.sluggard.core.common.util.DateUtil;
import com.mylq.sluggard.core.common.util.StringUtil;
import com.mylq.sluggard.core.db.entity.ColumnEntity;
import com.mylq.sluggard.core.db.entity.TableEntity;
import com.mylq.sluggard.core.db.service.DbService;
import com.mylq.sluggard.core.db.vo.DbVO;

import freemarker.template.TemplateException;
import lombok.NonNull;

/**
 * 数据服务
 *
 * @author WangRunQian
 * @date 2019/12/17
 * @since 1.0.0
 */
@Service
public class DataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TemplateService templateService;
    @Autowired
    private DbService dbService;

    /**
     * 单表代码生成
     *
     * @param folderDir 文件生成目录
     * @param codeTemplateNames 代码模板名称列表
     * @param templateConfigs 模板配置列表
     * @param project 项目信息
     * @param dataSource 数据源信息
     * @param table 表信息
     * @param columns 列信息集合
     */
    public void generatorCode(final String folderDir, @NonNull List<String> codeTemplateNames,
            @NonNull List<TemplateConfigVO> templateConfigs, @NonNull ProjectBasicVO project, @NonNull DbVO dataSource,
            @NonNull TableEntity table, @NonNull List<ColumnEntity> columns) {

        if (codeTemplateNames.isEmpty()) {
            throw new IllegalArgumentException("Code templates must not be empty.");
        }
        if (columns.isEmpty()) {
            throw new IllegalArgumentException("Columns must not be empty.");
        }

        // 模板参数
        Map<String, Object> codeDataModel = getCodeDataModel(templateConfigs, project, dataSource, table, columns);

        // 遍历代码模板
        for (String templateName : codeTemplateNames) {
            createFile(folderDir, table.getModuleName(), templateName, TemplateTypeEnum.CODE, codeDataModel);
        }
    }

    /**
     * 项目生成
     *
     * @param folderDir 文件生成目录
     * @param projectTemplateNames 项目模板名称列表
     * @param codeTemplateNames 代码模板名称列表
     * @param templateConfigs 模板配置列表
     * @param project 项目信息
     * @param dataSource 数据源信息
     * @param tables 表信息集合
     */
    public void generateProject(final String folderDir, @NonNull List<String> projectTemplateNames,
            @NonNull List<String> codeTemplateNames, @NonNull List<TemplateConfigVO> templateConfigs,
            @NonNull ProjectBasicVO project, @NonNull DbVO dataSource, @NonNull List<TableEntity> tables) {

        if (projectTemplateNames.isEmpty()) {
            throw new IllegalArgumentException("Project templates must not be empty.");
        }
        if (codeTemplateNames.isEmpty()) {
            throw new IllegalArgumentException("Code templates must not be empty.");
        }
        if (tables.isEmpty()) {
            throw new IllegalArgumentException("Tables must not be empty.");
        }

        // 模板参数
        if (Strings.isNullOrEmpty(project.getName())) {
            project.setName(project.getArtifactId());
        }
        Map<String, Object> projectDataModel = getProjectDataModel(templateConfigs, project, dataSource);

        // 遍历项目模板
        for (String templateName : projectTemplateNames) {
            createFile(folderDir, "", templateName, TemplateTypeEnum.PROJECT, projectDataModel);
        }

        for (TableEntity table : tables) {
            List<ColumnEntity> columns = dbService.getColumnsList(dataSource, table.getTableName());

            // 模板参数
            Map<String, Object> codeDataModel = getCodeDataModel(templateConfigs, project, dataSource, table, columns);

            // 遍历代码模板
            for (String templateName : codeTemplateNames) {
                createFile(folderDir, table.getModuleName(), templateName, TemplateTypeEnum.CODE, codeDataModel);
            }
        }
    }

    private void createFile(final String folderDir, String moduleName, String templateName,
            TemplateTypeEnum templateType, Map<String, Object> dataModel) {
        try {
            TemplateVO template = templateService.get(templateType, templateName);
            String fileName = template.getFileNamePrefix() + moduleName + template.getFileNameSuffix()
                    + template.getFileType().getName();
            // 文件父目录：如果是java文件则根据包路径生成目录
            String parentPath = template.getFileRelativePath();
            if (FileTypeEnum.JAVA.equals(template.getFileType())) {
                String sourceCode = templateService.getTextByTemplate(template.getTemplateType(), template.getName(),
                        dataModel);
                String packagePath = sourceCode.split(";")[0].replaceFirst("package ", "");
                parentPath += StringUtil.getFilePathByPackage(packagePath);
            }
            // 文件路径
            String filePath = folderDir + StringUtil.STR_FILE_SEPARATOR + parentPath + fileName;
            // 根据模板生成文件
            templateService.saveFileByTemplate(template.getTemplateType(), filePath, template.getName(), dataModel);
        } catch (IOException | TemplateException e) {
            if (TemplateTypeEnum.CODE.equals(templateType)) {
                logger.error("Failed to create file by code template {} and module {}.", templateName, moduleName, e);
            } else if (TemplateTypeEnum.PROJECT.equals(templateType)) {
                logger.error("Failed to create file by project template {}.", templateName, e);
            }
        }
    }

    private Map<String, Object> getProjectDataModel(@NonNull List<TemplateConfigVO> templateConfigs,
            @NonNull ProjectBasicVO project, @NonNull DbVO dataSource) {

        // 构建模板中替换的数据模型
        Map<String, Object> dataModel = Maps.newHashMap();
        for (TemplateConfigVO config : templateConfigs) {
            dataModel.put(config.getKey(), config.getValue());
        }

        dataModel.put("project", project);
        dataModel.put("datasource", dataSource);
        dataModel.put("date", DateUtil.getNowDateStr());

        return dataModel;
    }

    private Map<String, Object> getCodeDataModel(@NonNull List<TemplateConfigVO> templateConfigs,
            @NonNull ProjectBasicVO project, @NonNull DbVO dataSource, @NonNull TableEntity table,
            @NonNull List<ColumnEntity> columns) {

        // 构建模板中替换的数据模型
        Map<String, Object> dataModel = Maps.newHashMap();
        for (TemplateConfigVO config : templateConfigs) {
            dataModel.put(config.getKey(), config.getValue());
        }

        // 获取首个主键列
        List<ColumnEntity> primaryList = columns.stream().filter(c -> (c.getColumnKey().contains("P")))
                .collect(Collectors.toList());
        ColumnEntity primary = primaryList.isEmpty() ? columns.get(0) : primaryList.get(0);

        dataModel.put("table", table);
        dataModel.put("project", project);
        dataModel.put("dataSource", dataSource);
        dataModel.put("primary", primary);
        dataModel.put("columns", columns);
        dataModel.put("javaTypeImports", columns.stream().map(ColumnEntity::getJavaType).collect(Collectors.toSet()));
        dataModel.put("date", DateUtil.getNowDateStr());

        return dataModel;
    }
}

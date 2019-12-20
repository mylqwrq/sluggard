package com.mylq.sluggard.core.cli.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.mylq.sluggard.core.basic.service.TemplateService;
import com.mylq.sluggard.core.basic.vo.TemplateVO;
import com.mylq.sluggard.core.cli.vo.ProjectBasicVO;
import com.mylq.sluggard.core.cli.vo.TemplateConfigVO;
import com.mylq.sluggard.core.common.enums.FileTypeEnum;
import com.mylq.sluggard.core.common.util.DateUtil;
import com.mylq.sluggard.core.common.util.StringUtil;
import com.mylq.sluggard.core.db.entity.ColumnEntity;
import com.mylq.sluggard.core.db.entity.TableEntity;
import com.mylq.sluggard.core.db.vo.DbVO;

import freemarker.template.TemplateException;
import lombok.NonNull;

/**
 * Code服务
 *
 * @author WangRunQian
 * @date 2019/12/17
 * @since 1.0.0
 */
@Service
public class CodeService {

    @Autowired
    private TemplateService templateService;

    /**
     * 单表代码生成
     * 
     * @param templateNames 模板名称列表
     * @param templateConfigs 模板配置列表
     * @param project 项目信息
     * @param dataSource 数据源信息
     * @param table 表信息
     * @param columns 列信息集合
     * @return 生成代码的根目录
     * @throws IOException IO异常
     * @throws TemplateException Template异常
     */
    public String generator(@NonNull List<String> templateNames, @NonNull List<TemplateConfigVO> templateConfigs,
            @NonNull ProjectBasicVO project, @NonNull DbVO dataSource, @NonNull TableEntity table,
            @NonNull List<ColumnEntity> columns) throws IOException, TemplateException {

        if (templateNames.isEmpty()) {
            throw new IllegalArgumentException("Templates must not be empty.");
        }
        if (columns.isEmpty()) {
            throw new IllegalArgumentException("Columns must not be empty.");
        }

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

        // 得到UUID作为文件的父目录
        String strUid = UUID.randomUUID().toString().replaceAll("-", "");

        // 遍历模板
        for (String templateName : templateNames) {
            TemplateVO template = templateService.get(templateName);
            String fileName = template.getFileNamePrefix() + table.getModuleName() + template.getFileNameSuffix()
                    + template.getFileType().getName();
            // 文件父目录：如果是java文件则根据包路径生成目录
            String parentPath = template.getFileRelativePath();
            if (FileTypeEnum.JAVA.equals(template.getFileType())) {
                String sourceCode = templateService.getTextByTemplate(template.getName(), dataModel);
                String packagePath = sourceCode.split(";")[0].replaceFirst("package ", "");
                parentPath += StringUtil.getFilePathByPackage(packagePath);
            }
            // 文件路径
            String filePath = strUid + StringUtil.STR_FILE_SEPARATOR + parentPath + fileName;
            // 根据模板生成文件
            templateService.saveFileByTemplate(filePath, template.getName(), dataModel);
        }

        return strUid;
    }
}

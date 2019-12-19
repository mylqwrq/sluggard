package com.mylq.sluggard.core.cli.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.mylq.sluggard.core.basic.service.TemplateService;
import com.mylq.sluggard.core.basic.vo.TemplateVO;
import com.mylq.sluggard.core.cli.vo.ConfigVO;
import com.mylq.sluggard.core.common.enums.FileTypeEnum;
import com.mylq.sluggard.core.common.util.DateUtil;
import com.mylq.sluggard.core.common.util.ReflectUtil;
import com.mylq.sluggard.core.common.util.StringUtil;
import com.mylq.sluggard.core.db.entity.ColumnEntity;
import com.mylq.sluggard.core.db.entity.TableEntity;

import freemarker.template.TemplateException;

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
     * @param templateNames 使用的模板名称列表
     * @param configs 全局配置列表
     * @param table 表信息
     * @param columns 表的列信息
     * @return 生成代码的根目录
     * @throws IOException IO异常
     * @throws TemplateException Template异常
     * @throws IllegalAccessException IllegalAccess异常
     */
    public String generator(List<String> templateNames, List<ConfigVO> configs, TableEntity table,
            List<ColumnEntity> columns) throws IOException, TemplateException, IllegalAccessException {

        if (CollectionUtils.isEmpty(templateNames)) {
            throw new IllegalArgumentException("Templates must not be null or empty.");
        }
        if (Objects.isNull(table)) {
            throw new NullPointerException("Table info must not be null.");
        } else {
            Field[] fields = ReflectUtil.getFields(TableEntity.class);
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(table);
                if (Objects.isNull(value)) {
                    throw new NullPointerException(field.getName() + " must not be null.");
                }
                if (String.class.equals(value.getClass()) && Strings.isNullOrEmpty(value.toString())) {
                    throw new IllegalArgumentException(field.getName() + " must not be null or empty.");
                }
            }
        }
        if (CollectionUtils.isEmpty(columns)) {
            throw new IllegalArgumentException("Columns info must not be null or empty.");
        }

        // 构建模板中替换的数据模型
        Map<String, Object> dataModel = Maps.newHashMap();
        for (ConfigVO config : configs) {
            dataModel.put(config.getKey(), Strings.isNullOrEmpty(config.getValue()) ? "Sluggard" : config.getValue());
        }

        // 获取全部参数
        Set<String> javaTypes = columns.stream().map(ColumnEntity::getJavaType).collect(Collectors.toSet());
        List<ColumnEntity> primaryList = columns.stream().filter(c -> (c.getColumnKey().contains("P")))
                .collect(Collectors.toList());
        ColumnEntity primary = primaryList.isEmpty() ? columns.get(0) : primaryList.get(0);

        dataModel.put("tableName", table.getTableName());
        dataModel.put("tableComment", table.getTableComment());
        dataModel.put("moduleName", table.getModuleName());
        dataModel.put("lowerModuleName", StringUtil.firstLetterToLowerCase(table.getModuleName()));
        dataModel.put("javaTypes", javaTypes);
        dataModel.put("primary", primary);
        dataModel.put("columns", columns);
        dataModel.put("date", DateUtil.getNowDateStr());
        dataModel.putIfAbsent("author", "Sluggard");

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

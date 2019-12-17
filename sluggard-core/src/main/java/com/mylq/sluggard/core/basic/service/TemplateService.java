package com.mylq.sluggard.core.basic.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.mylq.sluggard.core.basic.factory.TemplatePropertyFactory;
import com.mylq.sluggard.core.basic.util.BasicUtil;
import com.mylq.sluggard.core.basic.vo.TemplateVO;

import freemarker.template.TemplateException;
import lombok.NonNull;

/**
 * Template服务
 *
 * @author WangRunQian
 * @date 2019/12/7
 * @since 1.0.0
 */
@Service
public class TemplateService {

    public String getText(String name) {
        BasicUtil.requireNotNullOrBlank("name", name);
        return TemplatePropertyFactory.getText(name);
    }

    public void setText(String name, String context) {
        BasicUtil.requireNotNullOrBlank("name", name);
        BasicUtil.requireNotNullOrBlank("context", context);
        TemplatePropertyFactory.setText(name, context);
    }

    public String getTextByTemplate(String name, Map<String, Object> dataModel)
            throws IOException, TemplateException {
        return TemplatePropertyFactory.getTextByTemplate(name, dataModel);
    }

    public void saveFileByTemplate(String fileName, String name, Map<String, Object> dataModel)
            throws IOException, TemplateException {
        TemplatePropertyFactory.saveFileByTemplate(fileName, name, dataModel);
    }

    public TemplateVO getByName(@NonNull String name) {
        return TemplatePropertyFactory.get(name);
    }

    public List<TemplateVO> getList(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return TemplatePropertyFactory.getList();
        } else {
            return TemplatePropertyFactory.getList().stream().filter(t -> (t.getName().contains(name)))
                    .collect(Collectors.toList());
        }
    }

    public void save(@NonNull TemplateVO templateVO, boolean isAbsent) {
        BasicUtil.requireNotNullOrBlank("name", templateVO.getName());
        BasicUtil.requireNotNull("fileType", templateVO.getFileType());
        BasicUtil.requireNotNullOrBlank("fileRelativePath", templateVO.getFileRelativePath());

        BasicUtil.checkPropValuePart("fileRelativePath", templateVO.getFileRelativePath());
        BasicUtil.checkPropValuePart("fileNamePrefix", templateVO.getFileNamePrefix());
        BasicUtil.checkPropValuePart("fileNameSuffix", templateVO.getFileNameSuffix());

        TemplatePropertyFactory.set(templateVO.getName(),
                BasicUtil.propValueFormat(templateVO.getFileType().getId().toString(), templateVO.getFileRelativePath(),
                        templateVO.getFileNamePrefix(), templateVO.getFileNameSuffix()),
                isAbsent);
    }

    public void delete(String name) {
        BasicUtil.requireNotNullOrBlank("name", name);
        TemplatePropertyFactory.remove(name);
    }
}

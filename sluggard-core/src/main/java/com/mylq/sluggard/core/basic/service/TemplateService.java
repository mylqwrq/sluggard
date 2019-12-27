package com.mylq.sluggard.core.basic.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mylq.sluggard.core.basic.factory.TemplatePropertyFactory;
import com.mylq.sluggard.core.basic.util.BasicUtil;
import com.mylq.sluggard.core.basic.vo.TemplateVO;
import com.mylq.sluggard.core.common.enums.TemplateTypeEnum;

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

    public List<TemplateVO> getList(@NonNull TemplateTypeEnum templateType) {
        return TemplatePropertyFactory.getList(templateType);
    }

    public TemplateVO get(@NonNull TemplateTypeEnum templateType, String name) {
        return TemplatePropertyFactory.get(templateType, name);
    }

    public void save(@NonNull TemplateVO templateVO, boolean isAbsent) {
        BasicUtil.requireNotNull("templateType", templateVO.getTemplateType());
        BasicUtil.requireNotNullOrBlank("name", templateVO.getName());
        BasicUtil.requireNotNull("fileType", templateVO.getFileType());
        BasicUtil.requireNotNullOrBlank("fileRelativePath", templateVO.getFileRelativePath());
        TemplatePropertyFactory.set(templateVO, isAbsent);
    }

    public void delete(@NonNull TemplateTypeEnum templateType, String name) {
        TemplatePropertyFactory.remove(templateType, name);
    }

    public String getText(@NonNull TemplateTypeEnum templateType, String name) {
        BasicUtil.requireNotNullOrBlank("name", name);
        return TemplatePropertyFactory.getText(templateType, name);
    }

    public void setText(@NonNull TemplateTypeEnum templateType, String name, String context) {
        BasicUtil.requireNotNullOrBlank("name", name);
        BasicUtil.requireNotNullOrBlank("context", context);
        TemplatePropertyFactory.setText(templateType, name, context);
    }

    public String getTextByTemplate(@NonNull TemplateTypeEnum templateType, String name, Map<String, Object> dataModel) throws IOException, TemplateException {
        return TemplatePropertyFactory.getTextByTemplate(templateType, name, dataModel);
    }

    public void saveFileByTemplate(@NonNull TemplateTypeEnum templateType, String fileName, String name, Map<String, Object> dataModel)
            throws IOException, TemplateException {
        TemplatePropertyFactory.saveFileByTemplate(templateType, fileName, name, dataModel);
    }
}

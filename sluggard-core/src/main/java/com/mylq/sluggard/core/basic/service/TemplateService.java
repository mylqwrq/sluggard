package com.mylq.sluggard.core.basic.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

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

    public List<TemplateVO> getList(String name) {
        return TemplatePropertyFactory.getList(name);
    }

    public TemplateVO get(String name) {
        return TemplatePropertyFactory.get(name);
    }

    public void save(@NonNull TemplateVO templateVO, boolean isAbsent) {
        BasicUtil.requireNotNullOrBlank("name", templateVO.getName());
        BasicUtil.requireNotNull("fileType", templateVO.getFileType());
        BasicUtil.requireNotNullOrBlank("fileRelativePath", templateVO.getFileRelativePath());
        TemplatePropertyFactory.set(templateVO, isAbsent);
    }

    public void delete(String name) {
        TemplatePropertyFactory.remove(name);
    }

    public String getText(String name) {
        BasicUtil.requireNotNullOrBlank("name", name);
        return TemplatePropertyFactory.getText(name);
    }

    public void setText(String name, String context) {
        BasicUtil.requireNotNullOrBlank("name", name);
        BasicUtil.requireNotNullOrBlank("context", context);
        TemplatePropertyFactory.setText(name, context);
    }

    public String getTextByTemplate(String name, Map<String, Object> dataModel) throws IOException, TemplateException {
        return TemplatePropertyFactory.getTextByTemplate(name, dataModel);
    }

    public void saveFileByTemplate(String fileName, String name, Map<String, Object> dataModel)
            throws IOException, TemplateException {
        TemplatePropertyFactory.saveFileByTemplate(fileName, name, dataModel);
    }
}

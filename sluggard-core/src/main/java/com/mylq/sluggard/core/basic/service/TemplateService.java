package com.mylq.sluggard.core.basic.service;

import com.google.common.base.Strings;
import com.mylq.sluggard.core.basic.factory.TemplatePropertyFactory;
import com.mylq.sluggard.core.basic.util.BasicUtil;
import com.mylq.sluggard.core.basic.vo.TemplateVO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Template服务
 *
 * @author WangRunQian
 * @date 2019/12/7
 * @since 1.0.0
 */
@Service
public class TemplateService {

    public String getTextByName(String name) {
        BasicUtil.requireNotNullOrBlank("name", name);
        return TemplatePropertyFactory.getText(name);
    }

    public List<TemplateVO> getList(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return TemplatePropertyFactory.getList();
        } else {
            return TemplatePropertyFactory.getList().stream().filter(t -> (t.getName().contains(name))).collect(Collectors.toList());
        }
    }

    public void save(@NonNull TemplateVO templateVO, boolean isAbsent) {
        BasicUtil.requireNotNullOrBlank("name", templateVO.getName());
        BasicUtil.requireNotNull("fileType", templateVO.getFileType());
        BasicUtil.requireNotNullOrBlank("fileRelativePath", templateVO.getFileRelativePath());
        BasicUtil.requireNotNullOrBlank("context", templateVO.getContext());

        BasicUtil.checkPropValuePart("fileRelativePath", templateVO.getFileRelativePath());
        BasicUtil.checkPropValuePart("fileNamePrefix", templateVO.getFileNamePrefix());
        BasicUtil.checkPropValuePart("fileNameSuffix", templateVO.getFileNameSuffix());
        TemplatePropertyFactory.set(templateVO.getName(),
                BasicUtil.propValueFormat(templateVO.getFileType().getId().toString(), templateVO.getFileRelativePath(),
                        templateVO.getFileNamePrefix(), templateVO.getFileNameSuffix()),
                templateVO.getContext(), isAbsent);
    }

    public void delete(String name) {
        BasicUtil.requireNotNullOrBlank("name", name);
        TemplatePropertyFactory.remove(name);
    }
}

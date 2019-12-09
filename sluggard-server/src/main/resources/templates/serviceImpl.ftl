package ${packageUrl}.service.impl;

import com.gw.cloud.common.core.base.service.AbstractBaseService;
import ${packageUrl}.common.entity.${moduleName}Entity;
import ${packageUrl}.common.vo.${moduleName}QueryVO;
import ${packageUrl}.common.vo.${moduleName}UpdateVO;
import ${packageUrl}.mapper.${moduleName}Mapper;
import ${packageUrl}.service.api.${moduleName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${tableComment}Service实现
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@Service
public interface ${moduleName}ServiceImpl extends AbstractBaseService<${primary["columnType"]}, ${moduleName}Entity, ${moduleName}UpdateVO, ${moduleName}QueryVO> implements ${moduleName}Service {

    @Autowired
    private ${moduleName}Mapper ${lowerModuleName}Mapper;

    @Override
    public ${moduleName}Mapper getMapper() {
        return this.${lowerModuleName}Mapper;
    }
}
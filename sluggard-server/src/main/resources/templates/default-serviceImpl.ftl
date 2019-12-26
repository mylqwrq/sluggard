package ${project.basePackage}.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylq.core.base.service.AbstractBaseService;
import com.mylq.core.util.BeanUtil;
import ${project.basePackage}.dao.${table.moduleName}Mapper;
import ${project.basePackage}.dto.${table.moduleName}DTO;
import ${project.basePackage}.entity.${table.moduleName}Entity;
import ${project.basePackage}.service.${table.moduleName}Service;

/**
 * ${table.tableComment}Service实现
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@Service
public class ${table.moduleName}ServiceImpl extends AbstractBaseService<${table.moduleName}DTO, ${table.moduleName}Entity> implements ${table.moduleName}Service {

    @Autowired
    private ${table.moduleName}Mapper ${table.moduleName ? uncap_first}Mapper;

    @Override
    private ${table.moduleName}Mapper getMapper() {
        return this.${table.moduleName ? uncap_first}Mapper;
    }

    @Override
    private ${table.moduleName}Entity convert(${table.moduleName}DTO dto) {
        return BeanUtil.copy(dto, ${table.moduleName}Entity.class);
    }

    @Override
    private Collection<${table.moduleName}Entity> convertBatch(Collection<${table.moduleName}DTO> dtos) {
        return BeanUtil.copyBatch(dtos, ${table.moduleName}Entity.class);
    }

    @Override
    private ${table.moduleName}DTO convert(${table.moduleName}Entity entity) {
        return BeanUtil.copy(entity, ${table.moduleName}DTO.class);
    }

    @Override
    private List<${table.moduleName}DTO> convertBatch(List<${table.moduleName}Entity> entities) {
        return BeanUtil.copyBatch(entities, ${table.moduleName}DTO.class);
    }
}
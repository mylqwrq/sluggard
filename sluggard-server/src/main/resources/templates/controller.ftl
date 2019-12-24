package ${project.basePackage}.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mylq.core.base.controller.BaseController;
import com.mylq.core.util.BeanUtil;
import ${project.basePackage}.dto.${table.moduleName}DTO;
import ${project.basePackage}.service.${table.moduleName}Service;
import ${project.basePackage}.vo.${table.moduleName}VO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ${table.tableComment}控制层接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/${table.moduleName ? uncap_first}")
@Api(value = "/${table.moduleName ? uncap_first}", description = "${table.tableComment}控制层接口")
public class ${table.moduleName}Controller implements BaseController<${table.moduleName}VO, ${table.moduleName}DTO> {

    @Autowired
    private ${table.moduleName}Service ${table.moduleName ? uncap_first}Service;

    @Override
    private ${table.moduleName}DTO convert(${table.moduleName}VO vo) {
        return BeanUtil.copy(vo, ${table.moduleName}DTO.class);
    }

    @Override
    private Collection<${table.moduleName}DTO> convertBatch(Collection<${table.moduleName}VO> vos) {
        return BeanUtil.copyBatch(vo, ${table.moduleName}DTO.class);
    }

    @Override
    private ${table.moduleName}VO convert(${table.moduleName}DTO dto) {
        return BeanUtil.copy(dto, ${table.moduleName}VO.class);
    }

    @Override
    private List<${table.moduleName}VO> convertBatch(List<${table.moduleName}DTO> dtos) {
        return BeanUtil.copyBatch(dtos, ${table.moduleName}VO.class);
    }
}
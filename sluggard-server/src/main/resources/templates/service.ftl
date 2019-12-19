package ${basePackage}.service.api;

import com.gw.cloud.common.core.base.service.BaseService;
import ${basePackage}.common.entity.${moduleName}Entity;
import ${basePackage}.common.vo.${moduleName}QueryVO;
import ${basePackage}.common.vo.${moduleName}UpdateVO;

/**
 * ${tableComment}Service接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
public interface ${moduleName}Service extends BaseService<${primary["columnType"]}, ${moduleName}Entity, ${moduleName}UpdateVO, ${moduleName}QueryVO> {
}
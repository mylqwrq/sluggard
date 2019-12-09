package ${packageUrl}.service.api;

import com.gw.cloud.common.core.base.service.BaseService;
import ${packageUrl}.common.entity.${moduleName}Entity;
import ${packageUrl}.common.vo.${moduleName}QueryVO;
import ${packageUrl}.common.vo.${moduleName}UpdateVO;

/**
 * ${tableComment}Service接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
public interface ${moduleName}Service extends BaseService<${primary["columnType"]}, ${moduleName}Entity, ${moduleName}UpdateVO, ${moduleName}QueryVO> {
}
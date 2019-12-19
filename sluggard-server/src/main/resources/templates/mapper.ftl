package ${basePackage}.mapper;

import com.gw.cloud.common.core.base.dao.BaseMapper;
import ${basePackage}.common.entity.${moduleName}Entity;
import ${basePackage}.common.vo.${moduleName}QueryVO;
import ${basePackage}.common.vo.${moduleName}UpdateVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${tableComment}Mapper接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@Mapper
public interface ${moduleName}Mapper extends BaseMapper<${primary["columnType"]}, ${moduleName}Entity, ${moduleName}UpdateVO, ${moduleName}QueryVO> {
}
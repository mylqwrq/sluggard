package ${packageUrl}.mapper;

import com.gw.cloud.common.core.base.dao.BaseMapper;
import ${packageUrl}.common.entity.${moduleName}Entity;
import ${packageUrl}.common.vo.${moduleName}QueryVO;
import ${packageUrl}.common.vo.${moduleName}UpdateVO;
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
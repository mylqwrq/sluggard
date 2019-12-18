package com.mylq.sluggard.core.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mylq.sluggard.core.basic.factory.MappingPropertyFactory;
import com.mylq.sluggard.core.basic.util.BasicUtil;
import com.mylq.sluggard.core.basic.vo.MappingVO;
import com.mylq.sluggard.core.common.enums.DbTypeEnum;

import lombok.NonNull;

/**
 * Mapping服务
 *
 * @author WangRunQian
 * @date 2019/12/5
 * @since 1.0.0
 */
@Service
public class MappingService {

    public List<MappingVO> getList(@NonNull DbTypeEnum dbType) {
        return MappingPropertyFactory.getList(dbType);
    }

    public MappingVO get(@NonNull DbTypeEnum dbType, String dataType) {
        return MappingPropertyFactory.get(dbType, dataType);
    }

    public void save(@NonNull MappingVO mappingVO) {
        BasicUtil.requireNotNull("dbType", mappingVO.getDbType());
        BasicUtil.requireNotNullOrBlank("dataType", mappingVO.getDataType());
        BasicUtil.requireNotNullOrBlank("javaType", mappingVO.getJavaType());
        BasicUtil.requireNotNullOrBlank("jdbcType", mappingVO.getJdbcType());
        MappingPropertyFactory.set(mappingVO);
    }

    public void delete(@NonNull DbTypeEnum dbType, String dataType) {
        MappingPropertyFactory.remove(dbType, dataType);
    }
}

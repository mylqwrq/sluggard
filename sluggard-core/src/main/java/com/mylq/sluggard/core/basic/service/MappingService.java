package com.mylq.sluggard.core.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mylq.sluggard.core.basic.factory.MappingPropertyFactory;
import com.mylq.sluggard.core.basic.util.BasicUtil;
import com.mylq.sluggard.core.basic.vo.MappingVO;
import com.mylq.sluggard.core.common.base.constant.Constant;
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

    public List<MappingVO> getList(String dbName) {
        DbTypeEnum.check(dbName);
        return MappingPropertyFactory.getList(dbName);
    }

    public String getJavaType(String dbName, String dataType) {
        DbTypeEnum.check(dbName);
        BasicUtil.requireNotNullOrBlank("dataType", dataType);
        return MappingPropertyFactory.get(BasicUtil.propKeyFormat(dbName, dataType))
                .split(Constant.PROP_VALUE_SEPARATOR)[0];
    }

    public String getJdbcType(String dbName, String dataType) {
        DbTypeEnum.check(dbName);
        BasicUtil.requireNotNullOrBlank("dataType", dataType);
        return MappingPropertyFactory.get(BasicUtil.propKeyFormat(dbName, dataType))
                .split(Constant.PROP_VALUE_SEPARATOR)[1];
    }

    public void save(@NonNull MappingVO mappingVO) {
        DbTypeEnum.check(mappingVO.getDbName());
        BasicUtil.requireNotNullOrBlank("dataType", mappingVO.getDataType());
        BasicUtil.requireNotNullOrBlank("javaType", mappingVO.getJavaType());
        BasicUtil.requireNotNullOrBlank("jdbcType", mappingVO.getJdbcType());
        BasicUtil.checkPropKeyPart("dataType", mappingVO.getDataType());
        BasicUtil.checkPropValuePart("javaType", mappingVO.getJavaType());
        BasicUtil.checkPropValuePart("jdbcType", mappingVO.getJdbcType());
        MappingPropertyFactory.set(BasicUtil.propKeyFormat(mappingVO.getDbName(), mappingVO.getDataType()),
                BasicUtil.propValueFormat(mappingVO.getJavaType(), mappingVO.getJdbcType()));
    }

    public void delete(@NonNull String dbName, @NonNull String dataType) {
        DbTypeEnum.check(dbName);
        MappingPropertyFactory.remove(BasicUtil.propKeyFormat(dbName, dataType));
    }
}

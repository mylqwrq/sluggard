package com.mylq.sluggard.core.basic.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mylq.sluggard.core.basic.vo.MappingVO;
import com.mylq.sluggard.core.common.base.constant.Constant;
import com.mylq.sluggard.core.common.enums.DbTypeEnum;
import com.mylq.sluggard.core.common.factory.PropertyFactory;
import com.mylq.sluggard.core.common.util.FileUtil;
import com.mylq.sluggard.core.common.util.JsonUtil;

/**
 * Mapping Properties工厂
 *
 * @author WangRunQian
 * @date 2019/12/4
 * @since 1.0.0
 */
public class MappingPropertyFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(MappingPropertyFactory.class);
    private static final Properties PROP = new Properties();
    private static final String PROP_NAME = "mapping.properties";
    private static final String PROP_PATH = Constant.FILE_ROOT_PATH_PROP + PROP_NAME;
    private static final String PROP_COMMENT = "Sluggard persistence file: mapping";
    private static final String PROP_KEY_SEPARATOR = ".";
    private static final String PROP_VALUE_SEPARATOR = ",";
    private static final String PROP_KEY_PREFIX = "mapping" + PROP_KEY_SEPARATOR;

    static {
        try {
            PROP.load((new FileInputStream(new File(PROP_PATH))));
            LOGGER.info("Load Properties: {}.", PROP_NAME);
        } catch (IOException e) {
            LOGGER.warn("Load Properties Error: {}.", PROP_NAME, e);
            Properties properties = PropertyFactory.getProperties();
            for (Object obj : properties.keySet()) {
                String key = String.valueOf(obj);
                if (key.startsWith(PROP_KEY_PREFIX)) {
                    String[] keys = key.split("\\" + PROP_KEY_SEPARATOR);
                    String[] values = properties.getProperty(key).split(PROP_VALUE_SEPARATOR);
                    MappingVO value = MappingVO.builder().dbType(DbTypeEnum.get(keys[1])).dataType(keys[2])
                            .javaType(values[0]).jdbcType(values[1]).build();
                    PROP.setProperty(key, JsonUtil.toJsonString(value));
                }
            }
            LOGGER.info("Load default mapping.");
            saveProperties();
        }
    }

    public static List<MappingVO> getList(DbTypeEnum dbType) {
        List<MappingVO> list = Lists.newArrayList();
        for (Object obj : PROP.keySet()) {
            String key = String.valueOf(obj);
            if (key.startsWith(PROP_KEY_PREFIX + dbType.getName() + PROP_KEY_SEPARATOR)) {
                list.add(JsonUtil.parseObject(PROP.getProperty(key), MappingVO.class));
            }
        }
        list.sort(Comparator.comparing(MappingVO::getDataType));
        return list;
    }

    public static MappingVO get(DbTypeEnum dbType, String dataType) {
        String key = addKeyPrefix(dbType, dataType);
        String value = PROP.getProperty(key);
        if (Objects.isNull(value)) {
            return MappingVO.builder().dbType(dbType).dataType(dataType).javaType("java.lang.String")
                    .jdbcType("VARCHAR").build();
        } else {
            return JsonUtil.parseObject(value, MappingVO.class);
        }
    }

    public static void set(MappingVO mappingVO) {
        String key = addKeyPrefix(mappingVO.getDbType(), mappingVO.getDataType());
        PROP.setProperty(key, JsonUtil.toJsonString(mappingVO));
        saveProperties();
    }

    public static void remove(DbTypeEnum dbType, String dataType) {
        String key = addKeyPrefix(dbType, dataType);
        PROP.remove(key);
        saveProperties();
    }

    private static String addKeyPrefix(DbTypeEnum dbType, String dataType) {
        return PROP_KEY_PREFIX + Strings.nullToEmpty(dbType.getName()) + PROP_KEY_SEPARATOR
                + Strings.nullToEmpty(dataType);
    }

    private static void saveProperties() {
        try {
            FileUtil.saveProperties(PROP_PATH, PROP_COMMENT, PROP);
        } catch (IOException e) {
            LOGGER.warn("Save Properties Error: {}.", PROP_NAME, e);
        }
    }
}

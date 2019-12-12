package com.mylq.sluggard.core.basic.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mylq.sluggard.core.basic.vo.MappingVO;
import com.mylq.sluggard.core.common.base.constant.Constant;
import com.mylq.sluggard.core.common.factory.PropertyFactory;
import com.mylq.sluggard.core.common.util.FileUtil;

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
    private static final String PROP_KEY_PREFIX = "mapping.";
    private static final String PROP_VALUE_DEFAULT = "java.lang.String,VARCHAR";

    static {
        try {
            PROP.load((new FileInputStream(new File(PROP_PATH))));
            LOGGER.info("Load Properties: {}.", PROP_NAME);
        } catch (IOException e) {
            LOGGER.warn("Load Properties Error: {}.", PROP_NAME, e);
            Properties properties = PropertyFactory.getProperties();
            for (Object key : properties.keySet()) {
                String name = String.valueOf(key);
                if (name.startsWith(PROP_KEY_PREFIX)) {
                    PROP.setProperty(name, properties.getProperty(name));
                }
            }
            LOGGER.info("Load default mapping.");
            saveProperties();
        }
    }

    public static List<MappingVO> getList(String dbName) {
        String keyPrefixAndDbName = PROP_KEY_PREFIX + dbName + Constant.PROP_KEY_SEPARATOR;
        List<MappingVO> list = Lists.newArrayList();
        for (Object key : PROP.keySet()) {
            String name = String.valueOf(key);
            if (name.startsWith(keyPrefixAndDbName)) {
                String[] keys = name.split("\\" + Constant.PROP_KEY_SEPARATOR);
                String[] values = PROP.getProperty(name).split(Constant.PROP_VALUE_SEPARATOR, -1);
                if (keys.length != 3 || values.length != 2) {
                    LOGGER.warn("Illegal mapping data: key={}.", name);
                    continue;
                }
                list.add(MappingVO.builder().dbName(keys[1]).dataType(keys[2]).javaType(values[0]).jdbcType(values[1])
                        .build());
            }
        }
        list.sort(Comparator.comparing(MappingVO::getDbName).thenComparing(MappingVO::getDataType));
        return list;
    }

    public static String get(String key) {
        String value = PROP.getProperty(addKeyPrefix(key));
        if (Strings.isNullOrEmpty(value) || value.split(Constant.PROP_VALUE_SEPARATOR).length < 2) {
            value = PROP_VALUE_DEFAULT;
        }
        return value;
    }

    public static void set(String key, String value) {
        PROP.setProperty(addKeyPrefix(key), value);
        saveProperties();
    }

    public static void remove(String key) {
        PROP.remove(addKeyPrefix(key));
        saveProperties();
    }

    private static String addKeyPrefix(String key) {
        return PROP_KEY_PREFIX + Strings.nullToEmpty(key);
    }

    private static void saveProperties() {
        try {
            FileUtil.saveProperties(PROP_PATH, PROP_COMMENT, PROP);
        } catch (IOException e) {
            LOGGER.warn("Save Properties Error: {}.", PROP_NAME, e);
        }
    }
}

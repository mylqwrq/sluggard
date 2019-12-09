package com.mylq.sluggard.core.common.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Properties工厂
 *
 * @author WangRunQian
 * @date 2019/11/28
 * @since 1.0.0
 */
public class PropertyFactory {

    private PropertyFactory() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFactory.class);
    private static final Properties PROP = new Properties();
    private static final String PROP_NAME = "sluggard.properties";

    static {
        try {
            PROP.load(PropertyFactory.class.getClassLoader().getResourceAsStream(PROP_NAME));
            LOGGER.info("Load Properties: {}.", PROP_NAME);
        } catch (IOException e) {
            LOGGER.warn("Load Properties Error: {}.", PROP_NAME, e);
        }
    }

    public static Properties getProperties() {
        return PROP;
    }
}

package com.mylq.sluggard.core.cli.factory;

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
import com.mylq.sluggard.core.cli.vo.ProjectVO;
import com.mylq.sluggard.core.common.base.constant.Constant;
import com.mylq.sluggard.core.common.base.exception.SluggardBusinessException;
import com.mylq.sluggard.core.common.util.FileUtil;
import com.mylq.sluggard.core.common.util.JsonUtil;

/**
 * Project Properties工厂
 *
 * @author WangRunQian
 * @date 2019/12/12
 * @since 1.0.0
 */
public class ProjectPropertyFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectPropertyFactory.class);
    private static final Properties PROP = new Properties();
    private static final String PROP_NAME = "project.properties";
    private static final String PROP_PATH = Constant.FILE_ROOT_PATH_PROP + PROP_NAME;
    private static final String PROP_COMMENT = "Sluggard persistence file: project";
    private static final String PROP_KEY_PREFIX = "project.";

    static {
        try {
            PROP.load((new FileInputStream(new File(PROP_PATH))));
            LOGGER.info("Load Properties: {}.", PROP_NAME);
        } catch (IOException e) {
            LOGGER.warn("Load Properties Error: {}.", PROP_NAME, e);
        }
    }

    public static List<ProjectVO> getList(String name) {
        List<ProjectVO> list = Lists.newArrayList();
        for (Object obj : PROP.keySet()) {
            String key = String.valueOf(obj);
            if (key.contains(Strings.nullToEmpty(name))) {
                list.add(JsonUtil.parseObject(PROP.getProperty(key), ProjectVO.class));
            }
        }
        list.sort(Comparator.comparing(ProjectVO::getName));
        return list;
    }

    public static ProjectVO get(String name) {
        String key = addKeyPrefix(name);
        String value = PROP.getProperty(key);
        if (Objects.isNull(value)) {
            throw new SluggardBusinessException("Project {0} does not exist.", name);
        }
        return JsonUtil.parseObject(value, ProjectVO.class);
    }

    public static void set(ProjectVO projectVO, boolean isAbsent) {
        String key = addKeyPrefix(projectVO.getName());
        String value = JsonUtil.toJsonString(projectVO);
        if (isAbsent) {
            // 新建操作
            if (Objects.nonNull(PROP.putIfAbsent(key, value))) {
                throw new SluggardBusinessException("Project {0} already exists.", projectVO.getName());
            }
        } else {
            // 编辑操作
            PROP.setProperty(key, value);
        }
        saveProperties();
    }

    public static void remove(String name) {
        PROP.remove(addKeyPrefix(name));
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

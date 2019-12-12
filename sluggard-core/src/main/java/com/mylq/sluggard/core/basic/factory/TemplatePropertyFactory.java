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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mylq.sluggard.core.basic.util.BasicUtil;
import com.mylq.sluggard.core.basic.vo.TemplateVO;
import com.mylq.sluggard.core.common.base.constant.Constant;
import com.mylq.sluggard.core.common.base.exception.SluggardBusinessException;
import com.mylq.sluggard.core.common.base.exception.SluggardCoreException;
import com.mylq.sluggard.core.common.enums.FileTypeEnum;
import com.mylq.sluggard.core.common.factory.PropertyFactory;
import com.mylq.sluggard.core.common.util.FileUtil;
import com.mylq.sluggard.core.common.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Template Properties工厂
 *
 * @author WangRunQian
 * @date 2019/12/7
 * @since 1.0.0
 */
public class TemplatePropertyFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplatePropertyFactory.class);
    private static final Properties PROP = new Properties();
    private static final String PROP_NAME = "template.properties";
    private static final String PROP_PATH = Constant.FILE_ROOT_PATH_PROP + PROP_NAME;
    private static final String PROP_COMMENT = "Sluggard persistence file: template";
    private static final String PROP_KEY_PREFIX = "template.";

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
            LOGGER.info("Load default template.");
            saveProperties();
            createDefaultTemplateFiles();
        }
    }

    /**
     * 创建默认模板文件
     */
    private static void createDefaultTemplateFiles() {
        try {
            // 获取容器资源解析器
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            // 获取所有匹配的资源文件
            Resource[] resources = resolver.getResources("templates/*.ftl");
            // 遍历资源文件获取文件对象
            for (Resource resource : resources) {
                String text = FileUtil.readFile(resource.getInputStream());
                saveTemplateFile(resource.getFilename(), text);
                LOGGER.info("Create template file: {}.", resource.getFilename());
            }
        } catch (IOException e) {
            throw new SluggardCoreException(e);
        }
    }

    public static List<TemplateVO> getList() {
        List<TemplateVO> list = Lists.newArrayList();
        for (Object key : PROP.keySet()) {
            String name = String.valueOf(key);
            String[] keys = name.split("\\" + Constant.PROP_KEY_SEPARATOR);
            String[] values = PROP.getProperty(name).split(Constant.PROP_VALUE_SEPARATOR, -1);
            if (keys.length != 2 || values.length != 4) {
                LOGGER.warn("Illegal mapping data: key={}.", name);
                continue;
            }
            list.add(TemplateVO.builder().name(keys[1]).fileType(FileTypeEnum.get(Integer.parseInt(values[0])))
                    .fileRelativePath(values[1]).fileNamePrefix(values[2]).fileNameSuffix(values[3])
                    .disabled(PropertyFactory.getProperties().containsKey(name)).build());
        }
        list.sort(Comparator.comparing(TemplateVO::getName));
        return list;
    }

    public static void set(String key, String value, boolean isAbsent) {
        if (isAbsent && Objects.nonNull(PROP.putIfAbsent(addKeyPrefix(key), value))) {
            throw new SluggardBusinessException("Template {0} already exists.", key);
        }
        if (PropertyFactory.getProperties().containsKey(addKeyPrefix(key))) {
            throw new SluggardBusinessException("Default template is not allowed to operate: {0}.", key);
        }
        PROP.setProperty(addKeyPrefix(key), value);
        saveProperties();
    }

    public static void remove(String key) {
        if (PropertyFactory.getProperties().containsKey(addKeyPrefix(key))) {
            throw new SluggardBusinessException("Default template is not allowed to operate: {0}.", key);
        }
        PROP.remove(addKeyPrefix(key));
        saveProperties();
        deleteTemplateFile(key);
    }

    public static String getText(String key) {
        try {
            return readTemplateFile(key);
        } catch (IOException e) {
            LOGGER.warn("Failed to read context from template: {}.", key, e);
            return "";
        }
    }

    public static void setText(String key, String context) {
        if (PropertyFactory.getProperties().containsKey(addKeyPrefix(key))) {
            throw new SluggardBusinessException("Default template is not allowed to operate: {0}.", key);
        }
        try {
            saveTemplateFile(key, context);
        } catch (IOException e) {
            LOGGER.error("Failed to save template file: {}.", key, e);
            throw new SluggardBusinessException("Failed to save template file {0}.", key);
        }
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

    /**
     * 获取模板文件名称
     *
     * @param name 模板名称
     * @return 模板文件名称
     */
    private static String getTemplateFileName(String name) {
        BasicUtil.requireNotNullOrBlank("template name", name);
        return name.endsWith(FileTypeEnum.FTL.getName()) ? name : name + FileTypeEnum.FTL.getName();
    }

    /**
     * 读取模板文件
     *
     * @param name 模板名称
     * @return 模板内容
     * @throws IOException IO异常
     */
    private static String readTemplateFile(String name) throws IOException {
        return FileUtil
                .readFile(Constant.FILE_ROOT_PATH_TEMPLATE + StringUtil.STR_FILE_SEPARATOR + getTemplateFileName(name));
    }

    /**
     * 保存模板文件
     *
     * @param name 模板名称
     * @param text 模板文件内容
     * @throws IOException IO异常
     */
    private static void saveTemplateFile(String name, String text) throws IOException {
        FileUtil.saveFile(Constant.FILE_ROOT_PATH_TEMPLATE + StringUtil.STR_FILE_SEPARATOR + getTemplateFileName(name),
                text);
    }

    /**
     * 删除模板文件
     *
     * @param name 模板名称
     */
    private static void deleteTemplateFile(String name) {
        FileUtil.deleteFile(
                Constant.FILE_ROOT_PATH_TEMPLATE + StringUtil.STR_FILE_SEPARATOR + getTemplateFileName(name));
    }

    /**
     * Template配置类
     */
    private static class TemplateConfiguration {

        private TemplateConfiguration() {
        }

        private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_29);

        static {
            try {
                CONFIGURATION
                        .setDirectoryForTemplateLoading(FileUtil.getFileAndCreate(Constant.FILE_ROOT_PATH_TEMPLATE));
                CONFIGURATION.setDefaultEncoding("utf-8");
            } catch (IOException e) {
                throw new SluggardCoreException(e);
            }
        }

        /**
         * 根据模板名称获取模板对象
         *
         * @param name 模板名称
         * @return 模板对象
         */
        private static Template getTemplate(String name) {
            try {
                return CONFIGURATION.getTemplate(getTemplateFileName(name));
            } catch (IOException e) {
                LOGGER.error("Failed to get template: {}.", name, e);
                throw new SluggardBusinessException("Template {0} does not exist.", name);
            }
        }
    }
}

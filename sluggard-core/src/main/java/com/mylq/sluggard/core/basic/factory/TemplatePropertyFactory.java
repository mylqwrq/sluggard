package com.mylq.sluggard.core.basic.factory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
import com.mylq.sluggard.core.common.util.JsonUtil;
import com.mylq.sluggard.core.common.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
    private static final String PROP_KEY_SEPARATOR = ".";
    private static final String PROP_VALUE_SEPARATOR = ",";
    private static final String PROP_KEY_PREFIX = "template" + PROP_KEY_SEPARATOR;

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
                    TemplateVO value = TemplateVO.builder().name(keys[1]).fileType(FileTypeEnum.get(values[0]))
                            .fileRelativePath(values[1]).fileNamePrefix(values[2]).fileNameSuffix(values[3])
                            .disabled(true).build();
                    PROP.setProperty(key, JsonUtil.toJsonString(value));
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

    public static List<TemplateVO> getList(String name) {
        List<TemplateVO> list = Lists.newArrayList();
        for (Object obj : PROP.keySet()) {
            String key = String.valueOf(obj);
            if (key.contains(Strings.nullToEmpty(name))) {
                list.add(JsonUtil.parseObject(PROP.getProperty(key), TemplateVO.class));
            }
        }
        list.sort(Comparator.comparing(TemplateVO::getName));
        return list;
    }

    public static TemplateVO get(String name) {
        String key = addKeyPrefix(name);
        String value = PROP.getProperty(key);
        if (Objects.isNull(value)) {
            throw new SluggardBusinessException("Template {0} does not exist.", name);
        }
        return JsonUtil.parseObject(value, TemplateVO.class);
    }

    public static void set(TemplateVO templateVO, boolean isAbsent) {
        String key = addKeyPrefix(templateVO.getName());
        String value = JsonUtil.toJsonString(templateVO);
        if (isAbsent) {
            // 新建操作
            if (Objects.nonNull(PROP.putIfAbsent(key, value))) {
                throw new SluggardBusinessException("Template {0} already exists.", templateVO.getName());
            }
        } else {
            // 编辑操作
            if (PropertyFactory.getProperties().containsKey(key)) {
                throw new SluggardBusinessException("Default template is not allowed to operate: {0}.", templateVO.getName());
            }
            PROP.setProperty(key, value);
        }
        saveProperties();
    }

    public static void remove(String name) {
        String key = addKeyPrefix(name);
        if (PropertyFactory.getProperties().containsKey(key)) {
            throw new SluggardBusinessException("Default template is not allowed to operate: {0}.", name);
        }
        PROP.remove(key);
        saveProperties();
        deleteTemplateFile(name);
    }

    public static String getText(String name) {
        try {
            return readTemplateFile(name);
        } catch (IOException e) {
            LOGGER.warn("Failed to read context from template: {}.", name, e);
            return "";
        }
    }

    public static void setText(String name, String context) {
        String key = addKeyPrefix(name);
        if (PropertyFactory.getProperties().containsKey(key)) {
            throw new SluggardBusinessException("Default template is not allowed to operate: {0}.", name);
        }
        try {
            saveTemplateFile(name, context);
        } catch (IOException e) {
            LOGGER.error("Failed to save template file: {}.", name, e);
            throw new SluggardBusinessException("Failed to save template file {0}.", name);
        }
    }

    /**
     * 将指定内容替换进模板文件
     *
     * @param name 模板名称
     * @param dataModel 模板中替换的数据模型
     * @return 模板文件内容
     * @throws IOException IO异常
     * @throws TemplateException Template异常
     */
    public static String getTextByTemplate(String name, Map<String, Object> dataModel)
            throws IOException, TemplateException {
        Template template = TemplateConfiguration.getTemplate(name);
        StringWriter out = new StringWriter();
        template.process(dataModel, out);
        return out.toString();
    }

    /**
     * 将指定内容写入模板文件并保存
     *
     * @param fileName 文件路径
     * @param name 模板名称
     * @param dataModel 模板中替换的数据模型
     * @throws IOException IO异常
     * @throws TemplateException Template异常
     */
    public static void saveFileByTemplate(String fileName, String name, Map<String, Object> dataModel)
            throws IOException, TemplateException {
        Template template = TemplateConfiguration.getTemplate(name);
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FileUtil.getFileAndCreateParent(Constant.FILE_ROOT_PATH_TEMP + fileName)),
                StandardCharsets.UTF_8));
        template.process(dataModel, out);
        out.close();
    }

    private static String addKeyPrefix(String name) {
        return PROP_KEY_PREFIX + Strings.nullToEmpty(name);
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

package com.mylq.sluggard.core.common.base.constant;

/**
 * 全局常量类
 *
 * @author WangRunQian
 * @date 2019/12/4
 * @since 1.0.0
 */
public class Constant {
    /**
     * 临时文件根目录
     */
    public static final String FILE_ROOT_PATH_TEMP = System.getProperty("java.io.tmpdir") + "/sluggard/";
    /**
     * Properties文件根目录
     */
    public static final String FILE_ROOT_PATH_PROP = System.getProperty("user.dir") + "/files/properties/";
    /**
     * 模板文件根目录
     */
    public static final String FILE_ROOT_PATH_TEMPLATE = System.getProperty("user.dir") + "/files/templates/";
}

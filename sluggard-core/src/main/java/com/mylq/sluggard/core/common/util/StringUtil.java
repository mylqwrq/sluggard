package com.mylq.sluggard.core.common.util;

/**
 * 字符串工具类
 *
 * @author WangRunQian
 * @date 2019/12/4
 * @since 1.0.0
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * 文件分隔符
     */
    public static final String STR_FILE_SEPARATOR = System.getProperty("file.separator", "\\");

    /**
     * 根据包路径获取文件目录
     *
     * @param packagePath 包路径
     * @return 文件目录
     */
    public static String getFilePathByPackage(String packagePath) {
        String[] dirs = packagePath.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String dir : dirs) {
            sb.append(dir).append(STR_FILE_SEPARATOR);
        }
        return sb.toString();
    }

    /**
     * 下划线命名转换为驼峰命名
     *
     * @param param 输入的下划线命名字符串
     * @param isFirstUpperCase 首字母是否大写
     * @param ignorePrefix 忽略前缀
     * @param ignoreSuffix 忽略后缀
     * @return 驼峰命名字符串
     */
    public static String underlineToHump(String param, boolean isFirstUpperCase, String ignorePrefix,
            String ignoreSuffix) {
        //
        String ignoredParam = param;
        if (ignorePrefix != null && !ignorePrefix.isEmpty() && ignoredParam.startsWith(ignorePrefix)) {
            ignoredParam = ignoredParam.substring(ignorePrefix.length());
        }
        if (ignoreSuffix != null && !ignoreSuffix.isEmpty() && ignoredParam.endsWith(ignoreSuffix)) {
            ignoredParam = ignoredParam.substring(0, ignoredParam.length() - ignoreSuffix.length());
        }
        // 以下划线分隔字符串
        String[] params = ignoredParam.split("_");
        // 初始化返回对象
        StringBuilder result = new StringBuilder();
        // 遍历
        for (String str : params) {
            if (isAllLetters(str)) {
                if (isFirstUpperCase) {
                    result.append(str.substring(0, 1).toUpperCase());
                    result.append(str.substring(1).toLowerCase());
                } else {
                    if (result.length() == 0) {
                        result.append(str.toLowerCase());
                    } else {
                        result.append(str.substring(0, 1).toUpperCase());
                        result.append(str.substring(1).toLowerCase());
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * 判断字符串是否全部由数字和字母组成
     *
     * @param str 输入的字符串
     * @return 判断结果
     */
    private static boolean isAllLetters(String str) {
        return str.matches("[0-9a-zA-Z]+");
    }
}
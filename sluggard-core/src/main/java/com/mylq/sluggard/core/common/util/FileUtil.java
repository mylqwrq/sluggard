package com.mylq.sluggard.core.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * 文件工具类
 *
 * @author WangRunQian
 * @date 2019/12/4
 * @since 1.0.0
 */
public class FileUtil {

    private FileUtil() {
    }

    /**
     * 通用换行符
     */
    private static final String STR_LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    /**
     * 读取文件内容
     *
     * @param filePath 文件路径
     * @return 文件内容
     * @throws IOException IO异常
     */
    public static String readFile(String filePath) throws IOException {
        return readFile(new File(filePath));
    }

    /**
     * 读取文件内容
     *
     * @param file 文件对象
     * @return 文件内容
     * @throws IOException IO异常
     */
    public static String readFile(File file) throws IOException {
        return readFile(new FileInputStream(file));
    }

    /**
     * 读取文件内容
     *
     * @param in 输入流对象
     * @return 文件内容
     * @throws IOException IO异常
     */
    public static String readFile(InputStream in) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String s;
            while ((s = br.readLine()) != null) {
                if (result.length() != 0) {
                    result.append(STR_LINE_SEPARATOR);
                }
                result.append(s);
            }
        }
        return result.toString();
    }

    /**
     * 获得文件对象，若文件不存在则创建文件
     *
     * @param filePath 文件路径
     * @return File对象
     */
    public static File getFileAndCreate(String filePath) {
        // 得到文件对象
        File file = new File(filePath);
        // 判断文件所在的目录是否存在，若不存在则创建
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获得文件对象，若文件父目录不存在则创建父目录
     *
     * @param filePath 文件路径
     * @return File对象
     */
    public static File getFileAndCreateParent(String filePath) {
        // 得到文件对象
        File file = new File(filePath);
        // 判断文件所在的目录是否存在，若不存在则创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    /**
     * 根据文件路径设置Properties对象
     *
     * @param filePath   文件路径
     * @param comment    文件注释
     * @param properties Properties对象
     * @throws IOException IO异常
     */
    public static void saveProperties(String filePath, String comment, Properties properties) throws IOException {
        try (FileWriter writer = new FileWriter(getFileAndCreateParent(filePath))) {
            properties.store(writer, comment);
        }
    }

    /**
     * 将指定内容写入文件并保存
     *
     * @param filePath 文件路径
     * @param text 文件内容
     * @throws IOException IO异常
     */
    public static void saveFile(String filePath, String text) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(getFileAndCreateParent(filePath)), StandardCharsets.UTF_8));) {
            bufferedWriter.write(text);
        }
    }

    /**
     * 递归删除文件
     *
     * @param filePath 文件路径
     */
    public static void deleteFile(String filePath) {
        if (Objects.nonNull(filePath)) {
            File file = new File(filePath);
            deleteFile(file);
        }
    }

    /**
     * 递归删除文件
     *
     * @param file 文件对象
     */
    public static void deleteFile(File file) {
        // 若文件不存在直接返回
        if (!file.exists()) {
            return;
        }
        // 若文件是文件类型或者不存在子文件直接删除
        if (file.isFile() || file.list() == null) {
            file.delete();
        } else {
            // 递归删除子文件
            File[] listFiles = file.listFiles();
            if (Objects.nonNull(listFiles)) {
                for (File childFile : listFiles) {
                    deleteFile(childFile);
                }
            }
            file.delete();
        }
    }
}

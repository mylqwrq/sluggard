package com.mylq.sluggard.core.common.util;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

/**
 * Zip文件工具类
 *
 * @author WangRunQian
 * @date 2019/12/21
 * @since 1.0.0
 */
public class ZipUtil {

    private ZipUtil() {
    }

    /**
     * 压缩文件夹到指定输出流中，可以是本地文件输出流，也可以是web响应下载流
     *
     * @param outputStream 压缩后文件的输出流
     * @param srcDir 源文件夹
     * @throws IOException IO异常
     */
    public static void compressToZip(OutputStream outputStream, String srcDir) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                ArchiveOutputStream out = new ZipArchiveOutputStream(bufferedOutputStream);) {
            Path start = Paths.get(srcDir);
            Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    ArchiveEntry entry = new ZipArchiveEntry(dir.toFile(), start.relativize(dir).toString());
                    out.putArchiveEntry(entry);
                    out.closeArchiveEntry();
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try (InputStream input = new FileInputStream(file.toFile())) {
                        ArchiveEntry entry = new ZipArchiveEntry(file.toFile(), start.relativize(file).toString());
                        out.putArchiveEntry(entry);
                        IOUtils.copy(input, out);
                        out.closeArchiveEntry();
                    }
                    return super.visitFile(file, attrs);
                }
            });
        }
    }
}

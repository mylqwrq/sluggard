package ${project.basePackage}.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.Lists;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import ${project.basePackage}.core.util.excel.BaseEasyExcelReadListener;

public class EasyExcelUtil {

    private EasyExcelUtil() {
    }

    public static <T> List<T> read(InputStream in, Class<T> clazz) {
        BaseEasyExcelReadListener readListener = new BaseEasyExcelReadListener();
        EasyExcel.read(in, clazz, readListener).sheet().doRead();
        return BeanUtil.copyBatch(readListener.getDataList(), clazz);
    }

    public static <T> List<T> readSheet(InputStream in, Class<T> clazz, int sheetNo) {
        BaseEasyExcelReadListener readListener = new BaseEasyExcelReadListener();
        ReadSheet readSheet = EasyExcel.readSheet(sheetNo).build();
        ExcelReader excelReader = EasyExcel.read(in, clazz, readListener).build();
        excelReader.read(readSheet);
        excelReader.finish();
        return BeanUtil.copyBatch(readListener.getDataList(), clazz);
    }

    public static <T> void write(HttpServletRequest request, HttpServletResponse response, String fileName,
                                 Class<T> clazz, List<T> data) throws IOException {
        write(request, response, fileName, clazz, data, 0);
    }

    public static <T> void write(HttpServletRequest request, HttpServletResponse response, String fileName,
                                 Class<T> clazz, List<T> data, int sheetNo) throws IOException {
        try (ServletOutputStream out = response.getOutputStream()) {
            setResponse(request, response, fileName);
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, "Sheet" + sheetNo).head(clazz).build();
            ExcelWriter excelWriter = EasyExcel.write(out).build();
            excelWriter.write(Optional.ofNullable(data).orElse(Lists.newArrayList()), writeSheet);
            excelWriter.finish();
        }
    }

    public static void fill(HttpServletRequest request, HttpServletResponse response, String fileName,
                            String templateFileName, Object data) throws IOException {
        fill(request, response, fileName, templateFileName, data, false, false);
    }

    public static void fill(HttpServletRequest request, HttpServletResponse response, String fileName,
                            String templateFileName, Object data, boolean isHorizontal, boolean forceNewRow) throws IOException {
        if (Objects.isNull(data)) {
            throw new NullPointerException("Fill data cannot be null.");
        }
        try (ServletOutputStream out = response.getOutputStream()) {
            setResponse(request, response, fileName);
            FillConfig fillConfig = FillConfig.builder()
                    .direction(isHorizontal ? WriteDirectionEnum.HORIZONTAL : WriteDirectionEnum.VERTICAL)
                    .forceNewRow(forceNewRow).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            ExcelWriter excelWriter = EasyExcel.write(out).withTemplate(templateFileName).build();
            excelWriter.fill(data, fillConfig, writeSheet);
            excelWriter.finish();
        }
    }

    private static String encode(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        String agent = request.getHeader("USER-AGENT");
        boolean isMsie = !Objects.isNull(agent) && (agent.contains("MSIE") || agent.contains("like Gecko"));
        if (isMsie) {
            fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
        } else {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
        }
        return fileName;
    }

    private static void setResponse(HttpServletRequest request, HttpServletResponse response, String fileName)
            throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + encode(request, fileName) + ".xlsx");
    }

    private static void setResponse(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + ".xlsx");
    }
}

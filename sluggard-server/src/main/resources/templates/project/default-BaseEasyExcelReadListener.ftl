package ${project.basePackage}.core.util.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;

public class BaseEasyExcelReadListener extends AnalysisEventListener<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseEasyExcelReadListener.class);

    private List<Object> headList = new ArrayList<>();
    private List<Object> dataList = new ArrayList<>();

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            LOGGER.error("读取文件异常。第{}行，第{}列解析失败，跳过解析下一行。", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), exception);
        } else {
            LOGGER.error("读取文件异常。解析失败，跳过解析下一行。", exception);
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        headList.add(headMap);
    }

    @Override
    public void invoke(Object t, AnalysisContext analysisContext) {
        dataList.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public List<Object> getDataList() {
        return dataList;
    }
}

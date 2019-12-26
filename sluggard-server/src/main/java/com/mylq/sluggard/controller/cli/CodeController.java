package com.mylq.sluggard.controller.cli;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mylq.sluggard.core.cli.service.CodeService;
import com.mylq.sluggard.core.cli.vo.CodeGeneratorVO;
import com.mylq.sluggard.core.cli.vo.ProjectBasicVO;
import com.mylq.sluggard.core.cli.vo.TemplateConfigVO;
import com.mylq.sluggard.core.common.base.constant.Constant;
import com.mylq.sluggard.core.common.base.exception.SluggardBusinessException;
import com.mylq.sluggard.core.common.util.FileUtil;
import com.mylq.sluggard.core.common.util.JsonUtil;
import com.mylq.sluggard.core.common.util.ZipUtil;
import com.mylq.sluggard.core.db.entity.ColumnEntity;
import com.mylq.sluggard.core.db.entity.TableEntity;
import com.mylq.sluggard.core.db.vo.DbVO;

/**
 * Code控制层接口
 *
 * @author WangRunQian
 * @date 2019/12/17
 * @since 1.0.0
 */
@RestController
@RequestMapping("/cli/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @PostMapping("/generator")
    public void generator(HttpServletRequest request, HttpServletResponse response, CodeGeneratorVO vo) {
        try {
            List<String> templateNames = JsonUtil.parseArray(vo.getTemplateNames(), String.class);
            List<TemplateConfigVO> templateConfigs = JsonUtil.parseArray(vo.getTemplateConfigs(), TemplateConfigVO.class);
            ProjectBasicVO project = JsonUtil.parseObject(vo.getProject(), ProjectBasicVO.class);
            DbVO dataSource = JsonUtil.parseObject(vo.getDataSource(), DbVO.class);
            TableEntity table = JsonUtil.parseObject(vo.getTable(), TableEntity.class);
            List<ColumnEntity> columns = JsonUtil.parseArray(vo.getColumns(), ColumnEntity.class);

            String folderDir = UUID.randomUUID().toString().replaceAll("-", "");
            codeService.generator(folderDir, templateNames, templateConfigs, project, dataSource, table, columns);
            String folderPath = Constant.FILE_ROOT_PATH_TEMP + folderDir;
            // 压缩文件
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename = " + table.getTableName() + ".zip");
            ZipUtil.compressToZip(response.getOutputStream(), folderPath);
            // 删除文件
            FileUtil.deleteFile(folderPath);
        } catch (Exception e) {
            throw new SluggardBusinessException(e);
        }
    }
}

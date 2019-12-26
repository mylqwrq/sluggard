package com.mylq.sluggard.controller.cli;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mylq.sluggard.core.cli.service.ProjectService;
import com.mylq.sluggard.core.cli.vo.ProjectBasicVO;
import com.mylq.sluggard.core.cli.vo.ProjectGeneratorVO;
import com.mylq.sluggard.core.cli.vo.ProjectVO;
import com.mylq.sluggard.core.cli.vo.TemplateConfigVO;
import com.mylq.sluggard.core.common.base.constant.Constant;
import com.mylq.sluggard.core.common.base.exception.SluggardBusinessException;
import com.mylq.sluggard.core.common.base.result.JsonResult;
import com.mylq.sluggard.core.common.util.FileUtil;
import com.mylq.sluggard.core.common.util.JsonUtil;
import com.mylq.sluggard.core.common.util.ZipUtil;
import com.mylq.sluggard.core.db.entity.TableEntity;
import com.mylq.sluggard.core.db.vo.DbVO;

/**
 * Project控制层接口
 *
 * @author WangRunQian
 * @date 2019/12/12
 * @since 1.0.0
 */
@RestController
@RequestMapping("/cli/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/getList")
    public JsonResult<List> getList(@RequestParam String name) {
        JsonResult<List> jsonResult;
        try {
            List<ProjectVO> result = projectService.getList(name);
            jsonResult = JsonResult.success(result);
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @GetMapping("/getByName")
    public JsonResult<ProjectVO> getByName(@RequestParam String name) {
        JsonResult<ProjectVO> jsonResult;
        try {
            ProjectVO result = projectService.get(name);
            jsonResult = JsonResult.success(result);
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @PostMapping("/save")
    public JsonResult<Void> save(@RequestBody ProjectVO projectVO) {
        JsonResult<Void> jsonResult;
        try {
            projectService.save(projectVO, true);
            jsonResult = JsonResult.success();
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @PostMapping("/edit")
    public JsonResult<Void> edit(@RequestBody ProjectVO projectVO) {
        JsonResult<Void> jsonResult;
        try {
            projectService.save(projectVO, false);
            jsonResult = JsonResult.success();
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @GetMapping("/del")
    public JsonResult<Void> del(@RequestParam String name) {
        JsonResult<Void> jsonResult;
        try {
            projectService.delete(name);
            jsonResult = JsonResult.success();
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @PostMapping("/generator")
    public void generator(HttpServletRequest request, HttpServletResponse response, ProjectGeneratorVO vo) {
        try {
            List<String> templateNames = JsonUtil.parseArray(vo.getTemplateNames(), String.class);
            List<TemplateConfigVO> templateConfigs = JsonUtil.parseArray(vo.getTemplateConfigs(), TemplateConfigVO.class);
            ProjectBasicVO project = JsonUtil.parseObject(vo.getProject(), ProjectBasicVO.class);
            DbVO dataSource = JsonUtil.parseObject(vo.getDataSource(), DbVO.class);
            List<TableEntity> tables = JsonUtil.parseArray(vo.getTables(), TableEntity.class);

            String folderDir = projectService.generator(templateNames, templateConfigs, project, dataSource, tables);
            String folderPath = Constant.FILE_ROOT_PATH_TEMP + folderDir;
            // 压缩文件
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename = " + project.getName() + ".zip");
            ZipUtil.compressToZip(response.getOutputStream(), folderPath);
            // 删除文件
            FileUtil.deleteFile(folderPath);
        } catch (Exception e) {
            throw new SluggardBusinessException(e);
        }
    }
}

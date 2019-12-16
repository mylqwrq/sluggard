package com.mylq.sluggard.controller.cli;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mylq.sluggard.core.cli.service.ProjectService;
import com.mylq.sluggard.core.cli.vo.ProjectVO;
import com.mylq.sluggard.core.common.base.result.JsonResult;

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

    @GetMapping(value = "/getList")
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

    @GetMapping(value = "/getByName")
    public JsonResult<ProjectVO> getByName(@RequestParam String name) {
        JsonResult<ProjectVO> jsonResult;
        try {
            ProjectVO result = projectService.getByName(name);
            jsonResult = JsonResult.success(result);
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @PostMapping(value = "/save")
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

    @PostMapping(value = "/edit")
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

    @GetMapping(value = "/del")
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
}

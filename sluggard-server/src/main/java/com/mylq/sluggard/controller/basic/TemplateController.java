package com.mylq.sluggard.controller.basic;

import com.mylq.sluggard.core.basic.service.TemplateService;
import com.mylq.sluggard.core.basic.vo.TemplateVO;
import com.mylq.sluggard.core.common.base.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Template控制层接口
 *
 * @author WangRunQian
 * @date 2019/12/7
 * @since 1.0.0
 */
@RestController
@RequestMapping("/basic/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping(value = "/getText")
    public JsonResult<String> getText(@RequestParam String name) {
        JsonResult<String> jsonResult;
        try {
            String result = templateService.getTextByName(name);
            jsonResult = JsonResult.success(result);
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @GetMapping(value = "/getList")
    public JsonResult<List> getList(@RequestParam String name) {
        JsonResult<List> jsonResult;
        try {
            List<TemplateVO> result = templateService.getList(name);
            jsonResult = JsonResult.success(result);
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @PostMapping(value = "/save")
    public JsonResult<Void> save(@RequestBody TemplateVO templateVO) {
        JsonResult<Void> jsonResult;
        try {
            templateService.save(templateVO, true);
            jsonResult = JsonResult.success();
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @PostMapping(value = "/edit")
    public JsonResult<Void> edit(@RequestBody TemplateVO templateVO) {
        JsonResult<Void> jsonResult;
        try {
            templateService.save(templateVO, false);
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
            templateService.delete(name);
            jsonResult = JsonResult.success();
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }
}

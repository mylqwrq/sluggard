package com.mylq.sluggard.controller.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mylq.sluggard.core.basic.service.MappingService;
import com.mylq.sluggard.core.basic.vo.MappingVO;
import com.mylq.sluggard.core.common.base.result.JsonResult;
import com.mylq.sluggard.core.common.enums.DbTypeEnum;

/**
 * Mapping控制层接口
 *
 * @author WangRunQian
 * @date 2019/12/5
 * @since 1.0.0
 */
@RestController
@RequestMapping("/basic/mapping")
public class MappingController {

    @Autowired
    private MappingService mappingService;

    @GetMapping("/getList")
    public JsonResult<List> getList(@RequestParam Integer dbTypeId) {
        JsonResult<List> jsonResult;
        try {
            List<MappingVO> result = mappingService.getList(DbTypeEnum.get(dbTypeId));
            jsonResult = JsonResult.success(result);
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @PostMapping("/save")
    public JsonResult<Void> save(@RequestBody MappingVO mappingVO) {
        JsonResult<Void> jsonResult;
        try {
            mappingService.save(mappingVO, true);
            jsonResult = JsonResult.success();
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @PostMapping("/edit")
    public JsonResult<Void> edit(@RequestBody MappingVO mappingVO) {
        JsonResult<Void> jsonResult;
        try {
            mappingService.save(mappingVO, false);
            jsonResult = JsonResult.success();
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @GetMapping("/del")
    public JsonResult<Void> del(@RequestParam Integer dbTypeId, @RequestParam String dataType) {
        JsonResult<Void> jsonResult;
        try {
            mappingService.delete(DbTypeEnum.get(dbTypeId), dataType);
            jsonResult = JsonResult.success();
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }
}

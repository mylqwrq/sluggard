package com.mylq.sluggard.controller.server;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mylq.sluggard.core.common.base.enums.BaseEnum;
import com.mylq.sluggard.core.common.base.result.JsonResult;
import com.mylq.sluggard.core.common.enums.DbTypeEnum;
import com.mylq.sluggard.core.common.enums.FileTypeEnum;

/**
 * 枚举控制层接口
 *
 * @author WangRunQian
 * @date 2019/12/6
 * @since 1.0.0
 */
@RestController
@RequestMapping("/server/enums")
public class EnumsController {

    @GetMapping("/getDbTypes")
    public JsonResult<List> getDbTypes() {
        JsonResult<List> jsonResult;
        try {
            List<BaseEnum> result = Arrays.asList(DbTypeEnum.values());
            jsonResult = JsonResult.success(result);
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @GetMapping("/getFileTypes")
    public JsonResult<List> getFileTypes() {
        JsonResult<List> jsonResult;
        try {
            List<BaseEnum> result = Arrays.asList(FileTypeEnum.values());
            jsonResult = JsonResult.success(result);
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }
}

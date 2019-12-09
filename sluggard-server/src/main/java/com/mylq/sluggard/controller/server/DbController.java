package com.mylq.sluggard.controller.server;

import com.mylq.sluggard.core.common.base.result.JsonResult;
import com.mylq.sluggard.core.db.entity.ColumnEntity;
import com.mylq.sluggard.core.db.vo.DbVO;
import com.mylq.sluggard.core.db.entity.TableEntity;
import com.mylq.sluggard.core.db.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DB控制层接口
 *
 * @author WangRunQian
 * @date 2019/12/5
 * @since 1.0.0
 */
@RestController
@RequestMapping("/server/db")
public class DbController {

    @Autowired
    private DbService dbService;

    @PostMapping(value = "/getTables")
    public JsonResult<List> getTables(@RequestBody DbVO dbVO, @RequestParam String tableName) {
        JsonResult<List> jsonResult;
        try {
            List<TableEntity> result = dbService.getTablesList(dbVO, tableName);
            jsonResult = JsonResult.success(result);
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }

    @PostMapping(value = "/getColumns")
    public JsonResult<List> getColumns(@RequestBody DbVO dbVO, @RequestParam String tableName) {
        JsonResult<List> jsonResult;
        try {
            List<ColumnEntity> result = dbService.getColumnsList(dbVO, tableName);
            jsonResult = JsonResult.success(result);
        } catch (Exception e) {
            jsonResult = JsonResult.error(e);
        }
        return jsonResult;
    }
}

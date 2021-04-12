package ${project.basePackage}.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;
import ${project.basePackage}.common.entity.${table.moduleName}Entity;
import ${project.basePackage}.common.query.${table.moduleName}Query;
import ${project.basePackage}.core.base.result.JsonResult;
import ${project.basePackage}.service.api.${table.moduleName}Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ${table.tableComment}控制层接口
 *
 * @author ${author}
 * @version 1.0.0
 * @since ${date}
 */
@Api(tags = "${table.tableComment}控制层接口")
@RequestMapping("/${table.moduleName ? uncap_first}")
@RestController
public class ${table.moduleName}Controller {

    @Autowired
    private ${table.moduleName}Service ${table.moduleName ? uncap_first}Service;

    @ApiOperation(value = "分页查询", notes = "分页精准查询")
    @PostMapping("/searchPage")
    public JsonResult<List<${table.moduleName}Entity>> searchPage(@RequestBody ${table.moduleName}Query query) {
        return JsonResult.transmit(${table.moduleName ? uncap_first}Service.queryPage(query, query.getPageNum(), query.getPageSize()));
    }

    @ApiOperation(value = "列表查询", notes = "列表精准查询")
    @PostMapping("/searchList")
    public JsonResult<List<${table.moduleName}Entity>> searchList(@RequestBody ${table.moduleName}Query query) {
        return JsonResult.success(${table.moduleName ? uncap_first}Service.queryList(query));
    }

    @ApiOperation(value = "单行查询", notes = "根据ID单行查询数据")
    @GetMapping("/searchById")
    public JsonResult<${table.moduleName}Entity> searchById(@RequestParam Long id) {
        return JsonResult.success(${table.moduleName ? uncap_first}Service.queryById(id));
    }

    @ApiOperation(value = "批量查询", notes = "根据ID串批量查询数据")
    @GetMapping("/searchByIds")
    public JsonResult<List<${table.moduleName}Entity>> searchByIds(@RequestParam String ids) {
        return JsonResult.success(${table.moduleName ? uncap_first}Service.queryByIds(Sets.newHashSet(ids.split(","))));
    }

    @ApiOperation(value = "单行删除", notes = "根据ID单行物理删除数据")
    @GetMapping("/remove")
    public JsonResult<Integer> remove(@RequestParam Long id) {
        return JsonResult.success(${table.moduleName ? uncap_first}Service.deleteById(id));
    }

    @ApiOperation(value = "批量删除", notes = "根据ID串批量物理删除数据")
    @GetMapping("/removeBatch")
    public JsonResult<Integer> removeBatch(@RequestParam String ids) {
        return JsonResult.success(${table.moduleName ? uncap_first}Service.deleteByIds(Sets.newHashSet(ids.split(","))));
    }

    @ApiOperation(value = "单行修改", notes = "根据ID单行修改数据")
    @PostMapping("/edit")
    public JsonResult<Integer> edit(@RequestBody ${table.moduleName}Entity update) {
        return JsonResult.success(${table.moduleName ? uncap_first}Service.updateById(update));
    }

    @ApiOperation(value = "单行创建", notes = "单行创建数据")
    @PostMapping("/create")
    public JsonResult<Integer> create(@RequestBody ${table.moduleName}Entity update) {
        return JsonResult.success(${table.moduleName ? uncap_first}Service.save(update));
    }

    @ApiOperation(value = "批量创建", notes = "批量创建数据")
    @PostMapping("/createBatch")
    public JsonResult<Integer> createBatch(@RequestBody List<${table.moduleName}Entity> updates) {
        return JsonResult.success(${table.moduleName ? uncap_first}Service.saveBatch(updates));
    }
}

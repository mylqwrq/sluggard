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
import com.mylq.core.base.result.JsonResult;
import com.mylq.core.base.result.PageResult;
import com.mylq.core.util.BeanUtil;
import ${project.basePackage}.common.entity.${table.moduleName}Entity;
import ${project.basePackage}.common.query.${table.moduleName}Query;
import ${project.basePackage}.common.vo.${table.moduleName}VO;
import ${project.basePackage}.service.api.${table.moduleName}Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ${table.tableComment}控制层接口
 *
 * @author ${author}
 * @date ${date}
 * @since 1.0.0
 */
@Api(value = "/${table.moduleName ? uncap_first}", tags = "${table.tableComment}控制层接口")
@RequestMapping("/${table.moduleName ? uncap_first}")
@RestController
public class ${table.moduleName}Controller {

    @Autowired
    private ${table.moduleName}Service ${table.moduleName ? uncap_first}Service;

    @ApiOperation(value = "分页查询", notes = "分页精准查询")
    @PostMapping("/searchPage")
    public JsonResult<PageResult<${table.moduleName}VO>> searchPage(@RequestBody ${table.moduleName}VO query, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageResult<${table.moduleName}Entity> result = ${table.moduleName ? uncap_first}Service.queryPage(convertToQuery(query), pageNum, pageSize);
        return JsonResult.success(result.convertData(${table.moduleName}VO.class));
    }

    @ApiOperation(value = "列表查询", notes = "列表精准查询")
    @PostMapping("/searchList")
    public JsonResult<List<${table.moduleName}VO>> searchList(@RequestBody ${table.moduleName}VO query) {
        List<${table.moduleName}Entity> result = ${table.moduleName ? uncap_first}Service.queryList(convertToQuery(query));
        return JsonResult.success(convertBatchToVO(result));
    }

    @ApiOperation(value = "单行查询", notes = "根据ID单行查询数据")
    @GetMapping("/searchById")
    public JsonResult<${table.moduleName}VO> searchById(@RequestParam Long id) {
        ${table.moduleName}Entity result = ${table.moduleName ? uncap_first}Service.queryById(id);
        return JsonResult.success(convertToVO(result));
    }

    @ApiOperation(value = "批量查询", notes = "根据ID串批量查询数据")
    @GetMapping("/searchByIds")
    public JsonResult<List<${table.moduleName}VO>> searchByIds(@RequestParam String ids) {
        List<${table.moduleName}Entity> result = ${table.moduleName ? uncap_first}Service.queryByIds(Sets.newHashSet(ids.split(",")));
        return JsonResult.success(convertBatchToVO(result));
    }

    @ApiOperation(value = "单行删除", notes = "根据ID单行物理删除数据")
    @GetMapping("/remove")
    public JsonResult<Integer> remove(@RequestParam Long id) {
        int result = ${table.moduleName ? uncap_first}Service.deleteById(id);
        return JsonResult.success(result);
    }

    @ApiOperation(value = "批量删除", notes = "根据ID串批量物理删除数据")
    @GetMapping("/removeBatch")
    public JsonResult<Integer> removeBatch(@RequestParam String ids) {
        int result = ${table.moduleName ? uncap_first}Service.deleteByIds(Sets.newHashSet(ids.split(",")));
        return JsonResult.success(result);
    }

    @ApiOperation(value = "单行修改", notes = "根据ID单行修改数据")
    @PostMapping("/edit")
    public JsonResult<Integer> edit(@RequestBody ${table.moduleName}VO update) {
        int result = ${table.moduleName ? uncap_first}Service.updateById(convertToEntity(update));
        return JsonResult.success(result);
    }

    @ApiOperation(value = "单行创建", notes = "单行创建数据")
    @PostMapping("/create")
    public JsonResult<Integer> create(@RequestBody ${table.moduleName}VO update) {
        int result = ${table.moduleName ? uncap_first}Service.save(convertToEntity(update));
        return JsonResult.success(result);
    }

    @ApiOperation(value = "批量创建", notes = "批量创建数据")
    @PostMapping("/createBatch")
    public JsonResult<Integer> createBatch(@RequestBody List<${table.moduleName}VO> updates) {
        int result = ${table.moduleName ? uncap_first}Service.saveBatch(convertBatchToEntity(updates));
        return JsonResult.success(result);
    }

    private ${table.moduleName}Query convertToQuery(${table.moduleName}VO vo) {
        return BeanUtil.copy(vo, ${table.moduleName}Query.class);
    }

    private ${table.moduleName}Entity convertToEntity(${table.moduleName}VO vo) {
        return BeanUtil.copy(vo, ${table.moduleName}Entity.class);
    }

    private ${table.moduleName}VO convertToVO(${table.moduleName}Entity entity) {
        return BeanUtil.copy(entity, ${table.moduleName}VO.class);
    }

    private List<${table.moduleName}Entity> convertBatchToEntity(List<${table.moduleName}VO> vos) {
        return BeanUtil.copyBatch(vos, ${table.moduleName}Entity.class);
    }

    private List<${table.moduleName}VO> convertBatchToVO(List<${table.moduleName}Entity> entities) {
        return BeanUtil.copyBatch(entities, ${table.moduleName}VO.class);
    }
}

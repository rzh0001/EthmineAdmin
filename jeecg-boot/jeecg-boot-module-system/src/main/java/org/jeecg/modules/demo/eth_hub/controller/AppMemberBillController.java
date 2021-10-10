package org.jeecg.modules.demo.eth_hub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberBill;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: app_member_bill
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Api(tags = "app_member_bill")
@RestController
@RequestMapping("/eth_hub/appMemberBill")
@Slf4j
public class AppMemberBillController extends JeecgController<AppMemberBill, IAppMemberBillService> {
    @Autowired
    private IAppMemberBillService appMemberBillService;

    /**
     * 分页列表查询
     *
     * @param appMemberBill 查询条件
     * @param pageNo        页码
     * @param pageSize      分页大小
     * @param req           请求体
     * @return 结果
     */
    @AutoLog(value = "app_member_bill-分页列表查询")
    @ApiOperation(value = "app_member_bill-分页列表查询", notes = "app_member_bill-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(AppMemberBill appMemberBill,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<AppMemberBill> queryWrapper = QueryGenerator.initQueryWrapper(appMemberBill, req.getParameterMap());
        Page<AppMemberBill> page = new Page<>(pageNo, pageSize);
        IPage<AppMemberBill> pageList = appMemberBillService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param appMemberBill 实体参数
     * @return 结果
     */
    @AutoLog(value = "app_member_bill-添加")
    @ApiOperation(value = "app_member_bill-添加", notes = "app_member_bill-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody AppMemberBill appMemberBill) {
        appMemberBillService.save(appMemberBill);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param appMemberBill 实体参数
     * @return 结果
     */
    @AutoLog(value = "app_member_bill-编辑")
    @ApiOperation(value = "app_member_bill-编辑", notes = "app_member_bill-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody AppMemberBill appMemberBill) {
        appMemberBillService.updateById(appMemberBill);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id 主键ID
     * @return 结果
     */
    @AutoLog(value = "app_member_bill-通过id删除")
    @ApiOperation(value = "app_member_bill-通过id删除", notes = "app_member_bill-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id") String id) {
        appMemberBillService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids 主键IDs
     * @return 结果
     */
    @AutoLog(value = "app_member_bill-批量删除")
    @ApiOperation(value = "app_member_bill-批量删除", notes = "app_member_bill-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids") String ids) {
        this.appMemberBillService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id 主键ID
     * @return 结果
     */
    @AutoLog(value = "app_member_bill-通过id查询")
    @ApiOperation(value = "app_member_bill-通过id查询", notes = "app_member_bill-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id") String id) {
        AppMemberBill appMemberBill = appMemberBillService.getById(id);
        if (appMemberBill == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(appMemberBill);
    }

    /**
     * 导出excel
     *
     * @param request       请求体
     * @param appMemberBill 查询参数
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AppMemberBill appMemberBill) {
        return super.exportXls(request, appMemberBill, AppMemberBill.class, "app_member_bill");
    }

    /**
     * 通过excel导入数据
     *
     * @param request  请求体
     * @param response 响应体
     * @return 结果
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AppMemberBill.class);
    }

}

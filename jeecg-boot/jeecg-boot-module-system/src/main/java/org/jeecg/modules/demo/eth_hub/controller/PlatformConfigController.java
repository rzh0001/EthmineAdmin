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
import org.jeecg.modules.demo.eth_hub.entity.PlatformConfig;
import org.jeecg.modules.demo.eth_hub.service.IPlatformConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: platform_config
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Api(tags = "platform_config")
@RestController
@RequestMapping("/eth_hub/platformConfig")
@Slf4j
public class PlatformConfigController extends JeecgController<PlatformConfig, IPlatformConfigService> {
    @Autowired
    private IPlatformConfigService platformConfigService;

    /**
     * 分页列表查询
     *
     * @param platformConfig 查询条件
     * @param pageNo         页码
     * @param pageSize       分页大小
     * @param req            请求体
     * @return 查询结果
     */
    @AutoLog(value = "platform_config-分页列表查询")
    @ApiOperation(value = "platform_config-分页列表查询", notes = "platform_config-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(PlatformConfig platformConfig,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<PlatformConfig> queryWrapper = QueryGenerator.initQueryWrapper(platformConfig, req.getParameterMap());
        Page<PlatformConfig> page = new Page<>(pageNo, pageSize);
        IPage<PlatformConfig> pageList = platformConfigService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param platformConfig 实体参数
     * @return 结果
     */
    @AutoLog(value = "platform_config-添加")
    @ApiOperation(value = "platform_config-添加", notes = "platform_config-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody PlatformConfig platformConfig) {
        platformConfigService.save(platformConfig);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param platformConfig 实体参数
     * @return 结果
     */
    @AutoLog(value = "platform_config-编辑")
    @ApiOperation(value = "platform_config-编辑", notes = "platform_config-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody PlatformConfig platformConfig) {
        platformConfigService.updateById(platformConfig);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id 主键ID
     * @return 结果
     */
    @AutoLog(value = "platform_config-通过id删除")
    @ApiOperation(value = "platform_config-通过id删除", notes = "platform_config-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id") String id) {
        platformConfigService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids 主键ID
     * @return 结果
     */
    @AutoLog(value = "platform_config-批量删除")
    @ApiOperation(value = "platform_config-批量删除", notes = "platform_config-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids") String ids) {
        this.platformConfigService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id 主键ID
     * @return 结果
     */
    @AutoLog(value = "platform_config-通过id查询")
    @ApiOperation(value = "platform_config-通过id查询", notes = "platform_config-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id") String id) {
        PlatformConfig platformConfig = platformConfigService.getById(id);
        if (platformConfig == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(platformConfig);
    }

    /**
     * 导出excel
     *
     * @param request        请求体
     * @param platformConfig 查询参数
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PlatformConfig platformConfig) {
        return super.exportXls(request, platformConfig, PlatformConfig.class, "platform_config");
    }

    /**
     * 通过excel导入数据
     *
     * @param request  请求体
     * @param response 返回
     * @return 结果
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, PlatformConfig.class);
    }

}

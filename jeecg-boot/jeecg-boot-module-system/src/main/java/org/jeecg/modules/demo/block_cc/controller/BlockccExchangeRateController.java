package org.jeecg.modules.demo.block_cc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.block_cc.entity.BlockccExchangeRate;
import org.jeecg.modules.demo.block_cc.service.IBlockccExchangeRateService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: blockcc_exchange_rate
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Api(tags="blockcc_exchange_rate")
@RestController
@RequestMapping("/block_cc/blockccExchangeRate")
@Slf4j
public class BlockccExchangeRateController extends JeecgController<BlockccExchangeRate, IBlockccExchangeRateService> {
	@Autowired
	private IBlockccExchangeRateService blockccExchangeRateService;
	
	/**
	 * 分页列表查询
	 *
	 * @param blockccExchangeRate 查询条件
	 * @param pageNo    页码
	 * @param pageSize  分页大小
	 * @param req   请求体
	 * @return  结果
	 */
	@AutoLog(value = "blockcc_exchange_rate-分页列表查询")
	@ApiOperation(value="blockcc_exchange_rate-分页列表查询", notes="blockcc_exchange_rate-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BlockccExchangeRate blockccExchangeRate,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BlockccExchangeRate> queryWrapper = QueryGenerator.initQueryWrapper(blockccExchangeRate, req.getParameterMap());
		Page<BlockccExchangeRate> page = new Page<>(pageNo, pageSize);
		IPage<BlockccExchangeRate> pageList = blockccExchangeRateService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param blockccExchangeRate 实体参数
	 * @return  结果
	 */
	@AutoLog(value = "blockcc_exchange_rate-添加")
	@ApiOperation(value="blockcc_exchange_rate-添加", notes="blockcc_exchange_rate-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BlockccExchangeRate blockccExchangeRate) {
		blockccExchangeRateService.save(blockccExchangeRate);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param blockccExchangeRate 实体参数
	 * @return  结果
	 */
	@AutoLog(value = "blockcc_exchange_rate-编辑")
	@ApiOperation(value="blockcc_exchange_rate-编辑", notes="blockcc_exchange_rate-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BlockccExchangeRate blockccExchangeRate) {
		blockccExchangeRateService.updateById(blockccExchangeRate);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id    主键ID
	 * @return  结果
	 */
	@AutoLog(value = "blockcc_exchange_rate-通过id删除")
	@ApiOperation(value="blockcc_exchange_rate-通过id删除", notes="blockcc_exchange_rate-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id") String id) {
		blockccExchangeRateService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids   主键IDs
	 * @return  结果
	 */
	@AutoLog(value = "blockcc_exchange_rate-批量删除")
	@ApiOperation(value="blockcc_exchange_rate-批量删除", notes="blockcc_exchange_rate-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids") String ids) {
		this.blockccExchangeRateService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id    主键ID
	 * @return  结果
	 */
	@AutoLog(value = "blockcc_exchange_rate-通过id查询")
	@ApiOperation(value="blockcc_exchange_rate-通过id查询", notes="blockcc_exchange_rate-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id") String id) {
		BlockccExchangeRate blockccExchangeRate = blockccExchangeRateService.getById(id);
		if(blockccExchangeRate==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(blockccExchangeRate);
	}

    /**
    * 导出excel
    *
    * @param request    请求体
    * @param blockccExchangeRate  查询参数
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BlockccExchangeRate blockccExchangeRate) {
        return super.exportXls(request, blockccExchangeRate, BlockccExchangeRate.class, "blockcc_exchange_rate");
    }

    /**
      * 通过excel导入数据
    *
    * @param request    请求体
    * @param response   响应体
    * @return   结果
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BlockccExchangeRate.class);
    }

}

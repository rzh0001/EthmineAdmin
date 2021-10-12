package org.jeecg.modules.demo.eth_hub.controller;

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
import org.jeecg.modules.demo.eth_hub.entity.DigitalCurrency;
import org.jeecg.modules.demo.eth_hub.service.IDigitalCurrencyService;

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
 * @Description: digital_currency
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Api(tags="digital_currency")
@RestController
@RequestMapping("/eth_hub/digitalCurrency")
@Slf4j
public class DigitalCurrencyController extends JeecgController<DigitalCurrency, IDigitalCurrencyService> {
	@Autowired
	private IDigitalCurrencyService digitalCurrencyService;
	
	/**
	 * 分页列表查询
	 *
	 * @param digitalCurrency 查询条件
	 * @param pageNo    页码
	 * @param pageSize  分页大小
	 * @param req   请求体
	 * @return  结果
	 */
	@AutoLog(value = "digital_currency-分页列表查询")
	@ApiOperation(value="digital_currency-分页列表查询", notes="digital_currency-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DigitalCurrency digitalCurrency,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DigitalCurrency> queryWrapper = QueryGenerator.initQueryWrapper(digitalCurrency, req.getParameterMap());
		Page<DigitalCurrency> page = new Page<>(pageNo, pageSize);
		IPage<DigitalCurrency> pageList = digitalCurrencyService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param digitalCurrency 实体参数
	 * @return  结果
	 */
	@AutoLog(value = "digital_currency-添加")
	@ApiOperation(value="digital_currency-添加", notes="digital_currency-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DigitalCurrency digitalCurrency) {
		digitalCurrencyService.save(digitalCurrency);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param digitalCurrency 实体参数
	 * @return  结果
	 */
	@AutoLog(value = "digital_currency-编辑")
	@ApiOperation(value="digital_currency-编辑", notes="digital_currency-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DigitalCurrency digitalCurrency) {
		digitalCurrencyService.updateById(digitalCurrency);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id    主键ID
	 * @return  结果
	 */
	@AutoLog(value = "digital_currency-通过id删除")
	@ApiOperation(value="digital_currency-通过id删除", notes="digital_currency-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id") String id) {
		digitalCurrencyService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids   主键IDs
	 * @return  结果
	 */
	@AutoLog(value = "digital_currency-批量删除")
	@ApiOperation(value="digital_currency-批量删除", notes="digital_currency-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids") String ids) {
		this.digitalCurrencyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id    主键ID
	 * @return  结果
	 */
	@AutoLog(value = "digital_currency-通过id查询")
	@ApiOperation(value="digital_currency-通过id查询", notes="digital_currency-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id") String id) {
		DigitalCurrency digitalCurrency = digitalCurrencyService.getById(id);
		if(digitalCurrency==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(digitalCurrency);
	}

    /**
    * 导出excel
    *
    * @param request    请求体
    * @param digitalCurrency  查询参数
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DigitalCurrency digitalCurrency) {
        return super.exportXls(request, digitalCurrency, DigitalCurrency.class, "digital_currency");
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
        return super.importExcel(request, response, DigitalCurrency.class);
    }

}

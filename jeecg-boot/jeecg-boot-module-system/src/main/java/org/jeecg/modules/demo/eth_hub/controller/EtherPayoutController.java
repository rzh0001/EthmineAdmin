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
import org.jeecg.modules.demo.eth_hub.entity.EtherPayout;
import org.jeecg.modules.demo.eth_hub.service.IEtherPayoutService;

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
 * @Description: ether_payout
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Api(tags="ether_payout")
@RestController
@RequestMapping("/eth_hub/etherPayout")
@Slf4j
public class EtherPayoutController extends JeecgController<EtherPayout, IEtherPayoutService> {
	@Autowired
	private IEtherPayoutService etherPayoutService;
	
	/**
	 * 分页列表查询
	 *
	 * @param etherPayout
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ether_payout-分页列表查询")
	@ApiOperation(value="ether_payout-分页列表查询", notes="ether_payout-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EtherPayout etherPayout,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EtherPayout> queryWrapper = QueryGenerator.initQueryWrapper(etherPayout, req.getParameterMap());
		Page<EtherPayout> page = new Page<EtherPayout>(pageNo, pageSize);
		IPage<EtherPayout> pageList = etherPayoutService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param etherPayout
	 * @return
	 */
	@AutoLog(value = "ether_payout-添加")
	@ApiOperation(value="ether_payout-添加", notes="ether_payout-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EtherPayout etherPayout) {
		etherPayoutService.save(etherPayout);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param etherPayout
	 * @return
	 */
	@AutoLog(value = "ether_payout-编辑")
	@ApiOperation(value="ether_payout-编辑", notes="ether_payout-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EtherPayout etherPayout) {
		etherPayoutService.updateById(etherPayout);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ether_payout-通过id删除")
	@ApiOperation(value="ether_payout-通过id删除", notes="ether_payout-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		etherPayoutService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ether_payout-批量删除")
	@ApiOperation(value="ether_payout-批量删除", notes="ether_payout-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.etherPayoutService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ether_payout-通过id查询")
	@ApiOperation(value="ether_payout-通过id查询", notes="ether_payout-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EtherPayout etherPayout = etherPayoutService.getById(id);
		if(etherPayout==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(etherPayout);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param etherPayout
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EtherPayout etherPayout) {
        return super.exportXls(request, etherPayout, EtherPayout.class, "ether_payout");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EtherPayout.class);
    }

}

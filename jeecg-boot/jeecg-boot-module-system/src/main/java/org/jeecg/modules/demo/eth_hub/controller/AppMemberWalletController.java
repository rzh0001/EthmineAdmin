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
import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberWalletService;

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
 * @Description: app_member_wallet
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
@Api(tags="app_member_wallet")
@RestController
@RequestMapping("/eth_hub/appMemberWallet")
@Slf4j
public class AppMemberWalletController extends JeecgController<AppMemberWallet, IAppMemberWalletService> {
	@Autowired
	private IAppMemberWalletService appMemberWalletService;
	
	/**
	 * 分页列表查询
	 *
	 * @param appMemberWallet
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "app_member_wallet-分页列表查询")
	@ApiOperation(value="app_member_wallet-分页列表查询", notes="app_member_wallet-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppMemberWallet appMemberWallet,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppMemberWallet> queryWrapper = QueryGenerator.initQueryWrapper(appMemberWallet, req.getParameterMap());
		Page<AppMemberWallet> page = new Page<AppMemberWallet>(pageNo, pageSize);
		IPage<AppMemberWallet> pageList = appMemberWalletService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param appMemberWallet
	 * @return
	 */
	@AutoLog(value = "app_member_wallet-添加")
	@ApiOperation(value="app_member_wallet-添加", notes="app_member_wallet-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppMemberWallet appMemberWallet) {
		appMemberWalletService.save(appMemberWallet);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param appMemberWallet
	 * @return
	 */
	@AutoLog(value = "app_member_wallet-编辑")
	@ApiOperation(value="app_member_wallet-编辑", notes="app_member_wallet-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppMemberWallet appMemberWallet) {
		appMemberWalletService.updateById(appMemberWallet);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "app_member_wallet-通过id删除")
	@ApiOperation(value="app_member_wallet-通过id删除", notes="app_member_wallet-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		appMemberWalletService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "app_member_wallet-批量删除")
	@ApiOperation(value="app_member_wallet-批量删除", notes="app_member_wallet-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appMemberWalletService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "app_member_wallet-通过id查询")
	@ApiOperation(value="app_member_wallet-通过id查询", notes="app_member_wallet-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppMemberWallet appMemberWallet = appMemberWalletService.getById(id);
		if(appMemberWallet==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(appMemberWallet);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param appMemberWallet
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AppMemberWallet appMemberWallet) {
        return super.exportXls(request, appMemberWallet, AppMemberWallet.class, "app_member_wallet");
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
        return super.importExcel(request, response, AppMemberWallet.class);
    }

}

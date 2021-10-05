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
import org.jeecg.modules.demo.eth_hub.entity.EtherWorkerOnline;
import org.jeecg.modules.demo.eth_hub.service.IEtherWorkerOnlineService;

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
 * @Description: ether_worker_online
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Api(tags="ether_worker_online")
@RestController
@RequestMapping("/eth_hub/etherWorkerOnline")
@Slf4j
public class EtherWorkerOnlineController extends JeecgController<EtherWorkerOnline, IEtherWorkerOnlineService> {
	@Autowired
	private IEtherWorkerOnlineService etherWorkerOnlineService;
	
	/**
	 * 分页列表查询
	 *
	 * @param etherWorkerOnline
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ether_worker_online-分页列表查询")
	@ApiOperation(value="ether_worker_online-分页列表查询", notes="ether_worker_online-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EtherWorkerOnline etherWorkerOnline,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EtherWorkerOnline> queryWrapper = QueryGenerator.initQueryWrapper(etherWorkerOnline, req.getParameterMap());
		Page<EtherWorkerOnline> page = new Page<EtherWorkerOnline>(pageNo, pageSize);
		IPage<EtherWorkerOnline> pageList = etherWorkerOnlineService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param etherWorkerOnline
	 * @return
	 */
	@AutoLog(value = "ether_worker_online-添加")
	@ApiOperation(value="ether_worker_online-添加", notes="ether_worker_online-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EtherWorkerOnline etherWorkerOnline) {
		etherWorkerOnlineService.save(etherWorkerOnline);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param etherWorkerOnline
	 * @return
	 */
	@AutoLog(value = "ether_worker_online-编辑")
	@ApiOperation(value="ether_worker_online-编辑", notes="ether_worker_online-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EtherWorkerOnline etherWorkerOnline) {
		etherWorkerOnlineService.updateById(etherWorkerOnline);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ether_worker_online-通过id删除")
	@ApiOperation(value="ether_worker_online-通过id删除", notes="ether_worker_online-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		etherWorkerOnlineService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ether_worker_online-批量删除")
	@ApiOperation(value="ether_worker_online-批量删除", notes="ether_worker_online-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.etherWorkerOnlineService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ether_worker_online-通过id查询")
	@ApiOperation(value="ether_worker_online-通过id查询", notes="ether_worker_online-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EtherWorkerOnline etherWorkerOnline = etherWorkerOnlineService.getById(id);
		if(etherWorkerOnline==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(etherWorkerOnline);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param etherWorkerOnline
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EtherWorkerOnline etherWorkerOnline) {
        return super.exportXls(request, etherWorkerOnline, EtherWorkerOnline.class, "ether_worker_online");
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
        return super.importExcel(request, response, EtherWorkerOnline.class);
    }

}

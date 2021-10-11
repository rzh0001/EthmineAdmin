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
import org.jeecg.modules.demo.eth_hub.entity.EtherWorker;
import org.jeecg.modules.demo.eth_hub.service.IEtherWorkerService;

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
 * @Description: ether_worker
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Api(tags="ether_worker")
@RestController
@RequestMapping("/eth_hub/etherWorker")
@Slf4j
public class EtherWorkerController extends JeecgController<EtherWorker, IEtherWorkerService> {
	@Autowired
	private IEtherWorkerService etherWorkerService;
	
	/**
	 * 分页列表查询
	 *
	 * @param etherWorker 查询条件
	 * @param pageNo    页码
	 * @param pageSize  分页大小
	 * @param req   请求体
	 * @return  结果
	 */
	@AutoLog(value = "ether_worker-分页列表查询")
	@ApiOperation(value="ether_worker-分页列表查询", notes="ether_worker-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EtherWorker etherWorker,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EtherWorker> queryWrapper = QueryGenerator.initQueryWrapper(etherWorker, req.getParameterMap());
		Page<EtherWorker> page = new Page<>(pageNo, pageSize);
		IPage<EtherWorker> pageList = etherWorkerService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param etherWorker 实体参数
	 * @return  结果
	 */
	@AutoLog(value = "ether_worker-添加")
	@ApiOperation(value="ether_worker-添加", notes="ether_worker-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EtherWorker etherWorker) {
		etherWorkerService.save(etherWorker);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param etherWorker 实体参数
	 * @return  结果
	 */
	@AutoLog(value = "ether_worker-编辑")
	@ApiOperation(value="ether_worker-编辑", notes="ether_worker-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EtherWorker etherWorker) {
		etherWorkerService.updateById(etherWorker);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id    主键ID
	 * @return  结果
	 */
	@AutoLog(value = "ether_worker-通过id删除")
	@ApiOperation(value="ether_worker-通过id删除", notes="ether_worker-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id") String id) {
		etherWorkerService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids   主键IDs
	 * @return  结果
	 */
	@AutoLog(value = "ether_worker-批量删除")
	@ApiOperation(value="ether_worker-批量删除", notes="ether_worker-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids") String ids) {
		this.etherWorkerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id    主键ID
	 * @return  结果
	 */
	@AutoLog(value = "ether_worker-通过id查询")
	@ApiOperation(value="ether_worker-通过id查询", notes="ether_worker-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id") String id) {
		EtherWorker etherWorker = etherWorkerService.getById(id);
		if(etherWorker==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(etherWorker);
	}

    /**
    * 导出excel
    *
    * @param request    请求体
    * @param etherWorker  查询参数
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EtherWorker etherWorker) {
        return super.exportXls(request, etherWorker, EtherWorker.class, "ether_worker");
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
        return super.importExcel(request, response, EtherWorker.class);
    }

}

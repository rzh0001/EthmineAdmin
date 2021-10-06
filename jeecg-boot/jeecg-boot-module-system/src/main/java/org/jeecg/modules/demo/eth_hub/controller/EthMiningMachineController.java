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
import org.jeecg.modules.demo.eth_hub.entity.EthMiningMachine;
import org.jeecg.modules.demo.eth_hub.service.IEthMiningMachineService;

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
 * @Description: eth_mining_machine
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Api(tags="eth_mining_machine")
@RestController
@RequestMapping("/eth_hub/ethMiningMachine")
@Slf4j
public class EthMiningMachineController extends JeecgController<EthMiningMachine, IEthMiningMachineService> {
	@Autowired
	private IEthMiningMachineService ethMiningMachineService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ethMiningMachine
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "eth_mining_machine-分页列表查询")
	@ApiOperation(value="eth_mining_machine-分页列表查询", notes="eth_mining_machine-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EthMiningMachine ethMiningMachine,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EthMiningMachine> queryWrapper = QueryGenerator.initQueryWrapper(ethMiningMachine, req.getParameterMap());
		Page<EthMiningMachine> page = new Page<EthMiningMachine>(pageNo, pageSize);
		IPage<EthMiningMachine> pageList = ethMiningMachineService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ethMiningMachine
	 * @return
	 */
	@AutoLog(value = "eth_mining_machine-添加")
	@ApiOperation(value="eth_mining_machine-添加", notes="eth_mining_machine-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EthMiningMachine ethMiningMachine) {
		ethMiningMachineService.save(ethMiningMachine);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ethMiningMachine
	 * @return
	 */
	@AutoLog(value = "eth_mining_machine-编辑")
	@ApiOperation(value="eth_mining_machine-编辑", notes="eth_mining_machine-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EthMiningMachine ethMiningMachine) {
		ethMiningMachineService.updateById(ethMiningMachine);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "eth_mining_machine-通过id删除")
	@ApiOperation(value="eth_mining_machine-通过id删除", notes="eth_mining_machine-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ethMiningMachineService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "eth_mining_machine-批量删除")
	@ApiOperation(value="eth_mining_machine-批量删除", notes="eth_mining_machine-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ethMiningMachineService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "eth_mining_machine-通过id查询")
	@ApiOperation(value="eth_mining_machine-通过id查询", notes="eth_mining_machine-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EthMiningMachine ethMiningMachine = ethMiningMachineService.getById(id);
		if(ethMiningMachine==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ethMiningMachine);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ethMiningMachine
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EthMiningMachine ethMiningMachine) {
        return super.exportXls(request, ethMiningMachine, EthMiningMachine.class, "eth_mining_machine");
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
        return super.importExcel(request, response, EthMiningMachine.class);
    }

}

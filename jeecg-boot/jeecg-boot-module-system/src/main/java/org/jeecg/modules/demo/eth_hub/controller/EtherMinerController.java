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
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.service.IEtherMinerService;
import org.jeecg.modules.eth_hub.dao.AppMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: ether_miner
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Api(tags = "ether_miner")
@RestController
@RequestMapping("/eth_hub/etherMiner")
@Slf4j
public class EtherMinerController extends JeecgController<EtherMiner, IEtherMinerService> {
    @Autowired
    private IEtherMinerService etherMinerService;

    @Autowired
    private AppMemberRepository memberRepository;

    /**
     * 分页列表查询
     *
     * @param etherMiner
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "ether_miner-分页列表查询")
    @ApiOperation(value = "ether_miner-分页列表查询", notes = "ether_miner-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(EtherMiner etherMiner,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<EtherMiner> queryWrapper = QueryGenerator.initQueryWrapper(etherMiner, req.getParameterMap());
        Page<EtherMiner> page = new Page<EtherMiner>(pageNo, pageSize);
        IPage<EtherMiner> pageList = etherMinerService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param etherMiner
     * @return
     */
    @AutoLog(value = "ether_miner-添加")
    @ApiOperation(value = "ether_miner-添加", notes = "ether_miner-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody EtherMiner etherMiner) {
        etherMinerService.save(etherMiner);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param etherMiner
     * @return
     */
    @AutoLog(value = "ether_miner-编辑")
    @ApiOperation(value = "ether_miner-编辑", notes = "ether_miner-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody EtherMiner etherMiner) {
        if (etherMiner.getMemberUsername() != null) {
            AppMember appMember = memberRepository.findByUsername(etherMiner.getMemberUsername());
            if (appMember.getStatus() != 1) {
                return Result.error("该会员未激活！");
            }
            if (appMember.getChargeRate() == null) {
                return Result.error("该会员未设置手续费率！");
            }
            etherMiner.setMemberId(appMember.getId());
            etherMiner.setMemberNickname(appMember.getNickname());
        }
        etherMinerService.updateById(etherMiner);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "ether_miner-通过id删除")
    @ApiOperation(value = "ether_miner-通过id删除", notes = "ether_miner-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        etherMinerService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "ether_miner-批量删除")
    @ApiOperation(value = "ether_miner-批量删除", notes = "ether_miner-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.etherMinerService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "ether_miner-通过id查询")
    @ApiOperation(value = "ether_miner-通过id查询", notes = "ether_miner-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        EtherMiner etherMiner = etherMinerService.getById(id);
        if (etherMiner == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(etherMiner);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param etherMiner
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EtherMiner etherMiner) {
        return super.exportXls(request, etherMiner, EtherMiner.class, "ether_miner");
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
        return super.importExcel(request, response, EtherMiner.class);
    }

}

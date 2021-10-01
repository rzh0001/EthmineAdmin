package org.jeecg.modules.demo.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.mapper.EtherMinerMapper;
import org.jeecg.modules.demo.eth_hub.service.IEtherMinerService;
import org.jeecg.modules.eth_hub.dao.AppMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: ether_miner
 * @Author: jeecg-boot
 * @Date: 2021-09-30
 * @Version: V1.0
 */
@Service
public class EtherMinerServiceImpl extends ServiceImpl<EtherMinerMapper, EtherMiner> implements IEtherMinerService {

    @Autowired
    private AppMemberRepository memberRepository;

    @Override
    public boolean updateAppMemberInfo(EtherMiner miner) {
        AppMember appMember = memberRepository.findByUsername(miner.getMemberUsername());
        if (BeanUtil.isEmpty(appMember)) {
            log.error("选择的会员[" + miner.getMemberUsername() + "]不存在");
            throw new JeecgBootException("选择的会员[" + miner.getMemberUsername() + "]不存在");
        }

        miner.setMemberId(appMember.getId());
        miner.setMemberNickname(appMember.getNickname());
        return updateById(miner);
    }
}

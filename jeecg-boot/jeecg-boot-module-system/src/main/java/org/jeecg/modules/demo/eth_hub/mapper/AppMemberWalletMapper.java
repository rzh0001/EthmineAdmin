package org.jeecg.modules.demo.eth_hub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;

import java.math.BigDecimal;

/**
 * @Description: app_member_wallet
 * @Author: jeecg-boot
 * @Version: V1.0
 */
public interface AppMemberWalletMapper extends BaseMapper<AppMemberWallet> {

    @Update("update app_member_wallet set balance = balance + #{amount},total_earnings = total_earnings + #{amount} where id = #{id}")
    boolean income(String id, BigDecimal amount);

    @Update("update app_member_wallet set balance = balance - #{amount} where id = #{id} and balance - #{amount} >= 0")
    boolean payout(String id, BigDecimal amount);
}

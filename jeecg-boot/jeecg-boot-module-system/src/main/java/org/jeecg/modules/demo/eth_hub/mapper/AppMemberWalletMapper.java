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

    @Update("update app_member_wallet set balance = balance + #{amount},total_earnings = total_earnings + #{amount} where member_id = #{id} and currency = #{currency}")
    void income(String id, String currency, BigDecimal amount);
}

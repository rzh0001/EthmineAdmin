package org.jeecg.modules.demo.eth_hub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;

/**
 * @Description: app_member_wallet
 * @Author: jeecg-boot
 * @Version: V1.0
 */
public interface IAppMemberWalletService extends IService<AppMemberWallet> {

    void newWallet(AppMember member, String currency);

}

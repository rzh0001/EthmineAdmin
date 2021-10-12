package org.jeecg.modules.demo.eth_hub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.eth_hub.entity.DigitalCurrency;

/**
 * @Description: digital_currency
 * @Author: jeecg-boot
 * @Version: V1.0
 */
public interface IDigitalCurrencyService extends IService<DigitalCurrency> {

    DigitalCurrency eth();
}

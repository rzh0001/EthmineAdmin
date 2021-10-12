package org.jeecg.modules.demo.eth_hub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.eth_hub.entity.DigitalCurrency;
import org.jeecg.modules.demo.eth_hub.mapper.DigitalCurrencyMapper;
import org.jeecg.modules.demo.eth_hub.service.IDigitalCurrencyService;
import org.springframework.stereotype.Service;

/**
 * @Description: digital_currency
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Service
public class DigitalCurrencyServiceImpl extends ServiceImpl<DigitalCurrencyMapper, DigitalCurrency> implements IDigitalCurrencyService {


    @Override
    public DigitalCurrency eth() {
        return baseMapper.selectById("ETH");
    }
}

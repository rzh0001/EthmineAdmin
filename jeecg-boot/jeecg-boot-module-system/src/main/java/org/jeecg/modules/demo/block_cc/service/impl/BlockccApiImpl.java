package org.jeecg.modules.demo.block_cc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.demo.block_cc.entity.BlockccExchangeRate;
import org.jeecg.modules.demo.block_cc.entity.BlockccExchangeRateDTO;
import org.jeecg.modules.demo.block_cc.entity.BlockccPrice;
import org.jeecg.modules.demo.block_cc.entity.BlockccPriceDTO;
import org.jeecg.modules.demo.block_cc.service.BlockccApi;
import org.jeecg.modules.demo.block_cc.service.IBlockccExchangeRateService;
import org.jeecg.modules.demo.block_cc.service.IBlockccPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BlockccApiImpl implements BlockccApi {

    String headerKey = "X-API-KEY";
    String headerValue = "6PF6L8YMZS750OPJOQR6NHHHFCVIGV0K3PEZGOAD";

    @Autowired
    private IBlockccExchangeRateService rateService;
    @Autowired
    private IBlockccPriceService priceService;

    @Override
    public void exchangeRate() {
        String body = HttpRequest.get(exchangeUrl).header(headerKey, headerValue).timeout(20000).execute().body();

        JSONArray jsonArray = JSONUtil.parseArray(body);
        List<BlockccExchangeRateDTO> list = JSONUtil.toList(jsonArray, BlockccExchangeRateDTO.class);

        List<BlockccExchangeRate> data = new ArrayList<>();
        for (BlockccExchangeRateDTO unit : list) {
            BlockccExchangeRate rate = new BlockccExchangeRate();
            BeanUtil.copyProperties(unit, rate);
            rate.setId("USD-" + unit.getCurrency());
            rate.setRate(unit.getRate());
            rate.setCurrency(unit.getCurrency());
            data.add(rate);
        }
        rateService.saveOrUpdateBatch(data);
    }

    @Override
    public void price() {
        String body = HttpRequest.get(priceUrl).header(headerKey, headerValue).timeout(20000).execute().body();
        JSONArray jsonArray = JSONUtil.parseArray(body);
        List<BlockccPriceDTO> list = JSONUtil.toList(jsonArray, BlockccPriceDTO.class);

        List<BlockccPrice> data = new ArrayList<>();
        for (BlockccPriceDTO unit : list) {
            BlockccPrice price = new BlockccPrice();
            BeanUtil.copyProperties(unit, price);
            price.setCurrency(unit.getSlug());
            price.setId(unit.getSymbol());
            price.setTime(DateUtil.date(unit.getTime()));
            price.setBtc(unit.getBtc());
            price.setUsd(unit.getUsd());
            price.setVolume(unit.getVolume());
            price.setVolumeUsd(unit.getVolumeUsd());
            data.add(price);
        }
        priceService.saveOrUpdateBatch(data);
    }
}

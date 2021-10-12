package org.jeecg.modules.eth_hub.job;

import org.jeecg.modules.demo.crypto_compare.CryptoCompareRestApi;
import org.jeecg.modules.demo.crypto_compare.EthPrice;
import org.jeecg.modules.demo.eth_hub.entity.DigitalCurrency;
import org.jeecg.modules.demo.eth_hub.service.IDigitalCurrencyService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class CryptoCompareSyncJob implements Job {

    @Autowired
    private IDigitalCurrencyService currencyService;

    @Autowired
    private CryptoCompareRestApi api;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        EthPrice price = api.getPrice();

        DigitalCurrency currency = new DigitalCurrency();
        currency.setId("ETH");
        currency.setCurrency("ETH");
        currency.setCny(price.getCny());
        currency.setUsd(price.getUsd());

        currencyService.saveOrUpdate(currency);
    }
}

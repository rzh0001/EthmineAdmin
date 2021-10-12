package org.jeecg.modules.demo.block_cc.service;

/**
 * 蜜蜂查商业API
 * website: https://blockcc-api-document.pages.dev/zh_CN/#152d3db758
 */
public interface BlockccApi {

    String url = "https://data.mifengcha.com/api";
    String exchangeUrl = url + "/v3/exchange_rate";
    String priceUrl = url + "/v3/price?slug=ethereum,bitcoin,filecoin";

    /**
     * 获取汇率
     */
    void exchangeRate();

    void price();


}

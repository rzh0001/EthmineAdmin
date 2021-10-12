package org.jeecg.modules.demo.block_cc.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.jeecg.modules.demo.block_cc.entity.BlockccExchangeRateDTO;
import org.jeecg.modules.demo.block_cc.service.BlockccApi;
import org.jeecg.modules.demo.crypto_compare.CryptoCompareRestApi;
import org.jeecg.modules.demo.crypto_compare.CryptoCompareRestApiImpl;
import org.jeecg.modules.demo.crypto_compare.EthPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JeecgSystemApplication.class)
class BlockccApiImplTest {

    String headerKey = "X-API-KEY";
    String headerValue = "6PF6L8YMZS750OPJOQR6NHHHFCVIGV0K3PEZGOAD";

    @Autowired
    private BlockccApi api;

    @Test
    void exchangeRate() {
//        api.exchangeRate();
        String body = HttpRequest.get("https://data.mifengcha.com/api/v3/exchange_rate").header(headerKey, headerValue).timeout(20000).execute().body();
        JSONArray jsonArray = JSONUtil.parseArray(body);
        List<BlockccExchangeRateDTO> list = JSONUtil.toList(jsonArray, BlockccExchangeRateDTO.class);
    }


    @Test
    void price() {
        Map<String, String> param = new HashMap<>();
        param.put("slug", "ethereum,bitcoin,filecoin");

        String body = HttpRequest.get("https://data.mifengcha.com/api/v3/price?slug=ethereum,bitcoin,filecoin").header(headerKey, headerValue).timeout(20000).execute().body();
//        JSONArray jsonArray = JSONUtil.parseArray(body);
//        List<ExchangeRateDTO> list = JSONUtil.toList(jsonArray, ExchangeRateDTO.class);
    }

    @Test
    void test() {
        CryptoCompareRestApi api = new CryptoCompareRestApiImpl();
        EthPrice price = api.getPrice();
    }
}
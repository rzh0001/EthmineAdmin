package org.jeecg.modules.demo.crypto_compare;

import cn.hutool.json.JSONUtil;
import org.jeecg.modules.ruan.RuanTool;

public class CryptoCompareRestApiImpl implements CryptoCompareRestApi {

    String apiKey = "0c2cad87f2ff7007b99f38292e572118ec49b213926d8baf0f89e9cc864fd95d";

    @Override
    public EthPrice getPrice() {
        String res = RuanTool.httpGet("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD,CNY&api_key=" + apiKey);
        return JSONUtil.toBean(res, EthPrice.class);
    }
}

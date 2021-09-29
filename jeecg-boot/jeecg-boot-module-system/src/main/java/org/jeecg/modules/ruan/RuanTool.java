package org.jeecg.modules.ruan;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

/**
 * 个人工具类
 *
 * @author ruanzh
 */
@Slf4j
public class RuanTool {

    /**
     * 字符串拼接，参考Slf4j源码
     *
     * @param format
     * @param args
     * @return
     */
    public static String concat(String format, Object... args) {
        return MessageFormatter.arrayFormat(format, args).getMessage();
    }

    public static String httpGet(String url) {
        log.info("http get: {}", url);
        String res = HttpUtil.get(url);
        log.info("http response: {}", res);
        return HttpUtil.get(url);
    }

}

package org.jeecg.modules.ruan;

import cn.hutool.core.date.DateBetween;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

import java.sql.Timestamp;
import java.util.Date;

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
        String res = HttpUtil.get(url, 40000);
//        log.info("http response: {}", res);
        return HttpUtil.get(url);
    }

    /**
     * 矿池API返回的时间格式是 以秒未单位的 Timestamp， 要转化为Date类型再保存到数据库
     *
     * @param time
     * @return
     */
    public static Timestamp convertTime(Integer time) {
        return Timestamp.valueOf(LocalDateTimeUtil.of(Long.valueOf(time) * 1000));
    }

    /**
     * 判断两个日期相差的分钟数(绝对值)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long betweenDateToMinute(Date date1, Date date2) {
        DateBetween dateBetween = DateBetween.create(date1, date2);
        return dateBetween.between(DateUnit.MINUTE);
    }

    public static String dateToHourString(Date date) {
        FastDateFormat instance = FastDateFormat.getInstance("yyyy-MM-dd HH");
        return instance.format(date);
    }

}

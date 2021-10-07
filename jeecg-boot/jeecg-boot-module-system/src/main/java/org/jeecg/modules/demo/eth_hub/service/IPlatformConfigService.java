package org.jeecg.modules.demo.eth_hub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.eth_hub.entity.PlatformConfig;

/**
 * @Description: platform_config
 * @Author: jeecg-boot
 * @Version: V1.0
 */
public interface IPlatformConfigService extends IService<PlatformConfig> {

    String getByConfigKey(String key);
}

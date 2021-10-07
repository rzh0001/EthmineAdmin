package org.jeecg.modules.demo.eth_hub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.demo.eth_hub.entity.PlatformConfig;
import org.jeecg.modules.demo.eth_hub.mapper.PlatformConfigMapper;
import org.jeecg.modules.demo.eth_hub.service.IPlatformConfigService;
import org.jeecg.modules.eth_hub.dao.PlatformConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Description: platform_config
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Service
public class PlatformConfigServiceImpl extends ServiceImpl<PlatformConfigMapper, PlatformConfig> implements IPlatformConfigService {

    private final String cacheName = "platform:cache:config";

    @Autowired
    private PlatformConfigRepository configDao;

    @Cacheable(cacheNames = cacheName, key = "#key")
    @Override
    public String getByConfigKey(String key) {
        Optional<PlatformConfig> config = configDao.findByConfigKey(key);
        return config.map(PlatformConfig::getConfigValue).orElseThrow(() -> new JeecgBootException("参数配置" + key + "不存在，请检查platform_config"));
    }

    @CacheEvict(cacheNames = "cacheName", key = "#p0.configKey")
    @Override
    public boolean updateById(PlatformConfig entity) {
        return super.updateById(entity);
    }

}

package org.jeecg.modules.eth_hub.dao;

import org.jeecg.modules.demo.eth_hub.entity.PlatformConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlatformConfigRepository extends CrudRepository<PlatformConfig, String> {

    Optional<PlatformConfig> findByConfigKey(String configKey);
}

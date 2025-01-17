package org.jeecg.modules.demo.eth_hub.dao;

import org.jeecg.modules.demo.eth_hub.entity.EtherPayout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EtherPayoutRepository extends CrudRepository<EtherPayout, String> {

    EtherPayout findByPayoutNo(String payoutNo);

    Optional<List<EtherPayout>> findAllBySettleStatusAndMinerId(Integer settleStatus, String minerId);

    boolean existsBySettleStatus(Integer settleStatus);

}

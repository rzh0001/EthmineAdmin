package org.jeecg.modules.demo.eth_hub.dao;

import org.jeecg.modules.demo.eth_hub.entity.AppMemberBill;
import org.springframework.data.repository.CrudRepository;

public interface AppMemberBillRepository extends CrudRepository<AppMemberBill, String> {


    Iterable<AppMemberBill> findAllByMemberIdOrderByCreateTimeDesc(String memberId);
}

package com.bytd.crm.roadinfo.mapper;

import com.bytd.crm.roadinfo.entities.DCustomerSubs;

import java.math.BigInteger;
import java.util.List;

public interface DCustomerSubsMapper {
    /**
     * 获取订阅用户列表
     */
    List<DCustomerSubs> queryCustomerSubs() throws Exception;

    /**
     *
     * @param userId
     * @return 根据用户Id查询其卡列表
     * @throws Exception
     */
    List<String> queryCards(String userId) throws Exception;

    List<DCustomerSubs> queryCustomerSubsById(List<BigInteger> ids) throws Exception;
}

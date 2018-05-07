package com.example.demo.dao.clusterone;

import com.example.demo.entity.clusterone.Customer;

/**
 * @Author: hejis
 * @Description:
 * @Date: Create in 20:05 2018/5/3
 * @Modified By:
 */
public interface CustomerMapper {

    Customer selectByPrimaryKey(Integer id);

    int insertSelective(Customer customer);

}

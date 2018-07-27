package com.example.demo.service.clusterOne;

import com.example.demo.dao.clusterone.CustomerMapper;
import com.example.demo.entity.clusterone.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hejis
 * @Description:
 * @Date: Create in 19:10 2018/5/3
 * @Modified By:
 */
@Service
public class CustomerTestService {

    @Autowired
    private CustomerMapper customerMapper;

    public Customer getM(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }


    public int instet() {
        Customer customer = new Customer();
        customer.setAge(36);
        customer.setName("customer111");
        customer.setSex("男");
        return customerMapper.insertSelective(customer);
    }

}

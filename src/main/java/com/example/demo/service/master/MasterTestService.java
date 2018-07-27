package com.example.demo.service.master;

import com.example.demo.dao.master.TestMapper;
import com.example.demo.entity.master.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hejis
 * @Description:
 * @Date: Create in 19:09 2018/5/3
 * @Modified By:
 */
@Service
public class MasterTestService {

    @Autowired
    private TestMapper testMapper;


    public Test getM(Integer id) {
        return testMapper.selectByPrimaryKey(id);
    }

    public int instet() {
        Test test = new Test();
        test.setAge(35);
        test.setName("test王五");
        test.setSex("男");
        return testMapper.insertSelective(test);
    }


}

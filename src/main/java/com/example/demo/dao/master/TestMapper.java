package com.example.demo.dao.master;

import com.example.demo.entity.master.Test;

/**
 * @Author: hejis
 * @Description:
 * @Date: Create in 20:05 2018/5/3
 * @Modified By:
 */
public interface TestMapper {

    Test selectByPrimaryKey(Integer id);

    int insertSelective(Test test);

}

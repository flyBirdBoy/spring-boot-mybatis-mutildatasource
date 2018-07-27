package com.example.demo.controller;

import com.example.demo.service.clusterOne.CustomerTestService;
import com.example.demo.service.master.MasterTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hejis
 * @Description:
 * @Date: Create in 19:07 2018/5/3
 * @Modified By:
 */
@RestController
public class TestApi {

    @Autowired
    private CustomerTestService customerTestService;

    @Autowired
    private MasterTestService masterTestService;


    /**
     * 测试主库查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/master/select")
    public Object getaster(@RequestParam Integer id) {
        System.out.println("id = [" + id + "]");
        return masterTestService.getM(id);
    }

    /**
     * 测试主库插入
     *
     * @return
     */
    @RequestMapping("/master/insert")
    public Object insertMaster() {
        return masterTestService.instet();
    }

    /**
     * 测试主库事务
     *
     * @return
     */
    @RequestMapping("/master/ex")
    @Transactional
    public Object exMaster() {
        int m = masterTestService.instet();
        int s = 1000 / 0;
        return m;
    }


    /**
     * 测试从库查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/custom/select")
    public Object getcustom(@RequestParam Integer id) {
        return customerTestService.getM(id);
    }

    /**
     * 测试从库插入
     *
     * @return
     */
    @RequestMapping("/custom/insert")
    public Object insertCustom() {
        return customerTestService.instet();
    }

    /**
     * 测试从库事务   从库这里需要注意的是，在@Transactional注解上需要显示的声明是哪个事务管理
     *
     * @return
     */
    @RequestMapping("/custom/ex")
    @Transactional("customTransactionManager")
    public Object exCustom() {
        int m = customerTestService.instet();
        int s = 1000 / 0;
        return m;
    }

}

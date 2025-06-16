package com.microservicearchitecture.notificationservice;

import com.microservicearchitecture.notificationservice.dao.OrderDao;
import com.microservicearchitecture.notificationservice.dao.OrderDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
@RequestMapping("api/nott")
public class TestController {

    @Autowired
    private OrderDaoImpl orderDao;

    @GetMapping("/test")
    public String test(){
        System.out.println("Test container");
        System.out.println("Test select");
        orderDao.readAll();
        return "testOK";
    }
}

package com.example.demo.controller;

import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(method = GET)
    public String getDemo() {
        return demoService.getDemo();
    }

}

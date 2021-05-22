package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "-------testA";
    }

    @GetMapping("/testB")
    public String testB(){

        log.info("*************testB测试异常比例");
        int i = 1/0;
        return "--------testB";
    }

    @GetMapping("/testHotKeyabcA")
    @SentinelResource(value = "testHotKeyabcA",blockHandler = "del_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {

        return "----testHotKey";
    }
    @GetMapping("/testHotKeyabcB")
    @SentinelResource(value = "testHotKeyabcA",blockHandler = "del_testHotKey")
    public String testHotKeyB(@RequestParam(value = "p2", required = false) String p1,
                             @RequestParam(value = "p1", required = false) String p2) {

        return "----testHotKey";
    }
    public String del_testHotKey(String p1, String p2, BlockException e) {
        return "这次不用默认的兜底提示Blocked by Sentinel(flow limiting)，自定义提示：del_testHotKeyo(╥﹏╥)o...";
    }
}

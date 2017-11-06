package com.dev.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.logging.CommonLoggerFactory;

@RestController
public class LogController {

    @RequestMapping("/log/sendMsg/{msg}")
    public String sendLog(@PathVariable("msg") String msg) {
        CommonLoggerFactory.getLogger().info(msg);
        return msg;
    }
}

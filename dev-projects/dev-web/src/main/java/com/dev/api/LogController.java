package com.dev.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.service.LogService;
import com.test.logging.CommonLoggerFactory;

@RestController
public class LogController {
    private LogService logService;

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @RequestMapping("/log/sendMsg/{msg}")
    public String sendLog(@PathVariable("msg") String msg) {
        CommonLoggerFactory.getLogger().info("sendLog start.");
        String result;
        try {
            logService.doLogic(msg);
            result = "send log success.";
        } catch (Exception e) {
            CommonLoggerFactory.getLogger().error(e.getMessage(), e);
            result = "send log failed. " + e.getMessage();
        }
        CommonLoggerFactory.getLogger().info("sendLog end.");
        return result;
    }
}

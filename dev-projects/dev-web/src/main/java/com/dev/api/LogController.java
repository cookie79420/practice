package com.dev.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.logging.CommonLogger;
import com.dev.service.LogService;

@RestController
public class LogController {
    private LogService logService;

    @RequestMapping("/log/sendMsg/{msg}")
    public String sendLog(@PathVariable("msg") String msg) {
        CommonLogger.initLogger("LogController.sendLog");
        CommonLogger.getLogger().info("sendLog start.");
        String result;
        try {
            logService.doSaveLog(msg);
            result = "send log success.";
        } catch (Exception e) {
            CommonLogger.getLogger().error(e.getMessage(), e);
            result = "send log failed. " + e.getMessage();
        }
        CommonLogger.getLogger().info("sendLog end.");
        return result;
    }

    @RequestMapping("/log/show")
    public String show() {
        CommonLogger.initLogger("LogController.show");
        CommonLogger.getLogger().info("show start.");
        String result;
        try {
            result = logService.getLog();
        } catch (Exception e) {
            CommonLogger.getLogger().error(e.getMessage(), e);
            result = "show log failed. " + e.getMessage();
        }
        CommonLogger.getLogger().info("show end.");
        return result;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}

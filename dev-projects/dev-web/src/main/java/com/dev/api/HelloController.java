package com.dev.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.logging.CommonLogger;
import com.dev.service.AccountService;

@RestController
public class HelloController {

    private AccountService accountService;

    @RequestMapping("/hello/{account}")
    public String hello(@PathVariable("account") String account) {
        CommonLogger.initLogger("hello", HelloController.class);
        CommonLogger.getLogger().info("hello start.");
        String result;
        try {
            String name = accountService.getName(account);
            result = "hello " + name;
        } catch (Exception e) {
            CommonLogger.getLogger().error(e.getMessage(), e);
            result = "hello failed. " + e.getMessage();
        }
        CommonLogger.getLogger().info("hello end.");
        return result;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

}

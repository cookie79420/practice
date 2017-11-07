package com.dev.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.logging.CommonLoggerFactory;
import com.dev.service.AccountService;

@RestController
public class HelloController {

    private AccountService accountService;

    @RequestMapping("/hello/{account}")
    public String hello(@PathVariable("account") String account) {
        CommonLoggerFactory.initLogger(HelloController.class);
        CommonLoggerFactory.getLogger().info("hello start.");
        String result;
        try {
            String name = accountService.getName(account);
            result = "hello " + name;
        } catch (Exception e) {
            CommonLoggerFactory.getLogger().error(e.getMessage(), e);
            result = "hello failed. " + e.getMessage();
        }
        CommonLoggerFactory.getLogger().info("hello end.");
        return result;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

}

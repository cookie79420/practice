package com.dev.service.impl;

import java.util.Map;
import com.dev.dao.AccountDao;
import com.dev.logging.CommonLoggerFactory;
import com.dev.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Override
    public String getName(String account) {
        CommonLoggerFactory.getLogger().info("getName start.");
        Map<String, String> nameInfo = accountDao.getNameInfoByAccount(account);
        String result = nameInfo.get("F_NAME") + " " + nameInfo.get("L_NAME");
        CommonLoggerFactory.getLogger().info("getName end.");
        return result;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}

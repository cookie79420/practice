package com.dev.service.impl;

import com.dev.service.LogService;
import com.test.logging.CommonLoggerFactory;

public class LogServiceImpl implements LogService {

    @Override
    public void doLogic(String msg) {
        CommonLoggerFactory.getLogger().info("doLogic.");
        CommonLoggerFactory.getLogger().info("log msg : " + msg);
    }
}

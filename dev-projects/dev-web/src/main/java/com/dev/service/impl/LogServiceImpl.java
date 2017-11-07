package com.dev.service.impl;

import com.dev.dao.LogDao;
import com.dev.logging.CommonLoggerFactory;
import com.dev.service.LogService;

public class LogServiceImpl implements LogService {

    private LogDao logDao;

    @Override
    public void doSaveLog(String msg) {
        CommonLoggerFactory.getLogger().info("doLogic start.");
        logDao.save(msg);
        CommonLoggerFactory.getLogger().info("doLogic end.");
    }

    @Override
    public String getLog() {
        StringBuffer sb = new StringBuffer();
        CommonLoggerFactory.getLogger().info("getLog start.");
        for (String log : logDao.getLogList()) {
            sb.append(log);
            sb.append("; ");
        }
        CommonLoggerFactory.getLogger().info("getLog end.");
        return sb.toString();
    }

    public void setLogDao(LogDao logDao) {
        this.logDao = logDao;
    }
}

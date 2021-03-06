package com.dev.dao.impl;

import java.util.ArrayList;
import java.util.List;
import com.dev.dao.LogDao;
import com.dev.logging.CommonLogger;

public class LogDaoImpl implements LogDao {

    private static final List<String> logList = new ArrayList<String>();

    @Override
    public void save(String msg) {
        CommonLogger.getLogger().info("save log msg : " + msg);
        CommonLogger.getCallerLogger().info("save log msg : " + msg);
        logList.add(msg);
    }

    @Override
    public List<String> getLogList() {
        CommonLogger.getLogger().info("get logs.");
        return logList;
    }
}

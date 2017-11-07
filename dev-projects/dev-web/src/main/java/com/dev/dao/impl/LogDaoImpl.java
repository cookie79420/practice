package com.dev.dao.impl;

import java.util.ArrayList;
import java.util.List;
import com.dev.dao.LogDao;
import com.dev.logging.CommonLoggerFactory;

public class LogDaoImpl implements LogDao {

    private static final List<String> logList = new ArrayList<String>();

    @Override
    public void save(String msg) {
        CommonLoggerFactory.getLogger().info("save log msg : " + msg);
        logList.add(msg);
    }

    @Override
    public List<String> getLogList() {
        CommonLoggerFactory.getLogger().info("get logs.");
        return logList;
    }
}

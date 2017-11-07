package com.dev.dao;

import java.util.List;

public interface LogDao {

    public void save(String msg);

    public List<String> getLogList();
}

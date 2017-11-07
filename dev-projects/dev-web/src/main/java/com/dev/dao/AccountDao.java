package com.dev.dao;

import java.util.Map;

public interface AccountDao {
    public Map<String, String> getNameInfoByAccount(String account);
}

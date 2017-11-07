package com.dev.dao.impl;

import java.util.HashMap;
import java.util.Map;
import com.dev.dao.AccountDao;
import com.dev.logging.CommonLoggerFactory;

public class AccountDaoImpl implements AccountDao {

    @Override
    public Map<String, String> getNameInfoByAccount(String account) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("F_NAME", "Cookie");
        map.put("L_NAME", "Chen");
        CommonLoggerFactory.getLogger()
                .info(String.format("account:%s, first name:%s, last name:%s", account,
                        map.get("F_NAME"), map.get("L_NAME")));
        return map;
    }
}

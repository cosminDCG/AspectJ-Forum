package com.company.aspects;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import com.company.utils.DbUtil;

public aspect DatabaseAspect {
    Logger LOG = Logger.getLogger("com.company");

    pointcut getDbConnection():
                call(* DbUtil.getConnection());

    before(): getDbConnection() {
        LOG.log(Level.INFO, "Connection to database attempted");
    }

    after() returning(Connection connection): getDbConnection() {
        LOG.log(Level.INFO, "Connection to database succeeded");
    }

    after() throwing(RuntimeException e): getDbConnection() {
        LOG.log(Level.SEVERE, e.getMessage());
    }
}

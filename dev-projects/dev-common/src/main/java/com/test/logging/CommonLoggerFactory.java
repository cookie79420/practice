package com.test.logging;

import java.lang.invoke.MethodHandles;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class CommonLoggerFactory {
    private static final Logger logger = LoggerFactory.getLogger(CommonLoggerFactory.class);
    private static ThreadLocal<Logger> threadLocalLogger = new ThreadLocal<Logger>();

    public static void main(String[] args) {
        logger.info(MethodHandles.lookup().lookupClass().toString());
    }

    public static Logger getLogger() {
        if (threadLocalLogger.get() == null) {
            initLogger();
        }
        return threadLocalLogger.get();
    }

    private synchronized static void initLogger() {
        if (threadLocalLogger.get() == null) {
            MDC.put("UUID", UUID.randomUUID().toString());
            logger.info("init logger.");
            threadLocalLogger.set(LoggerFactory
                    .getLogger(Thread.currentThread().getStackTrace()[3].getClassName()));
        }
    }
}

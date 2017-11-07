package com.dev.logging;

import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dev.util.TextUtils;

public class CommonLoggerFactory {
    private static final Logger logger = LoggerFactory.getLogger(CommonLoggerFactory.class);

    private static ThreadLocal<Logger> threadLocalLogger = new ThreadLocal<Logger>();
    private static Map<String, String> commonLogMapper;

    public static void initLogger(String command) {
        initLogger(null, command);
    }

    public static void initLogger(String traceId, String command) {
        if (TextUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        MappedDiagnosticContext.setTraceId(traceId);

        MappedDiagnosticContext.setCommand(command);

        initThreadLocalLogger(command);
    }

    private static void initThreadLocalLogger(String command) {
        if (commonLogMapper != null && TextUtils.isNotBlank(command)) {
            String className = commonLogMapper.get(command);
            if (TextUtils.isBlank(className)) {
                command = command.substring(0, command.indexOf("."));
                className = commonLogMapper.get(command);
                if (TextUtils.isBlank(className)) {
                    logger.warn(String.format("%s is not included commonLogMapper.", command));
                } else {
                    threadLocalLogger.set(LoggerFactory.getLogger(className));
                }
            } else {
                threadLocalLogger.set(LoggerFactory.getLogger(className));
            }
        } else {
            logger.warn("command and commonLogMapper cannot be null.");
        }
    }

    public static void initLogger(Class<?> clazz) {
        initLogger(null, clazz);
    }

    public static void initLogger(String traceId, Class<?> clazz) {
        if (TextUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        MappedDiagnosticContext.setTraceId(traceId);

        MappedDiagnosticContext.setCommand(getCallerCommand());

        initThreadLocalLogger(clazz);
    }

    private static String getCallerCommand() {
        StringBuffer result = new StringBuffer();
        String className = Thread.currentThread().getStackTrace()[4].getClassName();
        result.append(className.substring(className.lastIndexOf(".") + 1));
        result.append(".");
        result.append(Thread.currentThread().getStackTrace()[4].getMethodName());
        return result.toString();
    }

    private static void initThreadLocalLogger(Class<?> clazz) {
        if (clazz != null) {
            threadLocalLogger.set(LoggerFactory.getLogger(clazz));
        } else {
            logger.warn("clazz cannot be null.");
        }
    }

    public static Logger getLogger() {
        String traceId = MappedDiagnosticContext.getTraceId();
        if (TextUtils.isBlank(traceId)) {
            MappedDiagnosticContext.setTraceId(UUID.randomUUID().toString());
        }

        Logger result = null;
        if (threadLocalLogger.get() == null) {
            result = getCallerLogger();
        } else {
            result = threadLocalLogger.get();
        }
        return result;
    }

    public static Logger getCallerLogger() {
        String traceId = MappedDiagnosticContext.getTraceId();
        if (TextUtils.isBlank(traceId)) {
            MappedDiagnosticContext.setTraceId(UUID.randomUUID().toString());
        }
        return LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static void setCommonLogMapper(Map<String, String> commonLogMapper) {
        CommonLoggerFactory.commonLogMapper = commonLogMapper;
    }
}

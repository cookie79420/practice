package com.dev.logging;

import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dev.util.TextUtils;

public class CommonLogger {
    private static final Logger logger = LoggerFactory.getLogger(CommonLogger.class);

    private static ThreadLocal<Logger> threadLocalLogger = new ThreadLocal<Logger>();
    private static Map<String, String> logMapper;

    public static void initLogger(String command) {
        String traceId = UUID.randomUUID().toString();
        initLogger(traceId, command);
    }

    public static void initLogger(String traceId, String command) {
        MappedDiagnosticContext.setTraceId(traceId);
        MappedDiagnosticContext.setCommand(command);
        initThreadLocalLogger(command);
    }

    private static void initThreadLocalLogger(String command) {
        String className = getClassName(command);
        threadLocalLogger.set(LoggerFactory.getLogger(className));
    }

    private static String getClassName(String command) {
        String className = "";
        if (logMapper != null && TextUtils.isNotBlank(command)) {
            className = logMapper.get(command);
            if (TextUtils.isBlank(className)) {
                String[] commands = command.split("\\.");
                className = logMapper.get(commands[0]);
                if (TextUtils.isBlank(className)) {
                    logger.warn(String.format("%s is not included commonLogMapper.", command));
                }
            }
        } else {
            logger.warn("command and commonLogMapper cannot be null.");
        }
        return className;
    }

    public static void initLogger(String command, Class<?> clazz) {
        String traceId = UUID.randomUUID().toString();
        initLogger(traceId, command, clazz);
    }

    public static void initLogger(String traceId, String command, Class<?> clazz) {
        MappedDiagnosticContext.setTraceId(traceId);
        MappedDiagnosticContext.setCommand(command);
        initThreadLocalLogger(clazz);
    }

    private static void initThreadLocalLogger(Class<?> clazz) {
        if (clazz != null) {
            threadLocalLogger.set(LoggerFactory.getLogger(clazz));
        } else {
            logger.warn("clazz cannot be null.");
        }
    }

    public static Logger getLogger() {
        Logger result = threadLocalLogger.get();
        if (result == null) {
            result = getCallerLogger();
        }
        return result;
    }

    public static Logger getCallerLogger() {
        String traceId = MappedDiagnosticContext.getTraceId();
        if (TextUtils.isBlank(traceId)) {
            MappedDiagnosticContext.setTraceId(UUID.randomUUID().toString());
        }
        return LoggerFactory.getLogger(getCallerClassName());
    }

    private static String getCallerClassName() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String callerClassName = null;
        for (int i = 1; i < elements.length; i++) {
            String className = elements[i].getClassName();
            if (className.equals(CommonLogger.class.getName()) == false) {
                callerClassName = className;
                break;
            }
        }
        return callerClassName;
    }

    public static void setLogMapper(Map<String, String> logMapper) {
        CommonLogger.logMapper = logMapper;
    }
}

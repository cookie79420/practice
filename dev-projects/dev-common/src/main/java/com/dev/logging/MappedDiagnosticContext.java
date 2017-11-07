package com.dev.logging;

import java.util.Map;
import org.slf4j.MDC;
import com.dev.util.TextUtils;

public class MappedDiagnosticContext {

    public static final String TRACE_ID_KEY = "traceId";
    public static final String COMMAND_KEY = "command";

    public static void put(String key, String val) {
        if (TextUtils.isNotBlank(key) && TextUtils.isNotBlank(val)) {
            MDC.put(key, val);
        }
    }

    public static String get(String key) {
        return TextUtils.isNotBlank(key) ? MDC.get(key) : null;
    }

    public static void remove(String key) throws IllegalArgumentException {
        if (TextUtils.isNotBlank(key)) {
            MDC.remove(key);
        }
    }

    public static void clear() {
        MDC.clear();
    }

    public static Map<String, String> getCopyOfContextMap() {
        return MDC.getCopyOfContextMap();
    }

    public static void setTraceId(String traceId) {
        put(TRACE_ID_KEY, traceId);
    }

    public static String getTraceId() {
        return get(TRACE_ID_KEY);
    }

    public static void setCommand(String command) {
        put(COMMAND_KEY, command);
    }

    public static String getCommand() {
        return get(COMMAND_KEY);
    }
}

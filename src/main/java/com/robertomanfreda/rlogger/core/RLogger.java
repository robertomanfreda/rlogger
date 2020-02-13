package com.robertomanfreda.rlogger.core;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RLogger implements Logger {
    private static final Logger logger = LoggerFactory.getLogger(new Exception().getStackTrace()[1].getClassName());

    private final LoggingQueue loggingQueue = LoggingQueue.getInstance();

    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String s) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.trace("[" + current + "] - " + s));
    }

    @Override
    public void trace(String s, Object o) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.trace("[" + current + "] - " + s, o));
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.trace("[" + current + "] - " + s, o, o1));
    }

    @Override
    public void trace(String s, Object... objects) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.trace("[" + current + "] - " + s, objects));
    }

    @Override
    public void trace(String s, Throwable throwable) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.trace("[" + current + "] - " + s, throwable));
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(Marker marker, String s) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.trace(marker, "[" + current + "] - " + s));
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.trace(marker, "[" + current + "] - " + s, o));
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.trace(marker, "[" + current + "] - " + s, o, o1));
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.trace(marker, "[" + current + "] - " + s, objects));
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.trace(marker, "[" + current + "] - " + s, throwable));
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String s) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.debug("[" + current + "] - " + s));
    }

    @Override
    public void debug(String s, Object o) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.debug("[" + current + "] - " + s, o));
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.debug("[" + current + "] - " + s, o, o1));
    }

    @Override
    public void debug(String s, Object... objects) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.debug("[" + current + "] - " + s, objects));
    }

    @Override
    public void debug(String s, Throwable throwable) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.debug("[" + current + "] - " + s, throwable));
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String s) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.debug(marker, "[" + current + "] - " + s));
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.debug(marker, "[" + current + "] - " + s, o));
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.debug(marker, "[" + current + "] - " + s, o, o1));
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.debug(marker, "[" + current + "] - " + s, objects));
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.debug(marker, "[" + current + "] - " + s, throwable));
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String s) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.info("[" + current + "] - " + s));
    }

    @Override
    public void info(String s, Object o) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.info("[" + current + "] - " + s, o));
    }

    @Override
    public void info(String s, Object o, Object o1) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.info("[" + current + "] - " + s, o, o1));
    }

    @Override
    public void info(String s, Object... objects) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.info("[" + current + "] - " + s, objects));
    }

    @Override
    public void info(String s, Throwable throwable) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.info("[" + current + "] - " + s, throwable));
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String s) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.info(marker, "[" + current + "] - " + s));
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.info(marker, "[" + current + "] - " + s, o));
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.info(marker, "[" + current + "] - " + s, o, o1));
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.info(marker, "[" + current + "] - " + s, objects));
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.info(marker, "[" + current + "] - " + s, throwable));
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String s) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.warn("[" + current + "] - " + s));
    }

    @Override
    public void warn(String s, Object o) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.warn("[" + current + "] - " + s, o));
    }

    @Override
    public void warn(String s, Object... objects) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.warn("[" + current + "] - " + s, objects));
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.warn("[" + current + "] - " + s, o, o1));
    }

    @Override
    public void warn(String s, Throwable throwable) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.warn("[" + current + "] - " + s, throwable));
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String s) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.warn(marker, "[" + current + "] - " + s));
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.warn(marker, "[" + current + "] - " + s, o));
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.warn(marker, "[" + current + "] - " + s, o, o1));
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.warn(marker, "[" + current + "] - " + s, objects));
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.warn(marker, "[" + current + "] - " + s, throwable));
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String s) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.error("[" + current + "] - " + s));
    }

    @Override
    public void error(String s, Object o) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.error("[" + current + "] - " + s, o));
    }

    @Override
    public void error(String s, Object o, Object o1) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.error("[" + current + "] - " + s, o, o1));
    }

    @Override
    public void error(String s, Object... objects) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.error("[" + current + "] - " + s, objects));
    }

    @Override
    public void error(String s, Throwable throwable) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.error("[" + current + "] - " + s, throwable));
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String s) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.error(marker, "[" + current + "] - " + s));
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.error(marker, "[" + current + "] - " + s, o));
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.error(marker, "[" + current + "] - " + s, o, o1));
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.error(marker, "[" + current + "] - " + s, objects));
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        String current = Thread.currentThread().getName();
        loggingQueue.enqueue(() -> logger.error(marker, "[" + current + "] - " + s, throwable));
    }

}

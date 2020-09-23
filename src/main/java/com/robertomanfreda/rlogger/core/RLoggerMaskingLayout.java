package com.robertomanfreda.rlogger.core;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class RLoggerMaskingLayout extends PatternLayout {

    @Override
    public String doLayout(final ILoggingEvent event) {
        return super.doLayout(event);
    }

}

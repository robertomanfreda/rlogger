package com.robertomanfreda.rlogger;

import com.robertomanfreda.rlogger.core.RLogger;

public class Main {
    public final static RLogger log = RLogger.builder().build();

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            log.debug("foo: " + i);
            log.debug("bar : " + i);
        }
    }
}

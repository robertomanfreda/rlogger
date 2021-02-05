package com.robertomanfreda.rlogger.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ConfigLoader {
    private Config config;
    private List<Mask> masks;

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class Config {
        private Json json;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class Json {
        private Boolean enabled = false;
        private Integer indentFactor = 0;

    }
}
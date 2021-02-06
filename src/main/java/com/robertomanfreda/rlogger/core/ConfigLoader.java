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

    private MasksContainer masks;

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class MasksContainer {
        private List<Mask> json;
        private List<Mask> xml;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class Config {
        private Json json;
        private Xml xml;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class Json {
        private Boolean enabled = false;
        private Integer indentFactor = 0;
        private String placeholder = "***";
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class Xml {
        private Boolean enabled = false;
        private Integer indentFactor = 0;
        private String placeholder = "***";
    }
}
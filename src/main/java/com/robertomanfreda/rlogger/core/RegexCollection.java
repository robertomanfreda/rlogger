package com.robertomanfreda.rlogger.core;

public class RegexCollection {
    //This regex try to extract a complete JSON payload from a mixed-content string/log
    static final String JSON_EXTRACTION_REGEX = "^([^{]+)(\\{[\\s\\S]+})([^{]+)";

    //This regex try to extract a complete XML payload from a mixed-content string/log
    static final String XML_EXTRACTION_REGEX = "^([^<]+)(\\<[\\s\\S]+>)([^>]+)";
}

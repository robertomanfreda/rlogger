package com.robertomanfreda.rlogger.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class RLoggerTest {

    @BeforeEach
    void setUp() {
        // do_nothing
    }

    @AfterEach
    void tearDown() {
        // do_nothing
    }

    @Test
    void beautyJsonTest() {
        log.debug("{\n" +
                "  \"glossary\": {\n" +
                "    \"title\": \"example glossary\",\n" +
                "    \"GlossDiv\": {\n" +
                "      \"title\": \"S\",\n" +
                "      \"GlossList\": {\n" +
                "        \"GlossEntry\": {\n" +
                "          \"ID\": \"SGML\",\n" +
                "          \"SortAs\": \"SGML\",\n" +
                "          \"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
                "          \"Acronym\": \"SGML\",\n" +
                "          \"Abbrev\": \"ISO 8879:1986\",\n" +
                "          \"GlossDef\": {\n" +
                "            \"para\": \"A meta-markup language, used to create \\\"markup\\\" languages such as DocBook.\",\n" +
                "            \"GlossSeeAlso\": [\n" +
                "              \"GML\",\n" +
                "              \"XML\"\n" +
                "            ]\n" +
                "          },\n" +
                "          \"GlossSee\": \"markup\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}");
    }

    @Test
    void uglyJsonTest() {
        log.debug("{\"glossary\":{\"title\":\"exampleglossary\",\"GlossDiv\":{\"title\":\"S\",\"GlossList\":{\"GlossEntry\":{\"ID\":\"SGML\",\"SortAs\":\"SGML\",\"GlossTerm\":\"StandardGeneralizedMarkupLanguage\",\"Acronym\":\"SGML\",\"Abbrev\":\"ISO8879:1986\",\"GlossDef\":{\"para\":\"Ameta-markuplanguage,usedtocreatemarkuplanguagessuchasDocBook.\",\"GlossSeeAlso\":[\"GML\",\"XML\"]},\"GlossSee\":\"markup\"}}}}}");
    }
}
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
                "      \"title\": \"BuonaSeraaa\",\n" +
                "      \"GlossList\": {\n" +
                "        \"GlossEntry\": {\n" +
                "          \"ID\": \"SGML\",\n" +
                "          \"SortAs\": \"SGML\",\n" +
                "          \"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
                "          \"Acronym\": \"SGML\",\n" +
                "          \"Abbrev\": \"ISO 8879:1986\",\n" +
                "          \"GlossDef\": {\n" +
                "            \"para\": \"A meta-markup \\\"language\\\", used to create markup languages such as DocBook.\",\n" +
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
        log.debug("{\"breakfast_menu\": {\"food\": [{\"name\": \"Belgian Waffles\",\"price\": \"$5.95\",\"description\": \"Two of our famous Belgian Waffles with plenty of real maple syrup\",\"calories\": \"650\"},{\"name\": \"Strawberry Belgian Waffles\",\"price\": \"$7.95\",\"description\": \"Light Belgian waffles covered with strawberries and whipped cream\",\"calories\": \"900\"},{\"name\": \"Berry-Berry Belgian Waffles\",\"price\": \"$8.95\",\"description\": \"Light Belgian waffles covered with an assortment of fresh berries and whipped cream\",\"calories\": \"900\"},{\"name\": \"French Toast\",\"price\": \"$4.50\",\"description\": \"Thick slices made from our homemade sourdough bread\",\"calories\": \"600\"},{\"name\": \"Homestyle Breakfast\",\"price\": \"$6.95\",\"description\": \"Two eggs, bacon or sausage, toast, and our ever-popular hash browns\",\"calories\": \"950\"}]}}");
    }

    @Test
    void beautyXmlTest() {
        log.debug("<breakfast_menu>\n" +
                "    <food>\n" +
                "        <name>Belgian Waffles</name>\n" +
                "        <price>$5.95</price>\n" +
                "        <description>\n" +
                "Two of our famous Belgian Waffles with plenty of real maple syrup\n" +
                "</description>\n" +
                "        <calories>650</calories>\n" +
                "    </food>\n" +
                "    <food>\n" +
                "        <name>Strawberry Belgian Waffles</name>\n" +
                "        <price>$7.95</price>\n" +
                "        <description>\n" +
                "Light Belgian waffles covered with strawberries and whipped cream\n" +
                "</description>\n" +
                "        <calories>900</calories>\n" +
                "    </food>\n" +
                "    <food>\n" +
                "        <name>Berry-Berry Belgian Waffles</name>\n" +
                "        <price>$8.95</price>\n" +
                "        <description>\n" +
                "Light Belgian waffles covered with an assortment of fresh berries and whipped cream\n" +
                "</description>\n" +
                "        <calories>900</calories>\n" +
                "    </food>\n" +
                "    <food>\n" +
                "        <name>French Toast</name>\n" +
                "        <price>$4.50</price>\n" +
                "        <description>\n" +
                "Thick slices made from our homemade sourdough bread\n" +
                "</description>\n" +
                "        <calories>600</calories>\n" +
                "    </food>\n" +
                "    <food>\n" +
                "        <name>Homestyle Breakfast</name>\n" +
                "        <price>$6.95</price>\n" +
                "        <description>\n" +
                "Two eggs, bacon or sausage, toast, and our ever-popular hash browns\n" +
                "</description>\n" +
                "        <calories>950</calories>\n" +
                "    </food>\n" +
                "</breakfast_menu>");
    }
}
package com.robertomanfreda.rlogger.core;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import nu.xom.Builder;
import nu.xom.Serializer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RLoggerMaskingLayout extends PatternLayout {

    private static ConfigLoader loader;
    private static final Map<Syntax, Pattern> compiledPatterns = new HashMap<>();

    // static initialization block
    static {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();

            // TODO I'm just testing these, file loading will be properly handled in a second moment
            File yamlConfig = new File("src/test/resources/rlogger.yaml");

            // Loading configurations from yaml file
            loader = mapper.readValue(yamlConfig, ConfigLoader.class);

            // Pre-compiled patterns
            compiledPatterns.put(Syntax.JSON, Pattern.compile(RegexCollection.JSON_EXTRACTION_REGEX));
            compiledPatterns.put(Syntax.XML, Pattern.compile(RegexCollection.XML_EXTRACTION_REGEX));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String doLayout(final ILoggingEvent event) {
        String outMessage = super.doLayout(event);

        if (loader.getConfig().getJson().getEnabled()) {
            Pattern jsonPattern = compiledPatterns.get(Syntax.JSON);
            Matcher jsonMatcher = jsonPattern.matcher(outMessage);

            if (jsonMatcher.find()) {
                try {
                    long safeReplacement = System.currentTimeMillis();
                    String json = jsonMatcher.group(2);
                    json = json.replaceAll("\\$", String.valueOf(safeReplacement));
                    JSONObject jsonObject = new JSONObject(json);

                    loader.getMasks().getJson().forEach(mask ->
                            modJsonObj(jsonObject, mask.getTarget(), loader.getConfig().getJson().getPlaceholder())
                    );

                    // Using indexed groups
                    String newJson = jsonObject.toString(loader.getConfig().getJson().getIndentFactor());
                    String grouped = "$1\n" + newJson + "\n$3";
                    outMessage = jsonMatcher.replaceAll(grouped);
                    outMessage = outMessage.replaceAll(String.valueOf(safeReplacement), "\\$");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // --

        if (loader.getConfig().getXml().getEnabled()) {
            try {
                Pattern xmlPattern = compiledPatterns.get(Syntax.XML);
                Matcher xmlMatcher = xmlPattern.matcher(outMessage);

                // Why using jaxb or similar stuff when we can hacky convert objects?!
                if (xmlMatcher.find()) {
                    long safeReplacement = System.currentTimeMillis();
                    String xml = xmlMatcher.group(2);
                    xml = xml.replaceAll("\\$", String.valueOf(safeReplacement));
                    JSONObject jsonObject = XML.toJSONObject(xml);

                    loader.getMasks().getXml().forEach(mask ->
                            modJsonObj(jsonObject, mask.getTarget(), loader.getConfig().getXml().getPlaceholder())
                    );

                    String newXML = XML.toString(jsonObject);
                    newXML = beautifyXml(newXML, loader.getConfig().getXml().getIndentFactor());
                    String grouped = "$1\n" + newXML + "\n$3";
                    outMessage = xmlMatcher.replaceAll(grouped);
                    outMessage = outMessage.replaceAll(String.valueOf(safeReplacement), "\\$");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return outMessage;
    }

    // recursive
    private static void modJsonObj(JSONObject jsonObject, String targetKey, String replacementValue) {
        for (String key : jsonObject.keySet()) {
            if (key.equals(targetKey) && (jsonObject.get(key) instanceof String
                    || jsonObject.get(key) instanceof JSONArray
                    || jsonObject.get(key) instanceof Number
                    || null == jsonObject.get(key))) {

                jsonObject.put(key, replacementValue);
                return;
            } else if (jsonObject.get(key) instanceof JSONObject) {
                JSONObject modObj = (JSONObject) jsonObject.get(key);

                if (null != modObj) {
                    modJsonObj(modObj, targetKey, replacementValue);
                }
            } else if (jsonObject.get(key) instanceof JSONArray) {
                JSONArray modArray = (JSONArray) jsonObject.get(key);

                for (int i = 0; i < modArray.length(); i++) {
                    JSONObject nextObj = modArray.optJSONObject(i);
                    if (null != nextObj) modJsonObj(nextObj, targetKey, replacementValue);
                }
            }
        }
    }

    private static String beautifyXml(String xml, Integer indentFactor) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Serializer serializer = new Serializer(out);
        serializer.setIndent(indentFactor);
        serializer.write(new Builder().build(xml, ""));
        return out.toString(StandardCharsets.UTF_8);
    }
}

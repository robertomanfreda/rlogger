package com.robertomanfreda.rlogger.core;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RLoggerMaskingLayout extends PatternLayout {

    private static ConfigLoader loader;
    private static final List<Mask> masks = new ArrayList<>();
    private static final Map<Syntax, Pattern> compiledPatterns = new HashMap<>();

    // static initialization block
    static {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();

            // TODO I'm just testing these file loading will be correctly handled in a second moment
            File yamlConfig = new File("src/test/resources/rlogger.yaml");

            // Loading configurations from yaml file
            loader = mapper.readValue(yamlConfig, ConfigLoader.class);

            // Copying masks locally
            masks.addAll(loader.getMasks());

            // Pre-compiled patterns
            compiledPatterns.put(Syntax.JSON, Pattern.compile(RegexCollection.JSON_EXTRACTION_REGEX));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String doLayout(final ILoggingEvent event) {
        String outMessage = super.doLayout(event);

        Pattern jsonPattern = compiledPatterns.get(Syntax.JSON);
        Matcher jsonMatcher = jsonPattern.matcher(outMessage);

        if (jsonMatcher.find()) {
            try {
                String json = jsonMatcher.group(2);
                JSONObject jsonObject = new JSONObject(json);

                for (Mask mask : masks) {
                    modJsonObj(jsonObject, mask.getTarget(), "***");
                }

                // Using indexed groups
                String replacement = "$1\n" + jsonObject.toString(loader.getConfig().getIndentFactor()) + "$3";
                outMessage = jsonMatcher.replaceAll(replacement);
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }

        return outMessage;
    }

    // recursive
    private static void modJsonObj(JSONObject jsonObject, String jsonKey, String jsonValue) {
        for (String key : jsonObject.keySet()) {
            if (key.equals(jsonKey) && ((jsonObject.get(key) instanceof String)
                    || ((jsonObject.get(key) instanceof JSONArray
                    || (jsonObject.get(key) instanceof Number)
                    || jsonObject.get(key) == null)))) {

                jsonObject.put(key, jsonValue);
                return;
            } else if (jsonObject.get(key) instanceof JSONObject) {
                JSONObject modObj = (JSONObject) jsonObject.get(key);

                if (modObj != null) {
                    modJsonObj(modObj, jsonKey, jsonValue);
                }
            }
        }
    }
}

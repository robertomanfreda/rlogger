package com.robertomanfreda.rlogger.core;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.json.JSONArray;
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

    private final static List<Mask> masks = new ArrayList<>();
    private final static Map<Syntax, Pattern> compiledPatterns = new HashMap<>();

    // caught exception due to static initialization block
    static {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();

            // Loading Masks from yaml putting them in the MasksLoader
            // TODO I'm testing
            MasksLoader loader = mapper.readValue(new File("src/test/resources/rlogger.yaml"),
                    MasksLoader.class);

            // Copying masks locally
            masks.addAll(loader.getMasks());

            // Pre-compiled patterns
            compiledPatterns.put(Syntax.JSON, Pattern.compile("^([^{]+)(\\{[\\s\\S]+})([^{]+)"));
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
            String json = jsonMatcher.group(2);
            JSONObject obj = new JSONObject(json);

            for (Mask mask : masks) {
                modJsonObj(obj, mask.getTarget(), "***");
            }

            outMessage = jsonMatcher.replaceAll("$1\n" + obj.toString(2) + "$3");
        }

        return outMessage;
    }

    private static JSONObject modJsonObj(JSONObject jsonObject, String jsonKey, String jsonValue) {
        for (String key : jsonObject.keySet()) {
            if (key.equals(jsonKey)
                    && ((jsonObject.get(key) instanceof String)
                    || ((jsonObject.get(key) instanceof JSONArray
                    || (jsonObject.get(key) instanceof Number)
                    || jsonObject.get(key) == null)))) {

                jsonObject.put(key, jsonValue);
                return jsonObject;
            } else if (jsonObject.get(key) instanceof JSONObject) {
                JSONObject modObj = (JSONObject) jsonObject.get(key);

                if (modObj != null) {
                    modJsonObj(modObj, jsonKey, jsonValue);
                }
            }

        }

        return jsonObject;
    }
}

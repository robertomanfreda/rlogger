package com.robertomanfreda.rlogger.core;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RLoggerMaskingLayout extends PatternLayout {

    private final static List<Mask> masks = new ArrayList<>();
    private final static List<Pattern> compiledPatterns = new ArrayList<>();

    // caught exception due to static initialization block
    static {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();

            // Loading Masks from yaml putting them in the MasksLoader
            MasksLoader loader = mapper.readValue(new File("src/test/resources/rlogger.yaml"),
                    MasksLoader.class);

            // Copying masks locally
            masks.addAll(loader.getMasks());

            /*
             * Pre-compiled patterns
             */
            // "Beauty" JSON
            compiledPatterns.addAll(masks.stream()
                    .map(m -> Pattern.compile("(\"" + m.getTarget() + "\")( +)?(:)( +)?(\")?(.+)?(\")?(\")(,)?(\"$)?"))
                    .collect(Collectors.toList())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String doLayout(final ILoggingEvent event) {
        String outMessage = super.doLayout(event);

        for (Pattern pattern : compiledPatterns) {
            Matcher matcher = pattern.matcher(outMessage);
            outMessage = matcher.replaceAll("$1$2$3$4$5***$7$8$9");
        }

        return outMessage;
    }
}

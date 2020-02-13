package com.robertomanfreda.rlogger.markers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RLoggerMarkers {
    public static final Marker JSON = MarkerFactory.getMarker("JSON");
    public static final Marker TEXT = MarkerFactory.getMarker("TEXT");
    public static final Marker URL = MarkerFactory.getMarker("URL");
    public static final Marker XML = MarkerFactory.getMarker("XML");
}

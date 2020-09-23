package com.robertomanfreda.rlogger.core;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RLoggerMaskingLayout extends PatternLayout {

    private boolean enabled;
    private List<Mask> masks = new ArrayList<>();

    @Override
    public String doLayout(final ILoggingEvent event) {
        String outMessage = super.doLayout(event);

        //Pattern p = Pattern.compile(strMasks.get(0));
        //Matcher matcher = p.matcher(outMessage);
        //matcher.replaceAll("$1***");

        return outMessage;
    }

    @SneakyThrows
    public void setEnabled(boolean enabled) {
        if (enabled) {
            File configurationFile = ConfigurationWatchListUtil.getConfigurationWatchList(getContext())
                    .getCopyOfFileWatchList().get(0);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(configurationFile);
            NodeList nodeList = document.getElementsByTagName("masks");

            this.masks = IntStream.range(0, nodeList.getLength())
                    .mapToObj(nodeList::item)
                    .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                    .map(node -> (Element) node)
                    .map(element -> Mask.builder()
                            .id(element.getElementsByTagName("id").item(0).getTextContent())
                            .regex(element.getElementsByTagName("regex").item(0).getTextContent())
                            .replacement(element.getElementsByTagName("replacement").item(0).getTextContent())
                            .sensitive(element.getElementsByTagName("sensitive").item(0).getTextContent().equals("true"))
                            .build()).collect(Collectors.toList());
        }
    }
}

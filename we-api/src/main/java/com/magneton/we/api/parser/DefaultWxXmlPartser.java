package com.magneton.we.api.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangmingshuang
 * @since 2019/9/4
 */
public class DefaultWxXmlPartser extends WxXmlParser {

    private DocumentBuilderFactory documentBuilderFactory;

    public DefaultWxXmlPartser() {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        documentBuilderFactory.setXIncludeAware(false);
        documentBuilderFactory.setExpandEntityReferences(false);
    }

    @Override
    Map<String, String> doParse(String xml) throws Exception {
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document parse = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
        Element documentElement = parse.getDocumentElement();
        NodeList childNodes = documentElement.getChildNodes();
        int len = childNodes.getLength();
        Map<String, String> params = new HashMap<>(len, 1);
        for (int j = 0; j < len; j++) {
            Node item = childNodes.item(j);
            if (item.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            String key = item.getNodeName();
            String value = item.getTextContent();
            params.put(key, value);
        }
        return params;
    }
}

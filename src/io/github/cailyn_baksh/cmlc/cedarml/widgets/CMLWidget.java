package io.github.cailyn_baksh.cmlc.cedarml.widgets;

import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

public class CMLWidget {
    private CMLWidgetSchema schema;

    private Map<String, String> attrs = new HashMap<>();

    public CMLWidget(CMLWidgetSchema schema) {
        this.schema = schema;
    }
}

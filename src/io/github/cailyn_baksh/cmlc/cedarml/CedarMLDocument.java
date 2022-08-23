package io.github.cailyn_baksh.cmlc.cedarml;

import io.github.cailyn_baksh.cmlc.cedarml.widgets.WidgetSchema;

import java.util.HashMap;
import java.util.Map;

public class CedarMLDocument {
    private Map<String, WidgetSchema> widgets = new HashMap<>();

    public void addWidgetSchema(String tagname, WidgetSchema schema) {
        widgets.put(tagname, schema);
    }

    public void importDocument(CedarMLDocument other) {

    }
}

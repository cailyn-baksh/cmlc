package io.github.cailyn_baksh.cmlc.cedarml.widgets;

import io.github.cailyn_baksh.cmlc.cedarml.CedarMLType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Describes a custom widget
 */
public record WidgetSchema(
        CedarMLType contentType,
        Map<String, AttributeSchema> attrs,
        ConstructorSchema ctor
) {
    public record AttributeSchema(CedarMLType type, String defaultValue) {}

    public record ConstructorSchema(String name, List<String>params) {
        public ConstructorSchema() {
            this(null, new ArrayList<>());
        }
    }

    public WidgetSchema(CedarMLType type) {
        this(type, new HashMap<>(), new ConstructorSchema());
    }
}

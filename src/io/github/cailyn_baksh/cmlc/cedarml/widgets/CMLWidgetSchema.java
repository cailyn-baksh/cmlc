package io.github.cailyn_baksh.cmlc.cedarml.widgets;

import io.github.cailyn_baksh.cmlc.cedarml.CedarMLType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CMLWidgetSchema {
    public CedarMLType contentType = null;
    public Map<String, AttrSchema> attrs = new HashMap<>();
    public ConstructorSchema ctor = new ConstructorSchema();
    public List<IInitStep> init = new ArrayList<>();
}

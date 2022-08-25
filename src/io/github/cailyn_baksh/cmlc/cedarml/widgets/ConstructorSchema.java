package io.github.cailyn_baksh.cmlc.cedarml.widgets;

import io.github.cailyn_baksh.cmlc.utils.Template;

import java.util.ArrayList;
import java.util.List;

public class ConstructorSchema {
    public String name;
    public List<String> params = new ArrayList<>();

    public Template toTemplate() {
        return new Template(name + "(" + String.join(", ", params) + ")");
    }
}

package io.github.cailyn_baksh.cmlc.cedarml.widgets;

import io.github.cailyn_baksh.cmlc.utils.Template;

public class SetPropertySchema implements IInitStep {
    public String name;
    public String value;

    @Override
    public Template toTemplate() {
        return new Template("cedar_dispatchEvent(EVENT_SETPROPERTY, ");
    }
}

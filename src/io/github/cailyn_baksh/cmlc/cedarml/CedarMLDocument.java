package io.github.cailyn_baksh.cmlc.cedarml;

import io.github.cailyn_baksh.cmlc.cedarml.widgets.WidgetSchema;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CedarMLDocument {
    private Map<String, WidgetSchema> widgets = new HashMap<>();

    public void addWidgetSchema(String tagname, WidgetSchema schema) {
        widgets.put(tagname, schema);
    }

    public void importDocument(CedarMLDocument other) {
        widgets.putAll(other.widgets);
    }

    /**
     * Generate C code
     * @param baseFileName The base name for the .c and .h files to be generated.
     */
    public void generateC(String baseFileName) throws IOException {
        PrintWriter source = new PrintWriter(new FileWriter(baseFileName + ".c"));
        PrintWriter header = new PrintWriter(new FileWriter(baseFileName + ".h"));

        // Write beginning of header file
        header.printf("#ifndef __CEDARML_%s_H_\n", baseFileName.toUpperCase());
        header.printf("#define __CEDARML_%s_H_\n", baseFileName.toUpperCase());

        source.printf("#include \"%s\"", baseFileName + ".h");

        // Write end of header file
        header.printf("#endif\n");
    }
}

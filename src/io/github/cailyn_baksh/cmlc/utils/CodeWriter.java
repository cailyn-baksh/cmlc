package io.github.cailyn_baksh.cmlc.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Writes C code to a file
 */
public class CodeWriter {
    private String tab = "\t";

    private int indent = 0;

    private PrintWriter writer;

    public CodeWriter(String filepath) throws FileNotFoundException {
        writer = new PrintWriter(filepath);
    }

    /**
     * Indent using tabs
     */
    public void useTabs() {
        tab = "\t";
    }

    /**
     * Indent using spaces
     * @param tabWidth How many spaces in a tab
     */
    public void useSpaces(int tabWidth) {
        tab = " ".repeat(tabWidth);
    }

    /**
     * Get the current indentation level
     * @return How many levels deep the current indentation is
     */
    public int getIndent() {
        return indent;
    }

    /**
     * Increase the indentation by one level
     */
    public void indent() {
        indent++;
    }

    /**
     * Decrease the indentation by one level
     */
    public void outdent() {
        indent--;
    }

    public void ln(String fmt, Object... args) {
        String[] lines = fmt.split("\\r?\\n");

        for (String line : lines) {
            writer.printf(tab.repeat(indent) + line + "\n", args);
        }
    }

    public void ln() {
        writer.println();
    }

    public void flush() {
        writer.flush();
    }

    public void close() {
        writer.close();
    }
}

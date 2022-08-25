package io.github.cailyn_baksh.cmlc.utils;

import java.util.Map;

public class Template {
    private final String raw;

    public Template(String str) {
        this.raw = str;
    }

    public String toString(String content, Map<String, String> env) {
        StringBuilder sb = new StringBuilder();

        boolean substitution = false;
        StringBuilder substName = new StringBuilder();

        char lastCh = '\0';
        for (int i=0; i < raw.length(); ++i) {
            char ch = raw.charAt(i);

            if (substitution) {
                // build substitution
                if (ch == '}') {
                    // End of substitution; try and find property and reset substName
                    String property = substName.toString();

                    if (property.equals("content")) {
                        sb.append(content);
                    } else {
                        try {
                            sb.append(env.get(substName.toString()));
                        } catch (NullPointerException ex) {
                            // TODO: handle invalid keys
                        }
                    }

                    substName = new StringBuilder();
                    substitution = false;
                } else {
                    substName.append(ch);
                }
            } else if (lastCh == '\\') {
                // Escaped char, write it literally and prevent it from being
                // saved as the last char
                sb.append(ch);
                ch = '\0';
            } else if (lastCh == '$' && ch == '{') {
                // "${", beginning of a substitution
                substitution = true;
            }

            lastCh = ch;
        }

        return sb.toString();
    }
}

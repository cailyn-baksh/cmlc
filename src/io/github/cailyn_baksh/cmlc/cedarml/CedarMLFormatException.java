package io.github.cailyn_baksh.cmlc.cedarml;

import javax.xml.stream.Location;

public class CedarMLFormatException extends Exception {
    private String msg = "";

    public CedarMLFormatException() {}

    public CedarMLFormatException(Location location, String message) {
        msg = "%d:%d: %s".formatted(location.getLineNumber(), location.getColumnNumber(), message);
    }

    @Override
    public String getMessage() {
        return msg;
    }
}

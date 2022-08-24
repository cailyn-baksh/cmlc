package io.github.cailyn_baksh.cmlc.cedarml;

import io.github.cailyn_baksh.cmlc.cedarml.widgets.WidgetSchema;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CedarMLParser {
    private XMLStreamReader reader;

    private CedarMLDocument document = new CedarMLDocument();

    /**
     * Parses a CedarML file.
     */
    public CedarMLParser(String path) throws FileNotFoundException, XMLStreamException, CedarMLFormatException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        reader = factory.createXMLStreamReader(new FileInputStream(new File(path)));

        int depth = 0;
        while (reader.hasNext()) {
            int event = reader.getEventType();

            switch (event) {
                case XMLStreamConstants.START_DOCUMENT:
                    // Get next event
                    reader.next();
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    // decide which handler to pass control to
                    String tagName = reader.getLocalName();

                    if (depth == 0 && tagName.equals("cedarml")) {
                        // root tag
                        ++depth;
                    } else if (depth > 0) {
                        // child tag; pass off to another function
                        switch (tagName) {
                            case "import" -> handle_import();
                            case "widget" -> handle_widget();
                            default -> throw new CedarMLFormatException(reader.getLocation(), "Invalid tag <%s>".formatted(tagName));
                        }
                    } else {
                        // invalid
                        throw new CedarMLFormatException(reader.getLocation(), "Invalid tag <%s>".formatted(tagName));
                    }
            }
        }
    }

    /**
     * Parses the import tag. Called by the constructor
     */
    private void handle_import() throws XMLStreamException, CedarMLFormatException, FileNotFoundException {
        // Going into this function, the current reader event should be
        // START_ELEMENT. We can move right on to the next one since this event
        // has already been handled by the caller
        int event = reader.getEventType();

        if (event == XMLStreamConstants.START_ELEMENT) {
            String path = reader.getAttributeValue(null, "path");

            if (path != null) {
                CedarMLParser p = new CedarMLParser(path);

                document.importDocument(p.getDocument());
            } else {
                throw new CedarMLFormatException(reader.getLocation(), "<import> tag must have path attribute");
            }
        } else {
            throw new CedarMLFormatException(reader.getLocation(), "Invalid syntax");
        }

        reader.nextTag();  // this should get to the end of this tag. if it doesnt then the syntax error will be picked up later
    }

    /**
     * Parses the widget tag. Called by the constructor
     */
    private void handle_widget() throws CedarMLFormatException, XMLStreamException {
        String schemaName;
        WidgetSchema schema;
        int event = reader.getEventType();

        if (event != XMLStreamConstants.START_ELEMENT) {
            throw new CedarMLFormatException(reader.getLocation(), "Invalid syntax");
        }

        schemaName = reader.getAttributeValue(null, "name");
        String contentType = reader.getAttributeValue(null, "contentType");

        if (contentType == null) {
            contentType = "str";
        }

        schema = new WidgetSchema(CedarMLType.fromString(contentType));

        // Start parsing subelements

        while (reader.hasNext()) {
            event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                String tagName = reader.getLocalName();

                switch (tagName) {
                    case "attr":
                        String name = reader.getAttributeValue(null, "name");
                        String type = reader.getAttributeValue(null, "type");
                        String defaultValue = reader.getAttributeValue(null, "default");

                        if (name == null || type == null) {
                            throw new CedarMLFormatException(reader.getLocation(), "<attr> tag requires name and type attributes");
                        }

                        schema.attrs().put(name, new WidgetSchema.AttributeSchema(CedarMLType.fromString(type), defaultValue));
                        break;
                }
            }
        }

        document.addWidgetSchema(schemaName, schema);
    }

    public CedarMLDocument getDocument() {
        return document;
    }
}

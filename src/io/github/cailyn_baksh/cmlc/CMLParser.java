package io.github.cailyn_baksh.cmlc;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.thaiopensource.relaxng.jaxp.CompactSyntaxSchemaFactory;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.URL;

public class CMLParser {
    public class CMLParseException extends Exception {
        private String msg;

        public CMLParseException(String message) {
            msg = message;
        }

        @Override
        public String getMessage() {
            return msg;
        }
    }

    public static final URL CML_SCHEMA = CMLParser.class.getClassLoader().getResource("cedarml.rnc");

    private Document cmlDocument;

    private String outDir;
    private String baseFileName;

    public CMLParser(String srcFile, String outDir, String baseFileName) throws IOException, SAXException, ParserConfigurationException, CMLParseException {
        this.outDir = outDir;
        this.baseFileName = baseFileName;

        cmlDocument = loadXMLDocument(srcFile);
    }

    private Document loadXMLDocument(String file) throws SAXException, ParserConfigurationException, IOException, CMLParseException {
        // FIXME: find a better way to do this (maybe an alternative to Jing?)
        LineNumberReader inputReader;

        // Validate file against schema
        SchemaFactory schemaFactory = CompactSyntaxSchemaFactory.newInstance(XMLConstants.RELAXNG_NS_URI);
        Schema schema = schemaFactory.newSchema(CML_SCHEMA);
        Validator validator = schema.newValidator();

        inputReader = new LineNumberReader(new FileReader(file));
        Source src = new StreamSource(inputReader);

        try {
            validator.validate(src);
        } catch (SAXException e) {
            throw new CMLParseException("%s:%d: %s".formatted(file, inputReader.getLineNumber(), e.getMessage()));
        }

        inputReader.close();

        // Load Document
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        Document doc = builder.parse(file);

        inputReader.close();

        return doc;
    }
}

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
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class CMLParser {
    public static final URL CML_SCHEMA = CMLParser.class.getClassLoader().getResource("cedarml.rnc");

    private Document cmlDocument;

    private String outDir;
    private String baseFileName;

    public CMLParser(String srcFile, String outDir, String baseFileName) throws IOException, SAXException, ParserConfigurationException {
        this.outDir = outDir;
        this.baseFileName = baseFileName;

        cmlDocument = loadXMLDocument(srcFile);
    }

    private Document loadXMLDocument(String file) throws SAXException, ParserConfigurationException, IOException {
        // FIXME: find a better way to do this (maybe an alternative to Jing?)
        FileInputStream inputStream;

        // Validate file against schema
        SchemaFactory schemaFactory = CompactSyntaxSchemaFactory.newInstance(XMLConstants.RELAXNG_NS_URI);
        Schema schema = schemaFactory.newSchema(CML_SCHEMA);
        Validator validator = schema.newValidator();

        inputStream = new FileInputStream(file);
        Source src = new StreamSource(inputStream);
        validator.validate(src);

        inputStream.close();  // We have to close the stream and reopen it before we can load the Document

        // Load Document
        inputStream = new FileInputStream(file);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        Document doc = builder.parse(inputStream);

        inputStream.close();

        return doc;
    }
}

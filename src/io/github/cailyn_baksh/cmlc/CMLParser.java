package io.github.cailyn_baksh.cmlc;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
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
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.RELAXNG_NS_URI);

        Schema schema = schemaFactory.newSchema(CML_SCHEMA);

        dbFactory.setValidating(false);
        //dbFactory.setSchema(schema);

        DocumentBuilder builder = dbFactory.newDocumentBuilder();

        FileInputStream inputStream = new FileInputStream(file);

        Document doc = builder.parse(inputStream);

        inputStream.close();

        return doc;
    }
}

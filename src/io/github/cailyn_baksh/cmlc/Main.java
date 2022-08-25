package io.github.cailyn_baksh.cmlc;

import io.github.cailyn_baksh.cmlc.cedarml.CedarMLFormatException;
import io.github.cailyn_baksh.cmlc.cedarml.CedarMLParser;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;

public class Main {
    public static final String CML_VERSION = "1.0";

    @Option(name="-h", aliases={"--help"}, help=true, usage="Display this help message")
    private boolean showHelp;

    @Option(name="-o", aliases={"--output"}, usage="Place output in this folder.", metaVar="PATH")
    private String outDir = "./gui/";

    @Argument(required=true, usage="The source file to compile", metaVar="FILE")
    private String srcFile;

    private String baseFileName;

    public static void main(String[] args) {
        new Main().doMain(args);
    }

    /*
     * Actual main function
     */
    public void doMain(String[] args) {
        // Parse args
        CmdLineParser cmdLineParser = new CmdLineParser(this);

        try {
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException ex) {
            System.err.println(ex.getMessage());
            return;
        }

        if (showHelp) {
            System.out.println("Usage: cmlc FILE [OPTIONS...]");
            System.out.println("Compiles CedarML files into C source code.\n");

            cmdLineParser.printUsage(System.out);
            System.out.println();
        }

        // Create output directory
        if (outDir.charAt(outDir.length()-1) != '/') outDir += '/';
        new File(outDir).mkdirs();

        // Get base file name
        baseFileName = srcFile.substring(0, srcFile.lastIndexOf('.'));

        CMLParser cmlParser;
        try {
            cmlParser = new CMLParser(srcFile, outDir, baseFileName);
        } catch (IOException e) {
            System.err.println("Could not open file " + srcFile + " for reading");
            return;
        } catch (SAXException e) {
            System.err.println("Error parsing file " + srcFile + ":");
            System.err.println(e.getMessage());
            return;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return;
        }

        // Load XML
        Document cedarMLDocument;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = schemaFactory.newSchema(getClass().getClassLoader().getResource("cedarml.xsd"));

            dbFactory.setValidating(true);
            dbFactory.setSchema(schema);

            DocumentBuilder builder = dbFactory.newDocumentBuilder();

            FileInputStream inputStream = new FileInputStream(srcFile);

            cedarMLDocument = builder.parse(inputStream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            System.err.println("Could not read file " + srcFile);
            return;
        } catch (SAXException e) {
            System.err.println("Error parsing file " + srcFile);
            return;
        }

        /*
        // Parse CedarML file
        try {
            CedarMLParser cmlParser = new CedarMLParser(srcFile);
        } catch (FileNotFoundException e) {
            System.err.println("File " + srcFile + " does not exist");
            return;
        } catch (XMLStreamException e) {
            System.err.println("%d:%d: XML syntax error in file %s: %s"
                    .formatted(e.getLocation().getLineNumber(),
                            e.getLocation().getColumnNumber(),
                            srcFile, e.getMessage()));
        } catch (CedarMLFormatException e) {
            System.err.println("Syntax error while parsing " + srcFile);
            System.err.println(e.getMessage());
        }*/

        // Write C output
        try {
            generateC(cedarMLDocument);
        } catch (IOException e) {
            System.err.println("Could not open output file for writing");
            System.err.println(e.getMessage());
        }
    }

    public void generateC(Document document) throws IOException {
        // Create PrintWriters for output
        PrintWriter source = new PrintWriter(new FileWriter(outDir + baseFileName + ".c"));
        PrintWriter header = new PrintWriter(new FileWriter(outDir + baseFileName + ".h"));

        // Write beginning of include guards to header file
        header.println("#ifndef __CEDARML_" + baseFileName.toUpperCase() + "_H_");
        header.println("#define __CEDARML_" + baseFileName.toUpperCase() + "_H_");

        // Include header file in source file
        source.println("#include \"" + baseFileName + ".h\"");



        // Write end of include guards to header file
        header.println("#endif");
    }
}

package io.github.cailyn_baksh.cmlc;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
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
        // Config Jing RNC SchemaFactory
        // https://pages.lip6.fr/Jean-Francois.Perrot/XML-Int/Session3/RNG/#mozTocId175276
        System.setProperty(
                SchemaFactory.class.getName() + ":" + XMLConstants.RELAXNG_NS_URI,
                "com.thaiopensource.relaxng.jaxp.CompactSyntaxSchemaFactory"
        );

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
            return;
        }

        // Create output directory
        if (outDir.charAt(outDir.length() - 1) != '/') outDir += '/';
        new File(outDir).mkdirs();

        // Get base file name
        baseFileName = srcFile.substring(0, srcFile.lastIndexOf('.'));
        baseFileName = baseFileName.substring(baseFileName.lastIndexOf('/')+1);

        CMLParser cmlParser;
        try {
            cmlParser = new CMLParser(srcFile, outDir, baseFileName);
        } catch (IOException e) {
            // Error opening file
            System.err.println("Could not open file " + srcFile + " for reading");
            return;
        } catch (SAXException e) {
            // Error parsing file into DOM
            System.err.print("Error parsing file " + srcFile + ": ");
            System.err.println(e.getMessage());
            return;
        } catch (CMLParser.CMLParseException e) {
            // Error validating file against schema
            // Also XML syntax errors
            // most errors will occur here
            System.err.println(e.getMessage());
            return;
        } catch (ParserConfigurationException e) {
            // idk what causes this one
            e.printStackTrace();
            return;
        }

        try {
            cmlParser.generateC();
        } catch (IOException e) {
            System.err.println("Could not open output file");
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
}

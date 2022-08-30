package io.github.cailyn_baksh.cmlc;

import io.github.cailyn_baksh.cmlc.utils.IdentifierGenerator;
import org.dom4j.DocumentException;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.util.Date;
import java.util.logging.*;

public class Main {
    public static final String CMLC_VERSION = "0.1.0";

    private static Logger logger = Logger.getLogger("io.github.cailyn_baksh.cmlc");

    @Option(name="-h", aliases={"--help"}, help=true, usage="Display this help message")
    private boolean showHelp;

    @Option(name="-o", aliases={"--output"}, usage="Place output in this folder.", metaVar="PATH")
    private String outDir = "./gui/";

    @Option(name="-q", aliases={"--quiet"}, forbids={"-v", "--debug"}, usage="Suppress all output")
    private boolean quiet;

    @Option(name="-v", aliases={"--verbose"}, forbids={"--debug", "-q"}, usage="Display verbose output")
    private boolean verbose;

    @Option(name="--debug", forbids={"-v", "-q"}, hidden=true, usage="Display debugging information during compilation")
    private boolean debug;

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

        // Config logger format
        logger.setUseParentHandlers(false);

        ConsoleHandler loggerHandler = new ConsoleHandler();
        loggerHandler.setLevel(Level.ALL);
        loggerHandler.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tT.%1$tL] [%2$-7s] %3$s\n";

            @Override
            public synchronized String format(LogRecord lr) {
                return format.formatted(new Date(lr.getMillis()), lr.getLevel().getLocalizedName(), lr.getMessage());
            }
        });
        logger.addHandler(loggerHandler);

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

        // Set log level
        if (quiet) {
            logger.setLevel(Level.SEVERE);
        } else if (verbose) {
            logger.setLevel(Level.INFO);
        } else if (debug) {
            logger.setLevel(Level.FINEST);
        } else {
            logger.setLevel(Level.WARNING);
        }

        // Create output directory
        if (outDir.charAt(outDir.length() - 1) != '/') outDir += '/';
        new File(outDir).mkdirs();

        // Get base file name
        baseFileName = srcFile.substring(0, srcFile.lastIndexOf('.'));
        baseFileName = baseFileName.substring(baseFileName.lastIndexOf('/')+1);

        CMLCompiler compiler;
        try {
            compiler = new CMLCompiler(srcFile, outDir, baseFileName);
        } catch (IOException e) {
            // Error opening file
            logger.severe("Could not open file " + srcFile + " for reading");
            return;
        } catch (CMLCompiler.CMLParseException e) {
            // Error validating file against schema
            // Also XML syntax errors
            // most errors will occur here
            logger.severe(e.getMessage());
            return;
        } catch (DocumentException e) {
            // Error parsing file into DOM
            logger.severe("Error parsing file " + srcFile + ": ");
            logger.severe(e.getMessage());
            return;
        }

        try {
            compiler.generateC();
        } catch (IOException e) {
            logger.severe("Could not open output file");
            logger.severe(e.getMessage());
            return;
        } catch (CMLCompiler.CMLParseException e) {
            logger.severe(e.getMessage());
            return;
        }
    }
}

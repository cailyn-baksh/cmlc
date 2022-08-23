package io.github.cailyn_baksh.cmlc;

import io.github.cailyn_baksh.cmlc.cedarml.CedarMLFormatException;
import io.github.cailyn_baksh.cmlc.cedarml.CedarMLParser;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    @Option(name="-h", aliases={"--help"}, help=true, usage="Display this help message")
    private boolean showHelp;

    @Option(name="-o", aliases={"--output"}, usage="Place output in this folder.", metaVar="PATH")
    private String outFile = "./gui/";

    @Argument(required=true, usage="The source file to compile", metaVar="FILE")
    private String srcFile;


    public static void main(String[] args) {
        new Main().doMain(args);
    }

    /*
     * Actual main function
     */
    public void doMain(String[] args) {
        // Parse args
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException ex) {
            System.err.println(ex.getMessage());
            return;
        }

        if (showHelp) {
            System.out.println("Usage: cmlc FILE [OPTIONS...]");
            System.out.println("Compiles CedarML files into C source code.\n");

            parser.printUsage(System.out);
            System.out.println();
        }

        // Create output directory
        new File(outFile).mkdirs();

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
        }
    }
}
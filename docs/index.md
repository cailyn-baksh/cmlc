# CMLC
A CedarML Compiler. CedarML is an XML-based markup language for describing
[Cedar](https://cailyn-baksh.github.io/cedar/) GUIs. This compiler translates
CedarML into valid C code, in order to simplify the creation of Cedar GUIs.

## Usage
```
Usage: cmlc FILE [OPTIONS...]
Compiles CedarML files into C source code.

 FILE               : The source file to compile
 -h (--help)        : Display this help message (default: true)
 -o (--output) PATH : Place output in this folder. (default: ./gui/)
 -q (--quiet)       : Suppress all output (default: false)
 -v (--verbose)     : Display verbose output (default: false)
```

## CedarML
CedarML (CML) is the markup language which CMLC compiles. The CML docs are
available [here]({{ site.baseurl }}{% link cedarml/index.md %}).

## Internal Docs
The internal documentation of CMLC is available at
<https://cailyn-baksh.github.io/cmlc/javadoc>. This documentation is only
relevant to developers of CMLC.

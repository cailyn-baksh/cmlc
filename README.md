# cmlc

Compiles CedarML files into C. CedarML is an XML format that describes
[Cedar](https://github.com/cailyn-baksh/cedar) GUIs.

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

## Documentation

Documentation for CMLC and the CedarML format is available at <https://cailyn-baksh.github.io/cmlc/>.

## CedarML

A RELAX NG schema for CedarML can be found at `res/cedarml.rnc`.
Detailed documentation coming soon.

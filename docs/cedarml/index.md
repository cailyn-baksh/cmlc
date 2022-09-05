---
title: CedarML
has_children: true
---
# CedarML Tags

This section of documentation describes the CML format. CML is built on XML,
so its documents are constructed from a series of XML tags. CML documents begin
with an XML declaration, like all XML documents. After this is the root tag
which in CML is [`<cedarml>`]({% link cedarml.md %}).

## Templates
Some attributes and elements use templates as their types. Templates are a
string which has values substituted with variables currently in scope. These
variables are evaluated at (CML) compile time. Variables are wrapped within
`${}`. A backslash (`\\`) can be used to prevent that character sequence from
being substituted.

## CMLType

CMLType is a special type used in CML documents that extends the base XML
string type. It is a string value which may only have one of the following
values:

| Type   | Description                                                                     |
|--------|---------------------------------------------------------------------------------|
| `int`  | An integer                                                                      |
| `uint` | An unsigned integer                                                             |
| `bool` | A boolean value (`true` or `false`). This value is not case sensitive.          |
| `str`  | A string                                                                        |
| `id`   | An identifier. This can be any valid C symbol.                                  |
| `var`  | A variable. Similar to `id`, but it must be a symbol declared in this document. |

## See Also
 - [`<cedarml>`]({% link cedarml.md %})

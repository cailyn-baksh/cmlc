---
title: "&lt;cedarml&gt;"
parent: CedarML
nav_order: 1
---
# `<cedarml>`
The root tag of a CML document.

## Attributes

| Attribute | Type   | Required? |
|-----------|--------|-----------|
| `version` | String | Yes       |

### `version`
Specifies the version of CML used by the document. The version specified by
this documentation is `1.0`.

## Children
This element may contain the following child elements

| Element                                                       | Count |
|---------------------------------------------------------------|-------|
| [`<import>`]({{ site.baseurl }}{% link cedarml/import.md %})  | 0+    |
| [`<widget>`]({{ site.baseurl }}{% link cedarml/widget.md %})  | 0+    |
| [`<window>`]({{ site.baseurl }}{% link cedarml/window.md %})  | 0+    |

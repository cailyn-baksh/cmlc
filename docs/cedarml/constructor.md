---
title: "&lt;constructor&gt;"
parent: CedarML
---
# `<constructor>`
Defines the C constructor for the widget.

## Attributes

| Attribute | Type    | Required? |
|-----------|---------|-----------|
| name      | String  | Yes       |

### `name`
The name of the C function to call to create the widget. This function must
 - Exist
 - Return a pointer to a widget (`CedarWidget *`)

## Children

| Element                                                    | Count |
|------------------------------------------------------------|-------|
| [`<param>`]({{ site.baseurl }}{% link cedarml/param.md %}) | 0+    |

## See Also
- [`<widget>`]({{ site.baseurl }}{% link cedarml/widget.md %})

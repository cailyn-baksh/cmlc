---
title: "&lt;attr&gt;"
parent: CedarML
---
# `<attr>`
Defines an attribute of a custom widget's XML tag.

## Attributes

| Attribute | Type    | Required? |
|-----------|---------|-----------|
| `name`    | String  | Yes       |
| `type`    | CMLType | Yes       |
| `default` | String  | No        |

### `name`
The name of the attribute. This name can be used by templates within the widget
to access the value of the attribute when the widget is created.

### `type`
The type of the attribute.

### `default`
The default value of the attribute. If this is specified, then the attribute is
optional. If it is omitted, then the attribute is required.

## See Also
- [`<widget>`]({{ site.baseurl }}{% link cedarml/widget.md %})

---
parent: CedarML
---
# `<widget>`
Defines a custom widget. This definition describes two sides of a widget: its
XML representation and its C representation.

## Attributes

| Attribute     | Type    | Required? |
|---------------|---------|-----------|
| `name`        | String  | Yes       |
| `contentType` | CMLType | No        |

### `name`
The name of the XML tag used for this widget. This cannot conflict with any
already existing widgets.

### `contentType`
The type of this widget's XML tag's content.

## Children
This element may contain the following child elements
 
| Element                                      | Count |
|----------------------------------------------|-------|
| [`<attr>`]({% link /cedarml/attr.md %})               | 0+    |
| [`<constructor>`]({% link /cedarml/constructor.md %}) | 0-1   |
| [`<init>`]({% link /cedarml/init.md %})               | 0-1   |

## See Also
- [`<cedarml>`]({% link /cedarml/cedarml.md %})

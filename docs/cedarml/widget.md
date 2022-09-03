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
 
| Element              | Count |
|----------------------|-------|
| [`<attr>`](#)        | 0+    |
| [`<constructor>`](#) | 0-1   |
| [`<init>`](#)        | 0-1   |

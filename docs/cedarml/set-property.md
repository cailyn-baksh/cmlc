---
title: <set-property>
parent: CedarML
---
# `<set-property>`
Dispatches an `EVENT_SETPROPERTY` to a widget.

## Attributes

| Attribute     | Type    | Required? |
|---------------|---------|-----------|
| `name`        | String  | Yes       |

### `name`
The name of the property to set. This must be a valid C identifier that
corresponds to an existing property of the widget.

## Content
The content of this widget is a template used to construct the value to set the
property to.

## See Also
- [`<init>`]({{ site.baseurl }}{% link cedarml/init.md %})

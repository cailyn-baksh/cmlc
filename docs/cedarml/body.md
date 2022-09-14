---
title: "&lt;body&gt;"
parent: CedarML
---
# `<body>`
Describes the content of a window.

## Children
This element may contain the following defined child elements

| Element                                                | Count |
|--------------------------------------------------------|-------|
| [`<var>`]({{ site.baseurl }}{% link cedarml/var.md %}) | 0+    |



### Widget Children

Additionally, the `<body>` element may use any defined widget elements. Widget
child elements are defined through the
[`<widget>`]({{ site.baseurl }}{% link cedarml/widget.md %}) element. On top
of the attributes defined by the `<widget>` element, custom widgets have the
following additional attributes:

| Attribute | Type     | Required? |
|-----------|----------|-----------|
| `var`     | String   | No        |

#### `var`
The variable to assign the widget to. While the window function is running,
this variable will contain a pointer to this widget.

## See Also
- [`<window>`]({{ site.baseurl }}{% link cedarml/window.md %})
- [`<widget>`]({{ site.baseurl }}{% link cedarml/widget.md %})

---
parent: CedarML
---
# `<window>`
Describes a window.

## Attributes

| Attribute  | Type    | Required? |
|------------|---------|-----------|
| `name`     | String  | Yes       |
| `handler`  | String  | Yes       |

### `name`
The name of the window. This name will be capitalized and used to name the
function that initializes and displays the window.

### `handler`
The name of the window's event handler function.

## Children
This element may contain the following child elements

| Element                                     | Count |
|---------------------------------------------|-------|
| [`<colors>`]({{ site.baseurl }}{% link cedarml/colors.md %})  | 0-1   |
| [`<menu>`]({{ site.baseurl }}{% link cedarml/menu.md %})      | 0-1   |
| [`<timer>`]({{ site.baseurl }}{% link cedarml/timer.md %})    | 0+    |
| [`<global>`]({{ site.baseurl }}{% link cedarml/global.md %})  | 0+    |
| [`<body>`](#)                               | 1     |

## See Also
- [`<cedarml>`]({{ site.baseurl }}{% link cedarml/cedarml.md %})

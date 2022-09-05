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

| Element                            | Count |
|------------------------------------|-------|
| [`<colors>`]({% link colors.md %}) | 0-1   |
| [`<menu>`]({% link menu.md %})     | 0-1   |
| [`<timer>`]({% link timer.md %})   | 0+    |
| [`<global>`]({% link global.md %}) | 0+    |
| [`<body>`](#)                      | 1     |

## See Also
- [`<cedarml>`]({% link cedarml.md %})

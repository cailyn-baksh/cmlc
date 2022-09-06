---
title: <menuitem>
parent: CedarML
---
# `<menuitem>`
Defines an item in a menu

## Attributes

| Attribute | Type     | Required?                    |
|-----------|----------|------------------------------|
| `type`    | String   | Yes                          |
| `id`      | String   | If type is not `separator`   |
| `label`   | String   | If type is not `separator`   |

### `type`
The type of the menu item. This may be one of
 - `separator`
 - `item`
 - `submenu`

A `separator` menu item is a line which creates a visual separation between
menu items. An `item` menu item is an item which dispatches `EVENT_MENUSELECT`
when clicked. A `submenu` item is an item which displays a child menu when
clicked.

### `id`
The ID of the menu item. This is not valid in `separator` items.

### `label`
The label of the menu item. This is the text displayed to identify the menu
item. This is not valid in `separator` items.

## Children
This element may contain the following child elements only if `type` is
`submenu`.

| Element                                                  | Count |
|----------------------------------------------------------|-------|
| [`<menu>`]({{ site.baseurl }}{% link cedarml/menu.md %}) | 1     |

## See Also
- [`<menu>`]({{ site.baseurl }}{% link cedarml/menu.md %})

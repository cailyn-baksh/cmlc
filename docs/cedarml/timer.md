---
parent: CedarML
---
# `<timer>`
Adds a timer to a window.

## Attributes

| Attribute  | Type          | Required? |
|------------|---------------|-----------|
| `id`       | String        | Yes       |
| `period`   | Unsigned int  | Yes       |

### `id`
The ID of the timer; used to identify which timer triggered an `EVENT_TICK`.

### `period`
The number of milliseconds between timer ticks.

## See Also
- [`<window>`]({% link cedarml/window.md %})

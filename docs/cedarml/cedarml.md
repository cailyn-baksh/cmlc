# `<cedarml>`
The root tag of a CML document.

## Attributes

| Attribute | Type   | Required? |
|-----------|--------|-----------|
| `version` | String | Yes       |

### `version`
Specifies the version of CML used by the document. The version specified by
this documentation is `1.0`.

## Children
This element may contain the following child elements

| Element         | Count |
|-----------------|-------|
| [`<import>`](#) | 0+    |
| [`<widget>`](#) | 0+    |
| [`<window>`](#) | 0+    |

package io.github.cailyn_baksh.cmlc.cedarml;

public enum CedarMLType {
    INT ("int"),
    UINT ("uint"),
    BOOL ("bool"),
    ID ("id"),
    VAR ("var"),
    STR ("str");

    private String typeName;

    CedarMLType(String typeName) {
        this.typeName = typeName;
    }

    public static CedarMLType fromString(String str) {
        for (CedarMLType type : CedarMLType.values()) {
            if (type.typeName.equals(str)) {
                return type;
            }
        }

        throw new IllegalArgumentException();
    }
}

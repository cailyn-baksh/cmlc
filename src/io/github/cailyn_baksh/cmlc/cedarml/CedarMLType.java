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

        throw new IllegalArgumentException("Invalid type name " + str);
    }

    public boolean validate(String value) {
        switch (this) {
            case INT:
            case UINT:
                try {
                    int n = Integer.parseInt(value);
                    if (this == UINT && n < 0) {
                        return false;
                    }
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            case BOOL:
                return value.equals("true") || value.equals("false");
            case ID:
            case VAR:
                return value.matches("[_a-zA-Z][_a-zA-Z0-9]*");
            case STR:
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return typeName;
    }
}

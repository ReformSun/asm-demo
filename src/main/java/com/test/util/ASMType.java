package com.test.util;

public enum ASMType {
    METHOD("method"),
    FIELD("field"),
    PARAMETER("parameter"),
    MODULE_REQUIRES("module requires"),
    MODULE("module"),
    MODULE_("module *"),
    CLASS("class");

    private String typeName;
    ASMType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}

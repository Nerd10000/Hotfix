package me.dragon.hotfix.core.enums;

public enum DescriptorType {
    
    VOID("V"),
    INT("I"),
    BYTE("B"),
    CHAR("C"),
    SHORT("S"),
    LONG("J"),
    FLOAT("F"),
    DOUBLE("D"),
    BOOLEAN("Z"),
    STRING("Ljava/lang/String;");

    /// TODO: ADD NMS CLASSES.

    private final String descriptor;
    DescriptorType(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getDescriptor() {
        return descriptor;
    }
}

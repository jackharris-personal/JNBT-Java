package com.jackgharris.jnbt.utils;

public enum TagType {

    END(0),
    BYTE(1),
    SHORT(2),
    INT(3),
    LONG(4),
    FLOAT(5),
    DOUBLE(6),
    BYTE_ARRAY(7),
    STRING(8),
    LIST(9),
    COMPOUND(10),

    INT_ARRAY(11),

    FLOAT_ARRAY(12),

    SHORT_ARRAY(13),

    LONG_ARRAY(14),
    DOUBLE_ARRAY(15);

    private final int id;

    TagType(int id) {
        this.id = id;
    }

    public byte getId(){
        return (byte) this.id;
    }

}

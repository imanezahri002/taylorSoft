package org.taylorsoft.taylorsoft.entity.enums;

public enum Ordre {
    UN(1),
    DEUX(2),
    TROIS(3),
    QUATRE(4),
    CINQ(5),
    SIX(6);

    private final int value;

    Ordre(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

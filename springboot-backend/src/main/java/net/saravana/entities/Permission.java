package net.saravana.entities;

public enum Permission {
    READ(1),
    WRITE(1 << 1),
    UPDATE(1 << 2),
    DELETE(1 << 3);

    private final int value;

    Permission(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
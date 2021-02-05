package net.minecraft.server;

public enum BlockPropertyBedPart implements INamable {

    HEAD("head"), FOOT("foot");

    private final String c;

    private BlockPropertyBedPart(String s) {
        this.c = s;
    }

    public String toString() {
        return this.c;
    }

    @Override
    public String getName() {
        return this.c;
    }
}

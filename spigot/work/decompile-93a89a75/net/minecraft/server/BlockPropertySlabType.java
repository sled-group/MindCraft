package net.minecraft.server;

public enum BlockPropertySlabType implements INamable {

    TOP("top"), BOTTOM("bottom"), DOUBLE("double");

    private final String d;

    private BlockPropertySlabType(String s) {
        this.d = s;
    }

    public String toString() {
        return this.d;
    }

    @Override
    public String getName() {
        return this.d;
    }
}

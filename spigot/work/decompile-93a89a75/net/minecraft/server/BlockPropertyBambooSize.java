package net.minecraft.server;

public enum BlockPropertyBambooSize implements INamable {

    NONE("none"), SMALL("small"), LARGE("large");

    private final String d;

    private BlockPropertyBambooSize(String s) {
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

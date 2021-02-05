package net.minecraft.server;

public enum BlockPropertyRedstoneSide implements INamable {

    UP("up"), SIDE("side"), NONE("none");

    private final String d;

    private BlockPropertyRedstoneSide(String s) {
        this.d = s;
    }

    public String toString() {
        return this.getName();
    }

    @Override
    public String getName() {
        return this.d;
    }
}

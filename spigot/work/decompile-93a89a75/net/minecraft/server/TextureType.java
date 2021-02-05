package net.minecraft.server;

public enum TextureType {

    SOLID("Solid"), CUTOUT_MIPPED("Mipped Cutout"), CUTOUT("Cutout"), TRANSLUCENT("Translucent");

    private final String e;

    private TextureType(String s) {
        this.e = s;
    }

    public String toString() {
        return this.e;
    }
}

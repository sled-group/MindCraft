package net.minecraft.server;

public class BiomeLayoutCheckerboardConfiguration implements BiomeLayoutConfiguration {

    private BiomeBase[] a;
    private int b;

    public BiomeLayoutCheckerboardConfiguration() {
        this.a = new BiomeBase[]{Biomes.PLAINS};
        this.b = 1;
    }

    public BiomeLayoutCheckerboardConfiguration a(BiomeBase[] abiomebase) {
        this.a = abiomebase;
        return this;
    }

    public BiomeLayoutCheckerboardConfiguration a(int i) {
        this.b = i;
        return this;
    }

    public BiomeBase[] a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}

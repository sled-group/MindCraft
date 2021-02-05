package net.minecraft.server;

public class BiomeLayoutFixedConfiguration implements BiomeLayoutConfiguration {

    private BiomeBase a;

    public BiomeLayoutFixedConfiguration() {
        this.a = Biomes.PLAINS;
    }

    public BiomeLayoutFixedConfiguration a(BiomeBase biomebase) {
        this.a = biomebase;
        return this;
    }

    public BiomeBase a() {
        return this.a;
    }
}

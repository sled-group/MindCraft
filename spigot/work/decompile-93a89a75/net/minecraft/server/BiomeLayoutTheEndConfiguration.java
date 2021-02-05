package net.minecraft.server;

public class BiomeLayoutTheEndConfiguration implements BiomeLayoutConfiguration {

    private long a;

    public BiomeLayoutTheEndConfiguration() {}

    public BiomeLayoutTheEndConfiguration a(long i) {
        this.a = i;
        return this;
    }

    public long a() {
        return this.a;
    }
}

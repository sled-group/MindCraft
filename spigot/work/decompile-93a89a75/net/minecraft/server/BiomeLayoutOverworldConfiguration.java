package net.minecraft.server;

public class BiomeLayoutOverworldConfiguration implements BiomeLayoutConfiguration {

    private WorldData a;
    private GeneratorSettingsOverworld b;

    public BiomeLayoutOverworldConfiguration() {}

    public BiomeLayoutOverworldConfiguration a(WorldData worlddata) {
        this.a = worlddata;
        return this;
    }

    public BiomeLayoutOverworldConfiguration a(GeneratorSettingsOverworld generatorsettingsoverworld) {
        this.b = generatorsettingsoverworld;
        return this;
    }

    public WorldData a() {
        return this.a;
    }

    public GeneratorSettingsOverworld b() {
        return this.b;
    }
}

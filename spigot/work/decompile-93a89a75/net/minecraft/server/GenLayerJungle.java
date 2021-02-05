package net.minecraft.server;

public enum GenLayerJungle implements AreaTransformer6 {

    INSTANCE;

    private static final int b = IRegistry.BIOME.a((Object) Biomes.JUNGLE);
    private static final int c = IRegistry.BIOME.a((Object) Biomes.BAMBOO_JUNGLE);

    private GenLayerJungle() {}

    @Override
    public int a(WorldGenContext worldgencontext, int i) {
        return worldgencontext.a(10) == 0 && i == GenLayerJungle.b ? GenLayerJungle.c : i;
    }
}

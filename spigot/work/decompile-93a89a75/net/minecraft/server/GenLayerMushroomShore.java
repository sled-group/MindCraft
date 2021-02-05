package net.minecraft.server;

public enum GenLayerMushroomShore implements AreaTransformer7 {

    INSTANCE;

    private static final int b = IRegistry.BIOME.a((Object) Biomes.BEACH);
    private static final int c = IRegistry.BIOME.a((Object) Biomes.SNOWY_BEACH);
    private static final int d = IRegistry.BIOME.a((Object) Biomes.DESERT);
    private static final int e = IRegistry.BIOME.a((Object) Biomes.MOUNTAINS);
    private static final int f = IRegistry.BIOME.a((Object) Biomes.WOODED_MOUNTAINS);
    private static final int g = IRegistry.BIOME.a((Object) Biomes.FOREST);
    private static final int h = IRegistry.BIOME.a((Object) Biomes.JUNGLE);
    private static final int i = IRegistry.BIOME.a((Object) Biomes.JUNGLE_EDGE);
    private static final int j = IRegistry.BIOME.a((Object) Biomes.JUNGLE_HILLS);
    private static final int k = IRegistry.BIOME.a((Object) Biomes.BADLANDS);
    private static final int l = IRegistry.BIOME.a((Object) Biomes.WOODED_BADLANDS_PLATEAU);
    private static final int m = IRegistry.BIOME.a((Object) Biomes.BADLANDS_PLATEAU);
    private static final int n = IRegistry.BIOME.a((Object) Biomes.ERODED_BADLANDS);
    private static final int o = IRegistry.BIOME.a((Object) Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU);
    private static final int p = IRegistry.BIOME.a((Object) Biomes.MODIFIED_BADLANDS_PLATEAU);
    private static final int q = IRegistry.BIOME.a((Object) Biomes.MUSHROOM_FIELDS);
    private static final int r = IRegistry.BIOME.a((Object) Biomes.MUSHROOM_FIELD_SHORE);
    private static final int s = IRegistry.BIOME.a((Object) Biomes.RIVER);
    private static final int t = IRegistry.BIOME.a((Object) Biomes.MOUNTAIN_EDGE);
    private static final int u = IRegistry.BIOME.a((Object) Biomes.STONE_SHORE);
    private static final int v = IRegistry.BIOME.a((Object) Biomes.SWAMP);
    private static final int w = IRegistry.BIOME.a((Object) Biomes.TAIGA);

    private GenLayerMushroomShore() {}

    @Override
    public int a(WorldGenContext worldgencontext, int i, int j, int k, int l, int i1) {
        BiomeBase biomebase = (BiomeBase) IRegistry.BIOME.fromId(i1);

        if (i1 == GenLayerMushroomShore.q) {
            if (GenLayers.b(i) || GenLayers.b(j) || GenLayers.b(k) || GenLayers.b(l)) {
                return GenLayerMushroomShore.r;
            }
        } else if (biomebase != null && biomebase.o() == BiomeBase.Geography.JUNGLE) {
            if (!c(i) || !c(j) || !c(k) || !c(l)) {
                return GenLayerMushroomShore.i;
            }

            if (GenLayers.a(i) || GenLayers.a(j) || GenLayers.a(k) || GenLayers.a(l)) {
                return GenLayerMushroomShore.b;
            }
        } else if (i1 != GenLayerMushroomShore.e && i1 != GenLayerMushroomShore.f && i1 != GenLayerMushroomShore.t) {
            if (biomebase != null && biomebase.b() == BiomeBase.Precipitation.SNOW) {
                if (!GenLayers.a(i1) && (GenLayers.a(i) || GenLayers.a(j) || GenLayers.a(k) || GenLayers.a(l))) {
                    return GenLayerMushroomShore.c;
                }
            } else if (i1 != GenLayerMushroomShore.k && i1 != GenLayerMushroomShore.l) {
                if (!GenLayers.a(i1) && i1 != GenLayerMushroomShore.s && i1 != GenLayerMushroomShore.v && (GenLayers.a(i) || GenLayers.a(j) || GenLayers.a(k) || GenLayers.a(l))) {
                    return GenLayerMushroomShore.b;
                }
            } else if (!GenLayers.a(i) && !GenLayers.a(j) && !GenLayers.a(k) && !GenLayers.a(l) && (!this.d(i) || !this.d(j) || !this.d(k) || !this.d(l))) {
                return GenLayerMushroomShore.d;
            }
        } else if (!GenLayers.a(i1) && (GenLayers.a(i) || GenLayers.a(j) || GenLayers.a(k) || GenLayers.a(l))) {
            return GenLayerMushroomShore.u;
        }

        return i1;
    }

    private static boolean c(int i) {
        return IRegistry.BIOME.fromId(i) != null && ((BiomeBase) IRegistry.BIOME.fromId(i)).o() == BiomeBase.Geography.JUNGLE ? true : i == GenLayerMushroomShore.i || i == GenLayerMushroomShore.h || i == GenLayerMushroomShore.j || i == GenLayerMushroomShore.g || i == GenLayerMushroomShore.w || GenLayers.a(i);
    }

    private boolean d(int i) {
        return i == GenLayerMushroomShore.k || i == GenLayerMushroomShore.l || i == GenLayerMushroomShore.m || i == GenLayerMushroomShore.n || i == GenLayerMushroomShore.o || i == GenLayerMushroomShore.p;
    }
}

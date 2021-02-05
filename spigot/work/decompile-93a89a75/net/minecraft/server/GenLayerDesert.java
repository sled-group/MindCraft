package net.minecraft.server;

public enum GenLayerDesert implements AreaTransformer7 {

    INSTANCE;

    private static final int b = IRegistry.BIOME.a((Object) Biomes.DESERT);
    private static final int c = IRegistry.BIOME.a((Object) Biomes.MOUNTAINS);
    private static final int d = IRegistry.BIOME.a((Object) Biomes.WOODED_MOUNTAINS);
    private static final int e = IRegistry.BIOME.a((Object) Biomes.SNOWY_TUNDRA);
    private static final int f = IRegistry.BIOME.a((Object) Biomes.JUNGLE);
    private static final int g = IRegistry.BIOME.a((Object) Biomes.BAMBOO_JUNGLE);
    private static final int h = IRegistry.BIOME.a((Object) Biomes.JUNGLE_EDGE);
    private static final int i = IRegistry.BIOME.a((Object) Biomes.BADLANDS);
    private static final int j = IRegistry.BIOME.a((Object) Biomes.BADLANDS_PLATEAU);
    private static final int k = IRegistry.BIOME.a((Object) Biomes.WOODED_BADLANDS_PLATEAU);
    private static final int l = IRegistry.BIOME.a((Object) Biomes.PLAINS);
    private static final int m = IRegistry.BIOME.a((Object) Biomes.GIANT_TREE_TAIGA);
    private static final int n = IRegistry.BIOME.a((Object) Biomes.MOUNTAIN_EDGE);
    private static final int o = IRegistry.BIOME.a((Object) Biomes.SWAMP);
    private static final int p = IRegistry.BIOME.a((Object) Biomes.TAIGA);
    private static final int q = IRegistry.BIOME.a((Object) Biomes.SNOWY_TAIGA);

    private GenLayerDesert() {}

    @Override
    public int a(WorldGenContext worldgencontext, int i, int j, int k, int l, int i1) {
        int[] aint = new int[1];

        if (!this.a(aint, i, j, k, l, i1, GenLayerDesert.c, GenLayerDesert.n) && !this.b(aint, i, j, k, l, i1, GenLayerDesert.k, GenLayerDesert.i) && !this.b(aint, i, j, k, l, i1, GenLayerDesert.j, GenLayerDesert.i) && !this.b(aint, i, j, k, l, i1, GenLayerDesert.m, GenLayerDesert.p)) {
            if (i1 == GenLayerDesert.b && (i == GenLayerDesert.e || j == GenLayerDesert.e || l == GenLayerDesert.e || k == GenLayerDesert.e)) {
                return GenLayerDesert.d;
            } else {
                if (i1 == GenLayerDesert.o) {
                    if (i == GenLayerDesert.b || j == GenLayerDesert.b || l == GenLayerDesert.b || k == GenLayerDesert.b || i == GenLayerDesert.q || j == GenLayerDesert.q || l == GenLayerDesert.q || k == GenLayerDesert.q || i == GenLayerDesert.e || j == GenLayerDesert.e || l == GenLayerDesert.e || k == GenLayerDesert.e) {
                        return GenLayerDesert.l;
                    }

                    if (i == GenLayerDesert.f || k == GenLayerDesert.f || j == GenLayerDesert.f || l == GenLayerDesert.f || i == GenLayerDesert.g || k == GenLayerDesert.g || j == GenLayerDesert.g || l == GenLayerDesert.g) {
                        return GenLayerDesert.h;
                    }
                }

                return i1;
            }
        } else {
            return aint[0];
        }
    }

    private boolean a(int[] aint, int i, int j, int k, int l, int i1, int j1, int k1) {
        if (!GenLayers.a(i1, j1)) {
            return false;
        } else {
            if (this.a(i, j1) && this.a(j, j1) && this.a(l, j1) && this.a(k, j1)) {
                aint[0] = i1;
            } else {
                aint[0] = k1;
            }

            return true;
        }
    }

    private boolean b(int[] aint, int i, int j, int k, int l, int i1, int j1, int k1) {
        if (i1 != j1) {
            return false;
        } else {
            if (GenLayers.a(i, j1) && GenLayers.a(j, j1) && GenLayers.a(l, j1) && GenLayers.a(k, j1)) {
                aint[0] = i1;
            } else {
                aint[0] = k1;
            }

            return true;
        }
    }

    private boolean a(int i, int j) {
        if (GenLayers.a(i, j)) {
            return true;
        } else {
            BiomeBase biomebase = (BiomeBase) IRegistry.BIOME.fromId(i);
            BiomeBase biomebase1 = (BiomeBase) IRegistry.BIOME.fromId(j);

            if (biomebase != null && biomebase1 != null) {
                BiomeBase.EnumTemperature biomebase_enumtemperature = biomebase.f();
                BiomeBase.EnumTemperature biomebase_enumtemperature1 = biomebase1.f();

                return biomebase_enumtemperature == biomebase_enumtemperature1 || biomebase_enumtemperature == BiomeBase.EnumTemperature.MEDIUM || biomebase_enumtemperature1 == BiomeBase.EnumTemperature.MEDIUM;
            } else {
                return false;
            }
        }
    }
}

package net.minecraft.server;

public class GenLayerBiome implements AreaTransformer5 {

    private static final int a = IRegistry.BIOME.a((Object) Biomes.BIRCH_FOREST);
    private static final int b = IRegistry.BIOME.a((Object) Biomes.DESERT);
    private static final int c = IRegistry.BIOME.a((Object) Biomes.MOUNTAINS);
    private static final int d = IRegistry.BIOME.a((Object) Biomes.FOREST);
    private static final int e = IRegistry.BIOME.a((Object) Biomes.SNOWY_TUNDRA);
    private static final int f = IRegistry.BIOME.a((Object) Biomes.JUNGLE);
    private static final int g = IRegistry.BIOME.a((Object) Biomes.BADLANDS_PLATEAU);
    private static final int h = IRegistry.BIOME.a((Object) Biomes.WOODED_BADLANDS_PLATEAU);
    private static final int i = IRegistry.BIOME.a((Object) Biomes.MUSHROOM_FIELDS);
    private static final int j = IRegistry.BIOME.a((Object) Biomes.PLAINS);
    private static final int k = IRegistry.BIOME.a((Object) Biomes.GIANT_TREE_TAIGA);
    private static final int l = IRegistry.BIOME.a((Object) Biomes.DARK_FOREST);
    private static final int m = IRegistry.BIOME.a((Object) Biomes.SAVANNA);
    private static final int n = IRegistry.BIOME.a((Object) Biomes.SWAMP);
    private static final int o = IRegistry.BIOME.a((Object) Biomes.TAIGA);
    private static final int p = IRegistry.BIOME.a((Object) Biomes.SNOWY_TAIGA);
    private static final int[] q = new int[]{GenLayerBiome.b, GenLayerBiome.d, GenLayerBiome.c, GenLayerBiome.n, GenLayerBiome.j, GenLayerBiome.o};
    private static final int[] r = new int[]{GenLayerBiome.b, GenLayerBiome.b, GenLayerBiome.b, GenLayerBiome.m, GenLayerBiome.m, GenLayerBiome.j};
    private static final int[] s = new int[]{GenLayerBiome.d, GenLayerBiome.l, GenLayerBiome.c, GenLayerBiome.j, GenLayerBiome.a, GenLayerBiome.n};
    private static final int[] t = new int[]{GenLayerBiome.d, GenLayerBiome.c, GenLayerBiome.o, GenLayerBiome.j};
    private static final int[] u = new int[]{GenLayerBiome.e, GenLayerBiome.e, GenLayerBiome.e, GenLayerBiome.p};
    private final GeneratorSettingsOverworld v;
    private int[] w;

    public GenLayerBiome(WorldType worldtype, GeneratorSettingsOverworld generatorsettingsoverworld) {
        this.w = GenLayerBiome.r;
        if (worldtype == WorldType.NORMAL_1_1) {
            this.w = GenLayerBiome.q;
            this.v = null;
        } else {
            this.v = generatorsettingsoverworld;
        }

    }

    @Override
    public int a(WorldGenContext worldgencontext, int i) {
        if (this.v != null && this.v.x() >= 0) {
            return this.v.x();
        } else {
            int j = (i & 3840) >> 8;

            i &= -3841;
            if (!GenLayers.a(i) && i != GenLayerBiome.i) {
                switch (i) {
                    case 1:
                        if (j > 0) {
                            return worldgencontext.a(3) == 0 ? GenLayerBiome.g : GenLayerBiome.h;
                        }

                        return this.w[worldgencontext.a(this.w.length)];
                    case 2:
                        if (j > 0) {
                            return GenLayerBiome.f;
                        }

                        return GenLayerBiome.s[worldgencontext.a(GenLayerBiome.s.length)];
                    case 3:
                        if (j > 0) {
                            return GenLayerBiome.k;
                        }

                        return GenLayerBiome.t[worldgencontext.a(GenLayerBiome.t.length)];
                    case 4:
                        return GenLayerBiome.u[worldgencontext.a(GenLayerBiome.u.length)];
                    default:
                        return GenLayerBiome.i;
                }
            } else {
                return i;
            }
        }
    }
}

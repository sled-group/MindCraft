package net.minecraft.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum GenLayerRegionHills implements AreaTransformer3, AreaTransformerOffset1 {

    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int c = IRegistry.BIOME.a((Object) Biomes.BIRCH_FOREST);
    private static final int d = IRegistry.BIOME.a((Object) Biomes.BIRCH_FOREST_HILLS);
    private static final int e = IRegistry.BIOME.a((Object) Biomes.DESERT);
    private static final int f = IRegistry.BIOME.a((Object) Biomes.DESERT_HILLS);
    private static final int g = IRegistry.BIOME.a((Object) Biomes.MOUNTAINS);
    private static final int h = IRegistry.BIOME.a((Object) Biomes.WOODED_MOUNTAINS);
    private static final int i = IRegistry.BIOME.a((Object) Biomes.FOREST);
    private static final int j = IRegistry.BIOME.a((Object) Biomes.WOODED_HILLS);
    private static final int k = IRegistry.BIOME.a((Object) Biomes.SNOWY_TUNDRA);
    private static final int l = IRegistry.BIOME.a((Object) Biomes.SNOWY_MOUNTAINS);
    private static final int m = IRegistry.BIOME.a((Object) Biomes.JUNGLE);
    private static final int n = IRegistry.BIOME.a((Object) Biomes.JUNGLE_HILLS);
    private static final int o = IRegistry.BIOME.a((Object) Biomes.BAMBOO_JUNGLE);
    private static final int p = IRegistry.BIOME.a((Object) Biomes.BAMBOO_JUNGLE_HILLS);
    private static final int q = IRegistry.BIOME.a((Object) Biomes.BADLANDS);
    private static final int r = IRegistry.BIOME.a((Object) Biomes.WOODED_BADLANDS_PLATEAU);
    private static final int s = IRegistry.BIOME.a((Object) Biomes.PLAINS);
    private static final int t = IRegistry.BIOME.a((Object) Biomes.GIANT_TREE_TAIGA);
    private static final int u = IRegistry.BIOME.a((Object) Biomes.GIANT_TREE_TAIGA_HILLS);
    private static final int v = IRegistry.BIOME.a((Object) Biomes.DARK_FOREST);
    private static final int w = IRegistry.BIOME.a((Object) Biomes.SAVANNA);
    private static final int x = IRegistry.BIOME.a((Object) Biomes.SAVANNA_PLATEAU);
    private static final int y = IRegistry.BIOME.a((Object) Biomes.TAIGA);
    private static final int z = IRegistry.BIOME.a((Object) Biomes.SNOWY_TAIGA);
    private static final int A = IRegistry.BIOME.a((Object) Biomes.SNOWY_TAIGA_HILLS);
    private static final int B = IRegistry.BIOME.a((Object) Biomes.TAIGA_HILLS);

    private GenLayerRegionHills() {}

    @Override
    public int a(WorldGenContext worldgencontext, Area area, Area area1, int i, int j) {
        int k = area.a(this.a(i + 1), this.b(j + 1));
        int l = area1.a(this.a(i + 1), this.b(j + 1));

        if (k > 255) {
            GenLayerRegionHills.LOGGER.debug("old! {}", k);
        }

        int i1 = (l - 2) % 29;
        BiomeBase biomebase;

        if (!GenLayers.b(k) && l >= 2 && i1 == 1) {
            BiomeBase biomebase1 = (BiomeBase) IRegistry.BIOME.fromId(k);

            if (biomebase1 == null || !biomebase1.a()) {
                biomebase = BiomeBase.a(biomebase1);
                return biomebase == null ? k : IRegistry.BIOME.a((Object) biomebase);
            }
        }

        if (worldgencontext.a(3) == 0 || i1 == 0) {
            int j1 = k;

            if (k == GenLayerRegionHills.e) {
                j1 = GenLayerRegionHills.f;
            } else if (k == GenLayerRegionHills.i) {
                j1 = GenLayerRegionHills.j;
            } else if (k == GenLayerRegionHills.c) {
                j1 = GenLayerRegionHills.d;
            } else if (k == GenLayerRegionHills.v) {
                j1 = GenLayerRegionHills.s;
            } else if (k == GenLayerRegionHills.y) {
                j1 = GenLayerRegionHills.B;
            } else if (k == GenLayerRegionHills.t) {
                j1 = GenLayerRegionHills.u;
            } else if (k == GenLayerRegionHills.z) {
                j1 = GenLayerRegionHills.A;
            } else if (k == GenLayerRegionHills.s) {
                j1 = worldgencontext.a(3) == 0 ? GenLayerRegionHills.j : GenLayerRegionHills.i;
            } else if (k == GenLayerRegionHills.k) {
                j1 = GenLayerRegionHills.l;
            } else if (k == GenLayerRegionHills.m) {
                j1 = GenLayerRegionHills.n;
            } else if (k == GenLayerRegionHills.o) {
                j1 = GenLayerRegionHills.p;
            } else if (k == GenLayers.c) {
                j1 = GenLayers.h;
            } else if (k == GenLayers.b) {
                j1 = GenLayers.g;
            } else if (k == GenLayers.d) {
                j1 = GenLayers.i;
            } else if (k == GenLayers.e) {
                j1 = GenLayers.j;
            } else if (k == GenLayerRegionHills.g) {
                j1 = GenLayerRegionHills.h;
            } else if (k == GenLayerRegionHills.w) {
                j1 = GenLayerRegionHills.x;
            } else if (GenLayers.a(k, GenLayerRegionHills.r)) {
                j1 = GenLayerRegionHills.q;
            } else if ((k == GenLayers.h || k == GenLayers.g || k == GenLayers.i || k == GenLayers.j) && worldgencontext.a(3) == 0) {
                j1 = worldgencontext.a(2) == 0 ? GenLayerRegionHills.s : GenLayerRegionHills.i;
            }

            if (i1 == 0 && j1 != k) {
                biomebase = BiomeBase.a((BiomeBase) IRegistry.BIOME.fromId(j1));
                j1 = biomebase == null ? k : IRegistry.BIOME.a((Object) biomebase);
            }

            if (j1 != k) {
                int k1 = 0;

                if (GenLayers.a(area.a(this.a(i + 1), this.b(j + 0)), k)) {
                    ++k1;
                }

                if (GenLayers.a(area.a(this.a(i + 2), this.b(j + 1)), k)) {
                    ++k1;
                }

                if (GenLayers.a(area.a(this.a(i + 0), this.b(j + 1)), k)) {
                    ++k1;
                }

                if (GenLayers.a(area.a(this.a(i + 1), this.b(j + 2)), k)) {
                    ++k1;
                }

                if (k1 >= 3) {
                    return j1;
                }
            }
        }

        return k;
    }
}

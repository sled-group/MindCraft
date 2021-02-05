package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import java.util.function.LongFunction;

public class GenLayers {

    protected static final int a = IRegistry.BIOME.a((Object) Biomes.WARM_OCEAN);
    protected static final int b = IRegistry.BIOME.a((Object) Biomes.LUKEWARM_OCEAN);
    protected static final int c = IRegistry.BIOME.a((Object) Biomes.OCEAN);
    protected static final int d = IRegistry.BIOME.a((Object) Biomes.COLD_OCEAN);
    protected static final int e = IRegistry.BIOME.a((Object) Biomes.FROZEN_OCEAN);
    protected static final int f = IRegistry.BIOME.a((Object) Biomes.DEEP_WARM_OCEAN);
    protected static final int g = IRegistry.BIOME.a((Object) Biomes.DEEP_LUKEWARM_OCEAN);
    protected static final int h = IRegistry.BIOME.a((Object) Biomes.DEEP_OCEAN);
    protected static final int i = IRegistry.BIOME.a((Object) Biomes.DEEP_COLD_OCEAN);
    protected static final int j = IRegistry.BIOME.a((Object) Biomes.DEEP_FROZEN_OCEAN);

    private static <T extends Area, C extends AreaContextTransformed<T>> AreaFactory<T> a(long i, AreaTransformer2 areatransformer2, AreaFactory<T> areafactory, int j, LongFunction<C> longfunction) {
        AreaFactory<T> areafactory1 = areafactory;

        for (int k = 0; k < j; ++k) {
            areafactory1 = areatransformer2.a((AreaContextTransformed) longfunction.apply(i + (long) k), areafactory1);
        }

        return areafactory1;
    }

    public static <T extends Area, C extends AreaContextTransformed<T>> ImmutableList<AreaFactory<T>> a(WorldType worldtype, GeneratorSettingsOverworld generatorsettingsoverworld, LongFunction<C> longfunction) {
        AreaFactory<T> areafactory = LayerIsland.INSTANCE.a((AreaContextTransformed) longfunction.apply(1L));

        areafactory = GenLayerZoom.FUZZY.a((AreaContextTransformed) longfunction.apply(2000L), areafactory);
        areafactory = GenLayerIsland.INSTANCE.a((AreaContextTransformed) longfunction.apply(1L), areafactory);
        areafactory = GenLayerZoom.NORMAL.a((AreaContextTransformed) longfunction.apply(2001L), areafactory);
        areafactory = GenLayerIsland.INSTANCE.a((AreaContextTransformed) longfunction.apply(2L), areafactory);
        areafactory = GenLayerIsland.INSTANCE.a((AreaContextTransformed) longfunction.apply(50L), areafactory);
        areafactory = GenLayerIsland.INSTANCE.a((AreaContextTransformed) longfunction.apply(70L), areafactory);
        areafactory = GenLayerIcePlains.INSTANCE.a((AreaContextTransformed) longfunction.apply(2L), areafactory);
        AreaFactory<T> areafactory1 = GenLayerOceanEdge.INSTANCE.a((AreaContextTransformed) longfunction.apply(2L));

        areafactory1 = a(2001L, GenLayerZoom.NORMAL, areafactory1, 6, longfunction);
        areafactory = GenLayerTopSoil.INSTANCE.a((AreaContextTransformed) longfunction.apply(2L), areafactory);
        areafactory = GenLayerIsland.INSTANCE.a((AreaContextTransformed) longfunction.apply(3L), areafactory);
        areafactory = GenLayerSpecial.Special1.INSTANCE.a((AreaContextTransformed) longfunction.apply(2L), areafactory);
        areafactory = GenLayerSpecial.Special2.INSTANCE.a((AreaContextTransformed) longfunction.apply(2L), areafactory);
        areafactory = GenLayerSpecial.Special3.INSTANCE.a((AreaContextTransformed) longfunction.apply(3L), areafactory);
        areafactory = GenLayerZoom.NORMAL.a((AreaContextTransformed) longfunction.apply(2002L), areafactory);
        areafactory = GenLayerZoom.NORMAL.a((AreaContextTransformed) longfunction.apply(2003L), areafactory);
        areafactory = GenLayerIsland.INSTANCE.a((AreaContextTransformed) longfunction.apply(4L), areafactory);
        areafactory = GenLayerMushroomIsland.INSTANCE.a((AreaContextTransformed) longfunction.apply(5L), areafactory);
        areafactory = GenLayerDeepOcean.INSTANCE.a((AreaContextTransformed) longfunction.apply(4L), areafactory);
        areafactory = a(1000L, GenLayerZoom.NORMAL, areafactory, 0, longfunction);
        int i = 4;
        int j = i;

        if (generatorsettingsoverworld != null) {
            i = generatorsettingsoverworld.v();
            j = generatorsettingsoverworld.w();
        }

        if (worldtype == WorldType.LARGE_BIOMES) {
            i = 6;
        }

        AreaFactory<T> areafactory2 = a(1000L, GenLayerZoom.NORMAL, areafactory, 0, longfunction);

        areafactory2 = GenLayerCleaner.INSTANCE.a((AreaContextTransformed) longfunction.apply(100L), areafactory2);
        AreaFactory<T> areafactory3 = (new GenLayerBiome(worldtype, generatorsettingsoverworld)).a((AreaContextTransformed) longfunction.apply(200L), areafactory);

        areafactory3 = GenLayerJungle.INSTANCE.a((AreaContextTransformed) longfunction.apply(1001L), areafactory3);
        areafactory3 = a(1000L, GenLayerZoom.NORMAL, areafactory3, 2, longfunction);
        areafactory3 = GenLayerDesert.INSTANCE.a((AreaContextTransformed) longfunction.apply(1000L), areafactory3);
        AreaFactory<T> areafactory4 = a(1000L, GenLayerZoom.NORMAL, areafactory2, 2, longfunction);

        areafactory3 = GenLayerRegionHills.INSTANCE.a((AreaContextTransformed) longfunction.apply(1000L), areafactory3, areafactory4);
        areafactory2 = a(1000L, GenLayerZoom.NORMAL, areafactory2, 2, longfunction);
        areafactory2 = a(1000L, GenLayerZoom.NORMAL, areafactory2, j, longfunction);
        areafactory2 = GenLayerRiver.INSTANCE.a((AreaContextTransformed) longfunction.apply(1L), areafactory2);
        areafactory2 = GenLayerSmooth.INSTANCE.a((AreaContextTransformed) longfunction.apply(1000L), areafactory2);
        areafactory3 = GenLayerPlains.INSTANCE.a((AreaContextTransformed) longfunction.apply(1001L), areafactory3);

        for (int k = 0; k < i; ++k) {
            areafactory3 = GenLayerZoom.NORMAL.a((AreaContextTransformed) longfunction.apply((long) (1000 + k)), areafactory3);
            if (k == 0) {
                areafactory3 = GenLayerIsland.INSTANCE.a((AreaContextTransformed) longfunction.apply(3L), areafactory3);
            }

            if (k == 1 || i == 1) {
                areafactory3 = GenLayerMushroomShore.INSTANCE.a((AreaContextTransformed) longfunction.apply(1000L), areafactory3);
            }
        }

        areafactory3 = GenLayerSmooth.INSTANCE.a((AreaContextTransformed) longfunction.apply(1000L), areafactory3);
        areafactory3 = GenLayerRiverMix.INSTANCE.a((AreaContextTransformed) longfunction.apply(100L), areafactory3, areafactory2);
        areafactory3 = GenLayerOcean.INSTANCE.a((AreaContextTransformed) longfunction.apply(100L), areafactory3, areafactory1);
        AreaFactory<T> areafactory5 = GenLayerZoomVoronoi.INSTANCE.a((AreaContextTransformed) longfunction.apply(10L), areafactory3);

        return ImmutableList.of(areafactory3, areafactory5, areafactory3);
    }

    public static GenLayer[] a(long i, WorldType worldtype, GeneratorSettingsOverworld generatorsettingsoverworld) {
        boolean flag = true;
        ImmutableList<AreaFactory<AreaLazy>> immutablelist = a(worldtype, generatorsettingsoverworld, (j) -> {
            return new WorldGenContextArea(25, i, j);
        });
        GenLayer genlayer = new GenLayer((AreaFactory) immutablelist.get(0));
        GenLayer genlayer1 = new GenLayer((AreaFactory) immutablelist.get(1));
        GenLayer genlayer2 = new GenLayer((AreaFactory) immutablelist.get(2));

        return new GenLayer[]{genlayer, genlayer1, genlayer2};
    }

    public static boolean a(int i, int j) {
        if (i == j) {
            return true;
        } else {
            BiomeBase biomebase = (BiomeBase) IRegistry.BIOME.fromId(i);
            BiomeBase biomebase1 = (BiomeBase) IRegistry.BIOME.fromId(j);

            return biomebase != null && biomebase1 != null ? (biomebase != Biomes.WOODED_BADLANDS_PLATEAU && biomebase != Biomes.BADLANDS_PLATEAU ? (biomebase.o() != BiomeBase.Geography.NONE && biomebase1.o() != BiomeBase.Geography.NONE && biomebase.o() == biomebase1.o() ? true : biomebase == biomebase1) : biomebase1 == Biomes.WOODED_BADLANDS_PLATEAU || biomebase1 == Biomes.BADLANDS_PLATEAU) : false;
        }
    }

    protected static boolean a(int i) {
        return i == GenLayers.a || i == GenLayers.b || i == GenLayers.c || i == GenLayers.d || i == GenLayers.e || i == GenLayers.f || i == GenLayers.g || i == GenLayers.h || i == GenLayers.i || i == GenLayers.j;
    }

    protected static boolean b(int i) {
        return i == GenLayers.a || i == GenLayers.b || i == GenLayers.c || i == GenLayers.d || i == GenLayers.e;
    }
}

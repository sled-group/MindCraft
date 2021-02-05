package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

public class ChunkProviderFlat extends ChunkGenerator<GeneratorSettingsFlat> {

    private final BiomeBase e = this.g();
    private final MobSpawnerPhantom f = new MobSpawnerPhantom();
    private final MobSpawnerCat g = new MobSpawnerCat();

    public ChunkProviderFlat(GeneratorAccess generatoraccess, WorldChunkManager worldchunkmanager, GeneratorSettingsFlat generatorsettingsflat) {
        super(generatoraccess, worldchunkmanager, generatorsettingsflat);
    }

    private BiomeBase g() {
        BiomeBase biomebase = ((GeneratorSettingsFlat) this.settings).v();
        ChunkProviderFlat.a chunkproviderflat_a = new ChunkProviderFlat.a(biomebase.p(), biomebase.b(), biomebase.o(), biomebase.g(), biomebase.k(), biomebase.getTemperature(), biomebase.getHumidity(), biomebase.m(), biomebase.n(), biomebase.r());
        Map<String, Map<String, String>> map = ((GeneratorSettingsFlat) this.settings).w();
        Iterator iterator = map.keySet().iterator();

        int i;
        WorldGenFeatureConfigured worldgenfeatureconfigured;

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            WorldGenFeatureConfigured<?>[] aworldgenfeatureconfigured = (WorldGenFeatureConfigured[]) GeneratorSettingsFlat.u.get(s);

            if (aworldgenfeatureconfigured != null) {
                WorldGenFeatureConfigured[] aworldgenfeatureconfigured1 = aworldgenfeatureconfigured;

                i = aworldgenfeatureconfigured.length;

                for (int j = 0; j < i; ++j) {
                    WorldGenFeatureConfigured<?> worldgenfeatureconfigured1 = aworldgenfeatureconfigured1[j];

                    chunkproviderflat_a.a((WorldGenStage.Decoration) GeneratorSettingsFlat.t.get(worldgenfeatureconfigured1), worldgenfeatureconfigured1);
                    worldgenfeatureconfigured = ((WorldGenFeatureCompositeConfiguration) worldgenfeatureconfigured1.b).a;
                    if (worldgenfeatureconfigured.a instanceof StructureGenerator) {
                        StructureGenerator<WorldGenFeatureConfiguration> structuregenerator = (StructureGenerator) worldgenfeatureconfigured.a;
                        WorldGenFeatureConfiguration worldgenfeatureconfiguration = biomebase.b(structuregenerator);

                        chunkproviderflat_a.a(structuregenerator, worldgenfeatureconfiguration != null ? worldgenfeatureconfiguration : (WorldGenFeatureConfiguration) GeneratorSettingsFlat.v.get(worldgenfeatureconfigured1));
                    }
                }
            }
        }

        boolean flag = (!((GeneratorSettingsFlat) this.settings).A() || biomebase == Biomes.THE_VOID) && map.containsKey("decoration");

        if (flag) {
            List<WorldGenStage.Decoration> list = Lists.newArrayList();

            list.add(WorldGenStage.Decoration.UNDERGROUND_STRUCTURES);
            list.add(WorldGenStage.Decoration.SURFACE_STRUCTURES);
            WorldGenStage.Decoration[] aworldgenstage_decoration = WorldGenStage.Decoration.values();
            int k = aworldgenstage_decoration.length;

            for (i = 0; i < k; ++i) {
                WorldGenStage.Decoration worldgenstage_decoration = aworldgenstage_decoration[i];

                if (!list.contains(worldgenstage_decoration)) {
                    Iterator iterator1 = biomebase.a(worldgenstage_decoration).iterator();

                    while (iterator1.hasNext()) {
                        worldgenfeatureconfigured = (WorldGenFeatureConfigured) iterator1.next();
                        chunkproviderflat_a.a(worldgenstage_decoration, worldgenfeatureconfigured);
                    }
                }
            }
        }

        IBlockData[] aiblockdata = ((GeneratorSettingsFlat) this.settings).C();

        for (int l = 0; l < aiblockdata.length; ++l) {
            IBlockData iblockdata = aiblockdata[l];

            if (iblockdata != null && !HeightMap.Type.MOTION_BLOCKING.d().test(iblockdata)) {
                ((GeneratorSettingsFlat) this.settings).a(l);
                chunkproviderflat_a.a(WorldGenStage.Decoration.TOP_LAYER_MODIFICATION, BiomeBase.a(WorldGenerator.aN, new WorldGenFeatureFillConfiguration(l, iblockdata), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
            }
        }

        return chunkproviderflat_a;
    }

    @Override
    public void buildBase(IChunkAccess ichunkaccess) {}

    @Override
    public int getSpawnHeight() {
        IChunkAccess ichunkaccess = this.a.getChunkAt(0, 0);

        return ichunkaccess.a(HeightMap.Type.MOTION_BLOCKING, 8, 8);
    }

    @Override
    protected BiomeBase getCarvingBiome(IChunkAccess ichunkaccess) {
        return this.e;
    }

    @Override
    protected BiomeBase getDecoratingBiome(RegionLimitedWorldAccess regionlimitedworldaccess, BlockPosition blockposition) {
        return this.e;
    }

    @Override
    public void buildNoise(GeneratorAccess generatoraccess, IChunkAccess ichunkaccess) {
        IBlockData[] aiblockdata = ((GeneratorSettingsFlat) this.settings).C();
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
        HeightMap heightmap = ichunkaccess.b(HeightMap.Type.OCEAN_FLOOR_WG);
        HeightMap heightmap1 = ichunkaccess.b(HeightMap.Type.WORLD_SURFACE_WG);

        for (int i = 0; i < aiblockdata.length; ++i) {
            IBlockData iblockdata = aiblockdata[i];

            if (iblockdata != null) {
                for (int j = 0; j < 16; ++j) {
                    for (int k = 0; k < 16; ++k) {
                        ichunkaccess.setType(blockposition_mutableblockposition.d(j, i, k), iblockdata, false);
                        heightmap.a(j, i, k, iblockdata);
                        heightmap1.a(j, i, k, iblockdata);
                    }
                }
            }
        }

    }

    @Override
    public int getBaseHeight(int i, int j, HeightMap.Type heightmap_type) {
        IBlockData[] aiblockdata = ((GeneratorSettingsFlat) this.settings).C();

        for (int k = aiblockdata.length - 1; k >= 0; --k) {
            IBlockData iblockdata = aiblockdata[k];

            if (iblockdata != null && heightmap_type.d().test(iblockdata)) {
                return k + 1;
            }
        }

        return 0;
    }

    @Override
    public void doMobSpawning(WorldServer worldserver, boolean flag, boolean flag1) {
        this.f.a(worldserver, flag, flag1);
        this.g.a(worldserver, flag, flag1);
    }

    @Override
    public boolean canSpawnStructure(BiomeBase biomebase, StructureGenerator<? extends WorldGenFeatureConfiguration> structuregenerator) {
        return this.e.a(structuregenerator);
    }

    @Nullable
    @Override
    public <C extends WorldGenFeatureConfiguration> C getFeatureConfiguration(BiomeBase biomebase, StructureGenerator<C> structuregenerator) {
        return this.e.b(structuregenerator);
    }

    @Nullable
    @Override
    public BlockPosition findNearestMapFeature(World world, String s, BlockPosition blockposition, int i, boolean flag) {
        return !((GeneratorSettingsFlat) this.settings).w().keySet().contains(s.toLowerCase(Locale.ROOT)) ? null : super.findNearestMapFeature(world, s, blockposition, i, flag);
    }

    class a extends BiomeBase {

        protected a(WorldGenSurfaceComposite worldgensurfacecomposite, BiomeBase.Precipitation biomebase_precipitation, BiomeBase.Geography biomebase_geography, float f, float f1, float f2, float f3, int i, int j, String s) {
            super((new BiomeBase.a()).a(worldgensurfacecomposite).a(biomebase_precipitation).a(biomebase_geography).a(f).b(f1).c(f2).d(f3).a(i).b(j).a(s));
        }
    }
}

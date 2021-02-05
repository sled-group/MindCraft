package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.util.Pair;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GeneratorSettingsFlat extends GeneratorSettingsDefault {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final WorldGenFeatureConfigured<?> x = BiomeBase.a(WorldGenerator.MINESHAFT, new WorldGenMineshaftConfiguration(0.004D, WorldGenMineshaft.Type.NORMAL), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> y = BiomeBase.a(WorldGenerator.VILLAGE, new WorldGenFeatureVillageConfiguration("village/plains/town_centers", 6), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> z = BiomeBase.a(WorldGenerator.STRONGHOLD, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> A = BiomeBase.a(WorldGenerator.SWAMP_HUT, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> B = BiomeBase.a(WorldGenerator.DESERT_PYRAMID, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> C = BiomeBase.a(WorldGenerator.JUNGLE_TEMPLE, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> D = BiomeBase.a(WorldGenerator.IGLOO, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> E = BiomeBase.a(WorldGenerator.SHIPWRECK, new WorldGenFeatureShipwreckConfiguration(false), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> F = BiomeBase.a(WorldGenerator.OCEAN_MONUMENT, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> G = BiomeBase.a(WorldGenerator.LAKE, new WorldGenFeatureLakeConfiguration(Blocks.WATER.getBlockData()), WorldGenDecorator.E, new WorldGenDecoratorLakeChanceConfiguration(4));
    private static final WorldGenFeatureConfigured<?> H = BiomeBase.a(WorldGenerator.LAKE, new WorldGenFeatureLakeConfiguration(Blocks.LAVA.getBlockData()), WorldGenDecorator.D, new WorldGenDecoratorLakeChanceConfiguration(80));
    private static final WorldGenFeatureConfigured<?> I = BiomeBase.a(WorldGenerator.END_CITY, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> J = BiomeBase.a(WorldGenerator.WOODLAND_MANSION, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> K = BiomeBase.a(WorldGenerator.NETHER_BRIDGE, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> L = BiomeBase.a(WorldGenerator.OCEAN_RUIN, new WorldGenFeatureOceanRuinConfiguration(WorldGenFeatureOceanRuin.Temperature.COLD, 0.3F, 0.1F), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    private static final WorldGenFeatureConfigured<?> M = BiomeBase.a(WorldGenerator.PILLAGER_OUTPOST, new WorldGenFeaturePillagerOutpostConfiguration(0.004D), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e);
    public static final Map<WorldGenFeatureConfigured<?>, WorldGenStage.Decoration> t = (Map) SystemUtils.a((Object) Maps.newHashMap(), (hashmap) -> {
        hashmap.put(GeneratorSettingsFlat.x, WorldGenStage.Decoration.UNDERGROUND_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.y, WorldGenStage.Decoration.SURFACE_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.z, WorldGenStage.Decoration.UNDERGROUND_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.A, WorldGenStage.Decoration.SURFACE_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.B, WorldGenStage.Decoration.SURFACE_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.C, WorldGenStage.Decoration.SURFACE_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.D, WorldGenStage.Decoration.SURFACE_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.E, WorldGenStage.Decoration.SURFACE_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.L, WorldGenStage.Decoration.SURFACE_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.G, WorldGenStage.Decoration.LOCAL_MODIFICATIONS);
        hashmap.put(GeneratorSettingsFlat.H, WorldGenStage.Decoration.LOCAL_MODIFICATIONS);
        hashmap.put(GeneratorSettingsFlat.I, WorldGenStage.Decoration.SURFACE_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.J, WorldGenStage.Decoration.SURFACE_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.K, WorldGenStage.Decoration.UNDERGROUND_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.F, WorldGenStage.Decoration.SURFACE_STRUCTURES);
        hashmap.put(GeneratorSettingsFlat.M, WorldGenStage.Decoration.SURFACE_STRUCTURES);
    });
    public static final Map<String, WorldGenFeatureConfigured<?>[]> u = (Map) SystemUtils.a((Object) Maps.newHashMap(), (hashmap) -> {
        hashmap.put("mineshaft", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.x});
        hashmap.put("village", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.y});
        hashmap.put("stronghold", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.z});
        hashmap.put("biome_1", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.A, GeneratorSettingsFlat.B, GeneratorSettingsFlat.C, GeneratorSettingsFlat.D, GeneratorSettingsFlat.L, GeneratorSettingsFlat.E});
        hashmap.put("oceanmonument", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.F});
        hashmap.put("lake", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.G});
        hashmap.put("lava_lake", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.H});
        hashmap.put("endcity", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.I});
        hashmap.put("mansion", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.J});
        hashmap.put("fortress", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.K});
        hashmap.put("pillager_outpost", new WorldGenFeatureConfigured[]{GeneratorSettingsFlat.M});
    });
    public static final Map<WorldGenFeatureConfigured<?>, WorldGenFeatureConfiguration> v = (Map) SystemUtils.a((Object) Maps.newHashMap(), (hashmap) -> {
        hashmap.put(GeneratorSettingsFlat.x, new WorldGenMineshaftConfiguration(0.004D, WorldGenMineshaft.Type.NORMAL));
        hashmap.put(GeneratorSettingsFlat.y, new WorldGenFeatureVillageConfiguration("village/plains/town_centers", 6));
        hashmap.put(GeneratorSettingsFlat.z, WorldGenFeatureConfiguration.e);
        hashmap.put(GeneratorSettingsFlat.A, WorldGenFeatureConfiguration.e);
        hashmap.put(GeneratorSettingsFlat.B, WorldGenFeatureConfiguration.e);
        hashmap.put(GeneratorSettingsFlat.C, WorldGenFeatureConfiguration.e);
        hashmap.put(GeneratorSettingsFlat.D, WorldGenFeatureConfiguration.e);
        hashmap.put(GeneratorSettingsFlat.L, new WorldGenFeatureOceanRuinConfiguration(WorldGenFeatureOceanRuin.Temperature.COLD, 0.3F, 0.9F));
        hashmap.put(GeneratorSettingsFlat.E, new WorldGenFeatureShipwreckConfiguration(false));
        hashmap.put(GeneratorSettingsFlat.F, WorldGenFeatureConfiguration.e);
        hashmap.put(GeneratorSettingsFlat.I, WorldGenFeatureConfiguration.e);
        hashmap.put(GeneratorSettingsFlat.J, WorldGenFeatureConfiguration.e);
        hashmap.put(GeneratorSettingsFlat.K, WorldGenFeatureConfiguration.e);
        hashmap.put(GeneratorSettingsFlat.M, new WorldGenFeaturePillagerOutpostConfiguration(0.004D));
    });
    private final List<WorldGenFlatLayerInfo> N = Lists.newArrayList();
    private final Map<String, Map<String, String>> O = Maps.newHashMap();
    private BiomeBase P;
    private final IBlockData[] Q = new IBlockData[256];
    private boolean R;
    private int S;

    public GeneratorSettingsFlat() {}

    @Nullable
    public static Block a(String s) {
        try {
            MinecraftKey minecraftkey = new MinecraftKey(s);

            return (Block) IRegistry.BLOCK.getOptional(minecraftkey).orElse((Object) null);
        } catch (IllegalArgumentException illegalargumentexception) {
            GeneratorSettingsFlat.LOGGER.warn("Invalid blockstate: {}", s, illegalargumentexception);
            return null;
        }
    }

    public BiomeBase v() {
        return this.P;
    }

    public void a(BiomeBase biomebase) {
        this.P = biomebase;
    }

    public Map<String, Map<String, String>> w() {
        return this.O;
    }

    public List<WorldGenFlatLayerInfo> x() {
        return this.N;
    }

    public void y() {
        int i = 0;

        WorldGenFlatLayerInfo worldgenflatlayerinfo;
        Iterator iterator;

        for (iterator = this.N.iterator(); iterator.hasNext(); i += worldgenflatlayerinfo.a()) {
            worldgenflatlayerinfo = (WorldGenFlatLayerInfo) iterator.next();
            worldgenflatlayerinfo.a(i);
        }

        this.S = 0;
        this.R = true;
        i = 0;
        iterator = this.N.iterator();

        while (iterator.hasNext()) {
            worldgenflatlayerinfo = (WorldGenFlatLayerInfo) iterator.next();

            for (int j = worldgenflatlayerinfo.c(); j < worldgenflatlayerinfo.c() + worldgenflatlayerinfo.a(); ++j) {
                IBlockData iblockdata = worldgenflatlayerinfo.b();

                if (iblockdata.getBlock() != Blocks.AIR) {
                    this.R = false;
                    this.Q[j] = iblockdata;
                }
            }

            if (worldgenflatlayerinfo.b().getBlock() == Blocks.AIR) {
                i += worldgenflatlayerinfo.a();
            } else {
                this.S += worldgenflatlayerinfo.a() + i;
                i = 0;
            }
        }

    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();

        int i;

        for (i = 0; i < this.N.size(); ++i) {
            if (i > 0) {
                stringbuilder.append(",");
            }

            stringbuilder.append(this.N.get(i));
        }

        stringbuilder.append(";");
        stringbuilder.append(IRegistry.BIOME.getKey(this.P));
        stringbuilder.append(";");
        if (!this.O.isEmpty()) {
            i = 0;
            Iterator iterator = this.O.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<String, Map<String, String>> entry = (Entry) iterator.next();

                if (i++ > 0) {
                    stringbuilder.append(",");
                }

                stringbuilder.append(((String) entry.getKey()).toLowerCase(Locale.ROOT));
                Map<String, String> map = (Map) entry.getValue();

                if (!map.isEmpty()) {
                    stringbuilder.append("(");
                    int j = 0;
                    Iterator iterator1 = map.entrySet().iterator();

                    while (iterator1.hasNext()) {
                        Entry<String, String> entry1 = (Entry) iterator1.next();

                        if (j++ > 0) {
                            stringbuilder.append(" ");
                        }

                        stringbuilder.append((String) entry1.getKey());
                        stringbuilder.append("=");
                        stringbuilder.append((String) entry1.getValue());
                    }

                    stringbuilder.append(")");
                }
            }
        }

        return stringbuilder.toString();
    }

    public static GeneratorSettingsFlat a(Dynamic<?> dynamic) {
        GeneratorSettingsFlat generatorsettingsflat = (GeneratorSettingsFlat) ChunkGeneratorType.e.a();
        List<Pair<Integer, Block>> list = dynamic.get("layers").asList((dynamic1) -> {
            return Pair.of(dynamic1.get("height").asInt(1), a(dynamic1.get("block").asString("")));
        });

        if (list.stream().anyMatch((pair) -> {
            return pair.getSecond() == null;
        })) {
            return z();
        } else {
            List<WorldGenFlatLayerInfo> list1 = (List) list.stream().map((pair) -> {
                return new WorldGenFlatLayerInfo((Integer) pair.getFirst(), (Block) pair.getSecond());
            }).collect(Collectors.toList());

            if (list1.isEmpty()) {
                return z();
            } else {
                generatorsettingsflat.x().addAll(list1);
                generatorsettingsflat.y();
                generatorsettingsflat.a((BiomeBase) IRegistry.BIOME.get(new MinecraftKey(dynamic.get("biome").asString(""))));
                dynamic.get("structures").flatMap(Dynamic::getMapValues).ifPresent((map) -> {
                    map.keySet().forEach((dynamic1) -> {
                        dynamic1.asString().map((s) -> {
                            return (Map) generatorsettingsflat.w().put(s, Maps.newHashMap());
                        });
                    });
                });
                return generatorsettingsflat;
            }
        }
    }

    public static GeneratorSettingsFlat z() {
        GeneratorSettingsFlat generatorsettingsflat = (GeneratorSettingsFlat) ChunkGeneratorType.e.a();

        generatorsettingsflat.a(Biomes.PLAINS);
        generatorsettingsflat.x().add(new WorldGenFlatLayerInfo(1, Blocks.BEDROCK));
        generatorsettingsflat.x().add(new WorldGenFlatLayerInfo(2, Blocks.DIRT));
        generatorsettingsflat.x().add(new WorldGenFlatLayerInfo(1, Blocks.GRASS_BLOCK));
        generatorsettingsflat.y();
        generatorsettingsflat.w().put("village", Maps.newHashMap());
        return generatorsettingsflat;
    }

    public boolean A() {
        return this.R;
    }

    public IBlockData[] C() {
        return this.Q;
    }

    public void a(int i) {
        this.Q[i] = null;
    }
}

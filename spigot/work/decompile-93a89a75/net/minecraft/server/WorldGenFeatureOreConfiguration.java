package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorldGenFeatureOreConfiguration implements WorldGenFeatureConfiguration {

    public final WorldGenFeatureOreConfiguration.Target a;
    public final int b;
    public final IBlockData c;

    public WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target worldgenfeatureoreconfiguration_target, IBlockData iblockdata, int i) {
        this.b = i;
        this.c = iblockdata;
        this.a = worldgenfeatureoreconfiguration_target;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("size"), dynamicops.createInt(this.b), dynamicops.createString("target"), dynamicops.createString(this.a.a()), dynamicops.createString("state"), IBlockData.a(dynamicops, this.c).getValue())));
    }

    public static WorldGenFeatureOreConfiguration a(Dynamic<?> dynamic) {
        int i = dynamic.get("size").asInt(0);
        WorldGenFeatureOreConfiguration.Target worldgenfeatureoreconfiguration_target = WorldGenFeatureOreConfiguration.Target.a(dynamic.get("target").asString(""));
        IBlockData iblockdata = (IBlockData) dynamic.get("state").map(IBlockData::a).orElse(Blocks.AIR.getBlockData());

        return new WorldGenFeatureOreConfiguration(worldgenfeatureoreconfiguration_target, iblockdata, i);
    }

    public static enum Target {

        NATURAL_STONE("natural_stone", (iblockdata) -> {
            if (iblockdata == null) {
                return false;
            } else {
                Block block = iblockdata.getBlock();

                return block == Blocks.STONE || block == Blocks.GRANITE || block == Blocks.DIORITE || block == Blocks.ANDESITE;
            }
        }), NETHERRACK("netherrack", new BlockPredicate(Blocks.NETHERRACK));

        private static final Map<String, WorldGenFeatureOreConfiguration.Target> c = (Map) Arrays.stream(values()).collect(Collectors.toMap(WorldGenFeatureOreConfiguration.Target::a, (worldgenfeatureoreconfiguration_target) -> {
            return worldgenfeatureoreconfiguration_target;
        }));
        private final String d;
        private final Predicate<IBlockData> e;

        private Target(String s, Predicate predicate) {
            this.d = s;
            this.e = predicate;
        }

        public String a() {
            return this.d;
        }

        public static WorldGenFeatureOreConfiguration.Target a(String s) {
            return (WorldGenFeatureOreConfiguration.Target) WorldGenFeatureOreConfiguration.Target.c.get(s);
        }

        public Predicate<IBlockData> b() {
            return this.e;
        }
    }
}

package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureFillConfiguration implements WorldGenFeatureConfiguration {

    public final int a;
    public final IBlockData b;

    public WorldGenFeatureFillConfiguration(int i, IBlockData iblockdata) {
        this.a = i;
        this.b = iblockdata;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("height"), dynamicops.createInt(this.a), dynamicops.createString("state"), IBlockData.a(dynamicops, this.b).getValue())));
    }

    public static <T> WorldGenFeatureFillConfiguration a(Dynamic<T> dynamic) {
        int i = dynamic.get("height").asInt(0);
        IBlockData iblockdata = (IBlockData) dynamic.get("state").map(IBlockData::a).orElse(Blocks.AIR.getBlockData());

        return new WorldGenFeatureFillConfiguration(i, iblockdata);
    }
}

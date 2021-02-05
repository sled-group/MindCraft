package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureBlockOffsetConfiguration implements WorldGenFeatureConfiguration {

    public final IBlockData a;
    public final int b;

    public WorldGenFeatureBlockOffsetConfiguration(IBlockData iblockdata, int i) {
        this.a = iblockdata;
        this.b = i;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("state"), IBlockData.a(dynamicops, this.a).getValue(), dynamicops.createString("start_radius"), dynamicops.createInt(this.b))));
    }

    public static <T> WorldGenFeatureBlockOffsetConfiguration a(Dynamic<T> dynamic) {
        IBlockData iblockdata = (IBlockData) dynamic.get("state").map(IBlockData::a).orElse(Blocks.AIR.getBlockData());
        int i = dynamic.get("start_radius").asInt(0);

        return new WorldGenFeatureBlockOffsetConfiguration(iblockdata, i);
    }
}

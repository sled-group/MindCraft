package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureReplaceBlockConfiguration implements WorldGenFeatureConfiguration {

    public final IBlockData a;
    public final IBlockData b;

    public WorldGenFeatureReplaceBlockConfiguration(IBlockData iblockdata, IBlockData iblockdata1) {
        this.a = iblockdata;
        this.b = iblockdata1;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("target"), IBlockData.a(dynamicops, this.a).getValue(), dynamicops.createString("state"), IBlockData.a(dynamicops, this.b).getValue())));
    }

    public static <T> WorldGenFeatureReplaceBlockConfiguration a(Dynamic<T> dynamic) {
        IBlockData iblockdata = (IBlockData) dynamic.get("target").map(IBlockData::a).orElse(Blocks.AIR.getBlockData());
        IBlockData iblockdata1 = (IBlockData) dynamic.get("state").map(IBlockData::a).orElse(Blocks.AIR.getBlockData());

        return new WorldGenFeatureReplaceBlockConfiguration(iblockdata, iblockdata1);
    }
}

package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.List;

public class WorldGenFeatureCircleConfiguration implements WorldGenFeatureConfiguration {

    public final IBlockData a;
    public final int b;
    public final int c;
    public final List<IBlockData> d;

    public WorldGenFeatureCircleConfiguration(IBlockData iblockdata, int i, int j, List<IBlockData> list) {
        this.a = iblockdata;
        this.b = i;
        this.c = j;
        this.d = list;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("state"), IBlockData.a(dynamicops, this.a).getValue(), dynamicops.createString("radius"), dynamicops.createInt(this.b), dynamicops.createString("y_size"), dynamicops.createInt(this.c), dynamicops.createString("targets"), dynamicops.createList(this.d.stream().map((iblockdata) -> {
            return IBlockData.a(dynamicops, iblockdata).getValue();
        })))));
    }

    public static <T> WorldGenFeatureCircleConfiguration a(Dynamic<T> dynamic) {
        IBlockData iblockdata = (IBlockData) dynamic.get("state").map(IBlockData::a).orElse(Blocks.AIR.getBlockData());
        int i = dynamic.get("radius").asInt(0);
        int j = dynamic.get("y_size").asInt(0);
        List<IBlockData> list = dynamic.get("targets").asList(IBlockData::a);

        return new WorldGenFeatureCircleConfiguration(iblockdata, i, j, list);
    }
}

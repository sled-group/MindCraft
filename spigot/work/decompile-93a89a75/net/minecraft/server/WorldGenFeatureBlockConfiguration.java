package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.List;

public class WorldGenFeatureBlockConfiguration implements WorldGenFeatureConfiguration {

    protected final IBlockData a;
    protected final List<IBlockData> b;
    protected final List<IBlockData> c;
    protected final List<IBlockData> d;

    public WorldGenFeatureBlockConfiguration(IBlockData iblockdata, List<IBlockData> list, List<IBlockData> list1, List<IBlockData> list2) {
        this.a = iblockdata;
        this.b = list;
        this.c = list1;
        this.d = list2;
    }

    public WorldGenFeatureBlockConfiguration(IBlockData iblockdata, IBlockData[] aiblockdata, IBlockData[] aiblockdata1, IBlockData[] aiblockdata2) {
        this(iblockdata, (List) Lists.newArrayList(aiblockdata), (List) Lists.newArrayList(aiblockdata1), (List) Lists.newArrayList(aiblockdata2));
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        T t0 = IBlockData.a(dynamicops, this.a).getValue();
        T t1 = dynamicops.createList(this.b.stream().map((iblockdata) -> {
            return IBlockData.a(dynamicops, iblockdata).getValue();
        }));
        T t2 = dynamicops.createList(this.c.stream().map((iblockdata) -> {
            return IBlockData.a(dynamicops, iblockdata).getValue();
        }));
        T t3 = dynamicops.createList(this.d.stream().map((iblockdata) -> {
            return IBlockData.a(dynamicops, iblockdata).getValue();
        }));

        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("to_place"), t0, dynamicops.createString("place_on"), t1, dynamicops.createString("place_in"), t2, dynamicops.createString("place_under"), t3)));
    }

    public static <T> WorldGenFeatureBlockConfiguration a(Dynamic<T> dynamic) {
        IBlockData iblockdata = (IBlockData) dynamic.get("to_place").map(IBlockData::a).orElse(Blocks.AIR.getBlockData());
        List<IBlockData> list = dynamic.get("place_on").asList(IBlockData::a);
        List<IBlockData> list1 = dynamic.get("place_in").asList(IBlockData::a);
        List<IBlockData> list2 = dynamic.get("place_under").asList(IBlockData::a);

        return new WorldGenFeatureBlockConfiguration(iblockdata, list, list1, list2);
    }
}

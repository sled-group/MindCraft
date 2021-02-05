package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureHellFlowingLavaConfiguration implements WorldGenFeatureConfiguration {

    public final boolean a;

    public WorldGenFeatureHellFlowingLavaConfiguration(boolean flag) {
        this.a = flag;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("inside_rock"), dynamicops.createBoolean(this.a))));
    }

    public static <T> WorldGenFeatureHellFlowingLavaConfiguration a(Dynamic<T> dynamic) {
        boolean flag = dynamic.get("inside_rock").asBoolean(false);

        return new WorldGenFeatureHellFlowingLavaConfiguration(flag);
    }
}

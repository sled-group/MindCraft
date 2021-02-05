package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenHugeMushroomConfiguration implements WorldGenFeatureConfiguration {

    public final boolean a;

    public WorldGenHugeMushroomConfiguration(boolean flag) {
        this.a = flag;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("planted"), dynamicops.createBoolean(this.a))));
    }

    public static <T> WorldGenHugeMushroomConfiguration a(Dynamic<T> dynamic) {
        boolean flag = dynamic.get("planted").asBoolean(false);

        return new WorldGenHugeMushroomConfiguration(flag);
    }
}

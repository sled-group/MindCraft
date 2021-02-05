package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureShipwreckConfiguration implements WorldGenFeatureConfiguration {

    public final boolean a;

    public WorldGenFeatureShipwreckConfiguration(boolean flag) {
        this.a = flag;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("is_beached"), dynamicops.createBoolean(this.a))));
    }

    public static <T> WorldGenFeatureShipwreckConfiguration a(Dynamic<T> dynamic) {
        boolean flag = dynamic.get("is_beached").asBoolean(false);

        return new WorldGenFeatureShipwreckConfiguration(flag);
    }
}

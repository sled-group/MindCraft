package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenDecoratorLakeChanceConfiguration implements WorldGenFeatureDecoratorConfiguration {

    public final int a;

    public WorldGenDecoratorLakeChanceConfiguration(int i) {
        this.a = i;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("chance"), dynamicops.createInt(this.a))));
    }

    public static WorldGenDecoratorLakeChanceConfiguration a(Dynamic<?> dynamic) {
        int i = dynamic.get("chance").asInt(0);

        return new WorldGenDecoratorLakeChanceConfiguration(i);
    }
}

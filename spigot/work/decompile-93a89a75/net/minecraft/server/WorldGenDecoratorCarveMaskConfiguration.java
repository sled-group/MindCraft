package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenDecoratorCarveMaskConfiguration implements WorldGenFeatureDecoratorConfiguration {

    protected final WorldGenStage.Features a;
    protected final float b;

    public WorldGenDecoratorCarveMaskConfiguration(WorldGenStage.Features worldgenstage_features, float f) {
        this.a = worldgenstage_features;
        this.b = f;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("step"), dynamicops.createString(this.a.toString()), dynamicops.createString("probability"), dynamicops.createFloat(this.b))));
    }

    public static WorldGenDecoratorCarveMaskConfiguration a(Dynamic<?> dynamic) {
        WorldGenStage.Features worldgenstage_features = WorldGenStage.Features.valueOf(dynamic.get("step").asString(""));
        float f = dynamic.get("probability").asFloat(0.0F);

        return new WorldGenDecoratorCarveMaskConfiguration(worldgenstage_features, f);
    }
}

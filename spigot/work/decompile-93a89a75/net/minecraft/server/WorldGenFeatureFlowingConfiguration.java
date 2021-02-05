package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureFlowingConfiguration implements WorldGenFeatureConfiguration {

    public final Fluid a;

    public WorldGenFeatureFlowingConfiguration(Fluid fluid) {
        this.a = fluid;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("state"), Fluid.a(dynamicops, this.a).getValue())));
    }

    public static <T> WorldGenFeatureFlowingConfiguration a(Dynamic<T> dynamic) {
        Fluid fluid = (Fluid) dynamic.get("state").map(Fluid::a).orElse(FluidTypes.EMPTY.i());

        return new WorldGenFeatureFlowingConfiguration(fluid);
    }
}

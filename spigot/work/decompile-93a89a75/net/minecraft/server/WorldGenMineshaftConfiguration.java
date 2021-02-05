package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenMineshaftConfiguration implements WorldGenFeatureConfiguration {

    public final double a;
    public final WorldGenMineshaft.Type b;

    public WorldGenMineshaftConfiguration(double d0, WorldGenMineshaft.Type worldgenmineshaft_type) {
        this.a = d0;
        this.b = worldgenmineshaft_type;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("probability"), dynamicops.createDouble(this.a), dynamicops.createString("type"), dynamicops.createString(this.b.a()))));
    }

    public static <T> WorldGenMineshaftConfiguration a(Dynamic<T> dynamic) {
        float f = dynamic.get("probability").asFloat(0.0F);
        WorldGenMineshaft.Type worldgenmineshaft_type = WorldGenMineshaft.Type.a(dynamic.get("type").asString(""));

        return new WorldGenMineshaftConfiguration((double) f, worldgenmineshaft_type);
    }
}

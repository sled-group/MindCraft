package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenDecoratorNoiseConfiguration implements WorldGenFeatureDecoratorConfiguration {

    public final int a;
    public final double b;
    public final double c;
    public final HeightMap.Type d;

    public WorldGenDecoratorNoiseConfiguration(int i, double d0, double d1, HeightMap.Type heightmap_type) {
        this.a = i;
        this.b = d0;
        this.c = d1;
        this.d = heightmap_type;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("noise_to_count_ratio"), dynamicops.createInt(this.a), dynamicops.createString("noise_factor"), dynamicops.createDouble(this.b), dynamicops.createString("noise_offset"), dynamicops.createDouble(this.c), dynamicops.createString("heightmap"), dynamicops.createString(this.d.a()))));
    }

    public static WorldGenDecoratorNoiseConfiguration a(Dynamic<?> dynamic) {
        int i = dynamic.get("noise_to_count_ratio").asInt(10);
        double d0 = dynamic.get("noise_factor").asDouble(80.0D);
        double d1 = dynamic.get("noise_offset").asDouble(0.0D);
        HeightMap.Type heightmap_type = HeightMap.Type.a(dynamic.get("heightmap").asString("OCEAN_FLOOR_WG"));

        return new WorldGenDecoratorNoiseConfiguration(i, d0, d1, heightmap_type);
    }
}

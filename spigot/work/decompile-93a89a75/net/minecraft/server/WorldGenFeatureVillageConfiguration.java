package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureVillageConfiguration implements WorldGenFeatureConfiguration {

    public final MinecraftKey a;
    public final int b;

    public WorldGenFeatureVillageConfiguration(String s, int i) {
        this.a = new MinecraftKey(s);
        this.b = i;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("start_pool"), dynamicops.createString(this.a.toString()), dynamicops.createString("size"), dynamicops.createInt(this.b))));
    }

    public static <T> WorldGenFeatureVillageConfiguration a(Dynamic<T> dynamic) {
        String s = dynamic.get("start_pool").asString("");
        int i = dynamic.get("size").asInt(6);

        return new WorldGenFeatureVillageConfiguration(s, i);
    }
}

package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public interface WorldGenFeatureConfiguration {

    WorldGenFeatureEmptyConfiguration e = new WorldGenFeatureEmptyConfiguration();

    <T> Dynamic<T> a(DynamicOps<T> dynamicops);
}

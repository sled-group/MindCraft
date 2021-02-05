package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public interface WorldGenFeatureDecoratorConfiguration {

    WorldGenFeatureDecoratorEmptyConfiguration e = new WorldGenFeatureDecoratorEmptyConfiguration();

    <T> Dynamic<T> a(DynamicOps<T> dynamicops);
}

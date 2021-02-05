package net.minecraft.server;

import com.mojang.datafixers.types.DynamicOps;

public interface MinecraftSerializable {

    <T> T a(DynamicOps<T> dynamicops);
}

package net.minecraft.server;

import java.util.Map;

public interface ResourcePackSource {

    <T extends ResourcePackLoader> void a(Map<String, T> map, ResourcePackLoader.b<T> resourcepackloader_b);
}

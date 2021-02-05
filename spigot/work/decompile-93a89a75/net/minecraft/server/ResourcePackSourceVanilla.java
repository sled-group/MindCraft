package net.minecraft.server;

import java.util.Map;

public class ResourcePackSourceVanilla implements ResourcePackSource {

    private final ResourcePackVanilla a = new ResourcePackVanilla(new String[]{"minecraft"});

    public ResourcePackSourceVanilla() {}

    @Override
    public <T extends ResourcePackLoader> void a(Map<String, T> map, ResourcePackLoader.b<T> resourcepackloader_b) {
        T t0 = ResourcePackLoader.a("vanilla", false, () -> {
            return this.a;
        }, resourcepackloader_b, ResourcePackLoader.Position.BOTTOM);

        if (t0 != null) {
            map.put("vanilla", t0);
        }

    }
}

package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public class WorldGenFeatureDefinedStructurePoolTemplates {

    private final Map<MinecraftKey, WorldGenFeatureDefinedStructurePoolTemplate> a = Maps.newHashMap();

    public WorldGenFeatureDefinedStructurePoolTemplates() {
        this.a(WorldGenFeatureDefinedStructurePoolTemplate.a);
    }

    public void a(WorldGenFeatureDefinedStructurePoolTemplate worldgenfeaturedefinedstructurepooltemplate) {
        this.a.put(worldgenfeaturedefinedstructurepooltemplate.b(), worldgenfeaturedefinedstructurepooltemplate);
    }

    public WorldGenFeatureDefinedStructurePoolTemplate a(MinecraftKey minecraftkey) {
        WorldGenFeatureDefinedStructurePoolTemplate worldgenfeaturedefinedstructurepooltemplate = (WorldGenFeatureDefinedStructurePoolTemplate) this.a.get(minecraftkey);

        return worldgenfeaturedefinedstructurepooltemplate != null ? worldgenfeaturedefinedstructurepooltemplate : WorldGenFeatureDefinedStructurePoolTemplate.b;
    }
}

package net.minecraft.server;

import com.mojang.datafixers.Dynamic;

public interface WorldGenFeatureDefinedStructurePools extends DynamicDeserializer<WorldGenFeatureDefinedStructurePoolStructure> {

    WorldGenFeatureDefinedStructurePools b = a("single_pool_element", WorldGenFeatureDefinedStructurePoolSingle::new);
    WorldGenFeatureDefinedStructurePools c = a("list_pool_element", WorldGenFeatureDefinedStructurePoolList::new);
    WorldGenFeatureDefinedStructurePools d = a("feature_pool_element", WorldGenFeatureDefinedStructurePoolFeature::new);
    WorldGenFeatureDefinedStructurePools e = a("empty_pool_element", (dynamic) -> {
        return WorldGenFeatureDefinedStructurePoolEmpty.a;
    });

    static WorldGenFeatureDefinedStructurePools a(String s, WorldGenFeatureDefinedStructurePools worldgenfeaturedefinedstructurepools) {
        return (WorldGenFeatureDefinedStructurePools) IRegistry.a(IRegistry.STRUCTURE_POOL_ELEMENT, s, (Object) worldgenfeaturedefinedstructurepools);
    }
}

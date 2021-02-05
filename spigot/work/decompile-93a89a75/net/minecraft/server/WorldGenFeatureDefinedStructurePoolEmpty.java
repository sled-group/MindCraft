package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WorldGenFeatureDefinedStructurePoolEmpty extends WorldGenFeatureDefinedStructurePoolStructure {

    public static final WorldGenFeatureDefinedStructurePoolEmpty a = new WorldGenFeatureDefinedStructurePoolEmpty();

    private WorldGenFeatureDefinedStructurePoolEmpty() {
        super(WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING);
    }

    @Override
    public List<DefinedStructure.BlockInfo> a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation, Random random) {
        return Collections.emptyList();
    }

    @Override
    public StructureBoundingBox a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation) {
        return StructureBoundingBox.a();
    }

    @Override
    public boolean a(DefinedStructureManager definedstructuremanager, GeneratorAccess generatoraccess, BlockPosition blockposition, EnumBlockRotation enumblockrotation, StructureBoundingBox structureboundingbox, Random random) {
        return true;
    }

    @Override
    public WorldGenFeatureDefinedStructurePools a() {
        return WorldGenFeatureDefinedStructurePools.e;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.emptyMap());
    }

    public String toString() {
        return "Empty";
    }
}

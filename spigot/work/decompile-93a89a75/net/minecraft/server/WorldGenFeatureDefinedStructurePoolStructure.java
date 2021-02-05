package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

public abstract class WorldGenFeatureDefinedStructurePoolStructure {

    @Nullable
    private volatile WorldGenFeatureDefinedStructurePoolTemplate.Matching a;

    protected WorldGenFeatureDefinedStructurePoolStructure(WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        this.a = worldgenfeaturedefinedstructurepooltemplate_matching;
    }

    protected WorldGenFeatureDefinedStructurePoolStructure(Dynamic<?> dynamic) {
        this.a = WorldGenFeatureDefinedStructurePoolTemplate.Matching.a(dynamic.get("projection").asString(WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID.a()));
    }

    public abstract List<DefinedStructure.BlockInfo> a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation, Random random);

    public abstract StructureBoundingBox a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation);

    public abstract boolean a(DefinedStructureManager definedstructuremanager, GeneratorAccess generatoraccess, BlockPosition blockposition, EnumBlockRotation enumblockrotation, StructureBoundingBox structureboundingbox, Random random);

    public abstract WorldGenFeatureDefinedStructurePools a();

    public void a(GeneratorAccess generatoraccess, DefinedStructure.BlockInfo definedstructure_blockinfo, BlockPosition blockposition, EnumBlockRotation enumblockrotation, Random random, StructureBoundingBox structureboundingbox) {}

    public WorldGenFeatureDefinedStructurePoolStructure a(WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        this.a = worldgenfeaturedefinedstructurepooltemplate_matching;
        return this;
    }

    public WorldGenFeatureDefinedStructurePoolTemplate.Matching c() {
        WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching = this.a;

        if (worldgenfeaturedefinedstructurepooltemplate_matching == null) {
            throw new IllegalStateException();
        } else {
            return worldgenfeaturedefinedstructurepooltemplate_matching;
        }
    }

    protected abstract <T> Dynamic<T> a(DynamicOps<T> dynamicops);

    public <T> Dynamic<T> b(DynamicOps<T> dynamicops) {
        T t0 = this.a(dynamicops).getValue();
        T t1 = dynamicops.mergeInto(t0, dynamicops.createString("element_type"), dynamicops.createString(IRegistry.STRUCTURE_POOL_ELEMENT.getKey(this.a()).toString()));

        return new Dynamic(dynamicops, dynamicops.mergeInto(t1, dynamicops.createString("projection"), dynamicops.createString(this.a.a())));
    }

    public int d() {
        return 1;
    }
}

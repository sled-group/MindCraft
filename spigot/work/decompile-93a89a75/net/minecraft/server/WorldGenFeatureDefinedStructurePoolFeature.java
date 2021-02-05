package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.List;
import java.util.Random;

public class WorldGenFeatureDefinedStructurePoolFeature extends WorldGenFeatureDefinedStructurePoolStructure {

    private final WorldGenFeatureConfigured<?> a;
    private final NBTTagCompound b;

    @Deprecated
    public WorldGenFeatureDefinedStructurePoolFeature(WorldGenFeatureConfigured<?> worldgenfeatureconfigured) {
        this(worldgenfeatureconfigured, WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID);
    }

    public WorldGenFeatureDefinedStructurePoolFeature(WorldGenFeatureConfigured<?> worldgenfeatureconfigured, WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        super(worldgenfeaturedefinedstructurepooltemplate_matching);
        this.a = worldgenfeatureconfigured;
        this.b = this.b();
    }

    public <T> WorldGenFeatureDefinedStructurePoolFeature(Dynamic<T> dynamic) {
        super(dynamic);
        this.a = WorldGenFeatureConfigured.a(dynamic.get("feature").orElseEmptyMap());
        this.b = this.b();
    }

    public NBTTagCompound b() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.setString("target_pool", "minecraft:empty");
        nbttagcompound.setString("attachement_type", "minecraft:bottom");
        nbttagcompound.setString("final_state", "minecraft:air");
        return nbttagcompound;
    }

    public BlockPosition a(DefinedStructureManager definedstructuremanager, EnumBlockRotation enumblockrotation) {
        return BlockPosition.ZERO;
    }

    @Override
    public List<DefinedStructure.BlockInfo> a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation, Random random) {
        List<DefinedStructure.BlockInfo> list = Lists.newArrayList();

        list.add(new DefinedStructure.BlockInfo(blockposition, (IBlockData) Blocks.JIGSAW.getBlockData().set(BlockJigsaw.FACING, EnumDirection.DOWN), this.b));
        return list;
    }

    @Override
    public StructureBoundingBox a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation) {
        BlockPosition blockposition1 = this.a(definedstructuremanager, enumblockrotation);

        return new StructureBoundingBox(blockposition.getX(), blockposition.getY(), blockposition.getZ(), blockposition.getX() + blockposition1.getX(), blockposition.getY() + blockposition1.getY(), blockposition.getZ() + blockposition1.getZ());
    }

    @Override
    public boolean a(DefinedStructureManager definedstructuremanager, GeneratorAccess generatoraccess, BlockPosition blockposition, EnumBlockRotation enumblockrotation, StructureBoundingBox structureboundingbox, Random random) {
        ChunkGenerator<?> chunkgenerator = generatoraccess.getChunkProvider().getChunkGenerator();

        return this.a.a(generatoraccess, chunkgenerator, random, blockposition);
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("feature"), this.a.a(dynamicops).getValue())));
    }

    @Override
    public WorldGenFeatureDefinedStructurePools a() {
        return WorldGenFeatureDefinedStructurePools.d;
    }

    public String toString() {
        return "Feature[" + IRegistry.FEATURE.getKey(this.a.a) + "]";
    }
}

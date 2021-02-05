package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.util.List;

public class WorldGenFeaturePillagerOutpostPieces {

    public static void a(ChunkGenerator<?> chunkgenerator, DefinedStructureManager definedstructuremanager, BlockPosition blockposition, List<StructurePiece> list, SeededRandom seededrandom) {
        WorldGenFeatureDefinedStructureJigsawPlacement.a(new MinecraftKey("pillager_outpost/base_plates"), 7, WorldGenFeaturePillagerOutpostPieces.a::new, chunkgenerator, definedstructuremanager, blockposition, list, seededrandom);
    }

    static {
        WorldGenFeatureDefinedStructureJigsawPlacement.a.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("pillager_outpost/base_plates"), new MinecraftKey("empty"), ImmutableList.of(Pair.of(new WorldGenFeatureDefinedStructurePoolSingle("pillager_outpost/base_plate"), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeatureDefinedStructureJigsawPlacement.a.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("pillager_outpost/towers"), new MinecraftKey("empty"), ImmutableList.of(Pair.of(new WorldGenFeatureDefinedStructurePoolList(ImmutableList.of(new WorldGenFeatureDefinedStructurePoolSingle("pillager_outpost/watchtower"), new WorldGenFeatureDefinedStructurePoolSingle("pillager_outpost/watchtower_overgrown", ImmutableList.of(new DefinedStructureProcessorRotation(0.05F))))), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeatureDefinedStructureJigsawPlacement.a.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("pillager_outpost/feature_plates"), new MinecraftKey("empty"), ImmutableList.of(Pair.of(new WorldGenFeatureDefinedStructurePoolSingle("pillager_outpost/feature_plate"), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
        WorldGenFeatureDefinedStructureJigsawPlacement.a.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("pillager_outpost/features"), new MinecraftKey("empty"), ImmutableList.of(Pair.of(new WorldGenFeatureDefinedStructurePoolSingle("pillager_outpost/feature_cage1"), 1), Pair.of(new WorldGenFeatureDefinedStructurePoolSingle("pillager_outpost/feature_cage2"), 1), Pair.of(new WorldGenFeatureDefinedStructurePoolSingle("pillager_outpost/feature_logs"), 1), Pair.of(new WorldGenFeatureDefinedStructurePoolSingle("pillager_outpost/feature_tent1"), 1), Pair.of(new WorldGenFeatureDefinedStructurePoolSingle("pillager_outpost/feature_tent2"), 1), Pair.of(new WorldGenFeatureDefinedStructurePoolSingle("pillager_outpost/feature_targets"), 1), Pair.of(WorldGenFeatureDefinedStructurePoolEmpty.a, 6)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
    }

    public static class a extends WorldGenFeaturePillagerOutpostPoolPiece {

        public a(DefinedStructureManager definedstructuremanager, WorldGenFeatureDefinedStructurePoolStructure worldgenfeaturedefinedstructurepoolstructure, BlockPosition blockposition, int i, EnumBlockRotation enumblockrotation, StructureBoundingBox structureboundingbox) {
            super(WorldGenFeatureStructurePieceType.e, definedstructuremanager, worldgenfeaturedefinedstructurepoolstructure, blockposition, i, enumblockrotation, structureboundingbox);
        }

        public a(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(definedstructuremanager, nbttagcompound, WorldGenFeatureStructurePieceType.e);
        }
    }
}

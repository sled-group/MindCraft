package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureBamboo extends WorldGenerator<WorldGenFeatureConfigurationChance> {

    private static final IBlockData a = (IBlockData) ((IBlockData) ((IBlockData) Blocks.BAMBOO.getBlockData().set(BlockBamboo.d, 1)).set(BlockBamboo.e, BlockPropertyBambooSize.NONE)).set(BlockBamboo.f, 0);
    private static final IBlockData aS = (IBlockData) ((IBlockData) WorldGenFeatureBamboo.a.set(BlockBamboo.e, BlockPropertyBambooSize.LARGE)).set(BlockBamboo.f, 1);
    private static final IBlockData aT = (IBlockData) WorldGenFeatureBamboo.a.set(BlockBamboo.e, BlockPropertyBambooSize.LARGE);
    private static final IBlockData aU = (IBlockData) WorldGenFeatureBamboo.a.set(BlockBamboo.e, BlockPropertyBambooSize.SMALL);

    public WorldGenFeatureBamboo(Function<Dynamic<?>, ? extends WorldGenFeatureConfigurationChance> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureConfigurationChance worldgenfeatureconfigurationchance) {
        int i = 0;
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(blockposition);
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = new BlockPosition.MutableBlockPosition(blockposition);

        if (generatoraccess.isEmpty(blockposition_mutableblockposition)) {
            if (Blocks.BAMBOO.getBlockData().canPlace(generatoraccess, blockposition_mutableblockposition)) {
                int j = random.nextInt(12) + 5;
                int k;

                if (random.nextFloat() < worldgenfeatureconfigurationchance.b) {
                    k = random.nextInt(4) + 1;

                    for (int l = blockposition.getX() - k; l <= blockposition.getX() + k; ++l) {
                        for (int i1 = blockposition.getZ() - k; i1 <= blockposition.getZ() + k; ++i1) {
                            int j1 = l - blockposition.getX();
                            int k1 = i1 - blockposition.getZ();

                            if (j1 * j1 + k1 * k1 <= k * k) {
                                blockposition_mutableblockposition1.d(l, generatoraccess.a(HeightMap.Type.WORLD_SURFACE, l, i1) - 1, i1);
                                if (generatoraccess.getType(blockposition_mutableblockposition1).getBlock().a(TagsBlock.DIRT_LIKE)) {
                                    generatoraccess.setTypeAndData(blockposition_mutableblockposition1, Blocks.PODZOL.getBlockData(), 2);
                                }
                            }
                        }
                    }
                }

                for (k = 0; k < j && generatoraccess.isEmpty(blockposition_mutableblockposition); ++k) {
                    generatoraccess.setTypeAndData(blockposition_mutableblockposition, WorldGenFeatureBamboo.a, 2);
                    blockposition_mutableblockposition.c(EnumDirection.UP, 1);
                }

                if (blockposition_mutableblockposition.getY() - blockposition.getY() >= 3) {
                    generatoraccess.setTypeAndData(blockposition_mutableblockposition, WorldGenFeatureBamboo.aS, 2);
                    generatoraccess.setTypeAndData(blockposition_mutableblockposition.c(EnumDirection.DOWN, 1), WorldGenFeatureBamboo.aT, 2);
                    generatoraccess.setTypeAndData(blockposition_mutableblockposition.c(EnumDirection.DOWN, 1), WorldGenFeatureBamboo.aU, 2);
                }
            }

            ++i;
        }

        return i > 0;
    }
}

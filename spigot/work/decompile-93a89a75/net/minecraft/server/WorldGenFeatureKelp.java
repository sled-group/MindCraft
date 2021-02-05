package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureKelp extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenFeatureKelp(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        int i = 0;
        int j = generatoraccess.a(HeightMap.Type.OCEAN_FLOOR, blockposition.getX(), blockposition.getZ());
        BlockPosition blockposition1 = new BlockPosition(blockposition.getX(), j, blockposition.getZ());

        if (generatoraccess.getType(blockposition1).getBlock() == Blocks.WATER) {
            IBlockData iblockdata = Blocks.KELP.getBlockData();
            IBlockData iblockdata1 = Blocks.KELP_PLANT.getBlockData();
            int k = 1 + random.nextInt(10);

            for (int l = 0; l <= k; ++l) {
                if (generatoraccess.getType(blockposition1).getBlock() == Blocks.WATER && generatoraccess.getType(blockposition1.up()).getBlock() == Blocks.WATER && iblockdata1.canPlace(generatoraccess, blockposition1)) {
                    if (l == k) {
                        generatoraccess.setTypeAndData(blockposition1, (IBlockData) iblockdata.set(BlockKelp.a, random.nextInt(23)), 2);
                        ++i;
                    } else {
                        generatoraccess.setTypeAndData(blockposition1, iblockdata1, 2);
                    }
                } else if (l > 0) {
                    BlockPosition blockposition2 = blockposition1.down();

                    if (iblockdata.canPlace(generatoraccess, blockposition2) && generatoraccess.getType(blockposition2.down()).getBlock() != Blocks.KELP) {
                        generatoraccess.setTypeAndData(blockposition2, (IBlockData) iblockdata.set(BlockKelp.a, random.nextInt(23)), 2);
                        ++i;
                    }
                    break;
                }

                blockposition1 = blockposition1.up();
            }
        }

        return i > 0;
    }
}

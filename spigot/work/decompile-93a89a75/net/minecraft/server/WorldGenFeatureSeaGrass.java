package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureSeaGrass extends WorldGenerator<WorldGenFeatureSeaGrassConfiguration> {

    public WorldGenFeatureSeaGrass(Function<Dynamic<?>, ? extends WorldGenFeatureSeaGrassConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureSeaGrassConfiguration worldgenfeatureseagrassconfiguration) {
        int i = 0;

        for (int j = 0; j < worldgenfeatureseagrassconfiguration.a; ++j) {
            int k = random.nextInt(8) - random.nextInt(8);
            int l = random.nextInt(8) - random.nextInt(8);
            int i1 = generatoraccess.a(HeightMap.Type.OCEAN_FLOOR, blockposition.getX() + k, blockposition.getZ() + l);
            BlockPosition blockposition1 = new BlockPosition(blockposition.getX() + k, i1, blockposition.getZ() + l);

            if (generatoraccess.getType(blockposition1).getBlock() == Blocks.WATER) {
                boolean flag = random.nextDouble() < worldgenfeatureseagrassconfiguration.b;
                IBlockData iblockdata = flag ? Blocks.TALL_SEAGRASS.getBlockData() : Blocks.SEAGRASS.getBlockData();

                if (iblockdata.canPlace(generatoraccess, blockposition1)) {
                    if (flag) {
                        IBlockData iblockdata1 = (IBlockData) iblockdata.set(BlockTallSeaGrass.c, BlockPropertyDoubleBlockHalf.UPPER);
                        BlockPosition blockposition2 = blockposition1.up();

                        if (generatoraccess.getType(blockposition2).getBlock() == Blocks.WATER) {
                            generatoraccess.setTypeAndData(blockposition1, iblockdata, 2);
                            generatoraccess.setTypeAndData(blockposition2, iblockdata1, 2);
                        }
                    } else {
                        generatoraccess.setTypeAndData(blockposition1, iblockdata, 2);
                    }

                    ++i;
                }
            }
        }

        return i > 0;
    }
}

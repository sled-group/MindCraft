package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureSeaPickel extends WorldGenerator<WorldGenFeatureKelpConfiguration> {

    public WorldGenFeatureSeaPickel(Function<Dynamic<?>, ? extends WorldGenFeatureKelpConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<?> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureKelpConfiguration worldgenfeaturekelpconfiguration) {
        int i = 0;

        for (int j = 0; j < worldgenfeaturekelpconfiguration.a; ++j) {
            int k = random.nextInt(8) - random.nextInt(8);
            int l = random.nextInt(8) - random.nextInt(8);
            int i1 = generatoraccess.a(HeightMap.Type.OCEAN_FLOOR, blockposition.getX() + k, blockposition.getZ() + l);
            BlockPosition blockposition1 = new BlockPosition(blockposition.getX() + k, i1, blockposition.getZ() + l);
            IBlockData iblockdata = (IBlockData) Blocks.SEA_PICKLE.getBlockData().set(BlockSeaPickle.a, random.nextInt(4) + 1);

            if (generatoraccess.getType(blockposition1).getBlock() == Blocks.WATER && iblockdata.canPlace(generatoraccess, blockposition1)) {
                generatoraccess.setTypeAndData(blockposition1, iblockdata, 2);
                ++i;
            }
        }

        return i > 0;
    }
}

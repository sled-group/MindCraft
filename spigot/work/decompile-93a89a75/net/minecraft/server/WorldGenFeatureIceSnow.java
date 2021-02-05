package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureIceSnow extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenFeatureIceSnow(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = new BlockPosition.MutableBlockPosition();

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int k = blockposition.getX() + i;
                int l = blockposition.getZ() + j;
                int i1 = generatoraccess.a(HeightMap.Type.MOTION_BLOCKING, k, l);

                blockposition_mutableblockposition.d(k, i1, l);
                blockposition_mutableblockposition1.g(blockposition_mutableblockposition).c(EnumDirection.DOWN, 1);
                BiomeBase biomebase = generatoraccess.getBiome(blockposition_mutableblockposition);

                if (biomebase.a(generatoraccess, blockposition_mutableblockposition1, false)) {
                    generatoraccess.setTypeAndData(blockposition_mutableblockposition1, Blocks.ICE.getBlockData(), 2);
                }

                if (biomebase.b(generatoraccess, blockposition_mutableblockposition)) {
                    generatoraccess.setTypeAndData(blockposition_mutableblockposition, Blocks.SNOW.getBlockData(), 2);
                    IBlockData iblockdata = generatoraccess.getType(blockposition_mutableblockposition1);

                    if (iblockdata.b((IBlockState) BlockDirtSnow.a)) {
                        generatoraccess.setTypeAndData(blockposition_mutableblockposition1, (IBlockData) iblockdata.set(BlockDirtSnow.a, true), 2);
                    }
                }
            }
        }

        return true;
    }
}

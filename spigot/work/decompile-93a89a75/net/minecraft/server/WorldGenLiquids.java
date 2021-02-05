package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenLiquids extends WorldGenerator<WorldGenFeatureFlowingConfiguration> {

    public WorldGenLiquids(Function<Dynamic<?>, ? extends WorldGenFeatureFlowingConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureFlowingConfiguration worldgenfeatureflowingconfiguration) {
        if (!Block.b(generatoraccess.getType(blockposition.up()).getBlock())) {
            return false;
        } else if (!Block.b(generatoraccess.getType(blockposition.down()).getBlock())) {
            return false;
        } else {
            IBlockData iblockdata = generatoraccess.getType(blockposition);

            if (!iblockdata.isAir() && !Block.b(iblockdata.getBlock())) {
                return false;
            } else {
                int i = 0;
                int j = 0;

                if (Block.b(generatoraccess.getType(blockposition.west()).getBlock())) {
                    ++j;
                }

                if (Block.b(generatoraccess.getType(blockposition.east()).getBlock())) {
                    ++j;
                }

                if (Block.b(generatoraccess.getType(blockposition.north()).getBlock())) {
                    ++j;
                }

                if (Block.b(generatoraccess.getType(blockposition.south()).getBlock())) {
                    ++j;
                }

                int k = 0;

                if (generatoraccess.isEmpty(blockposition.west())) {
                    ++k;
                }

                if (generatoraccess.isEmpty(blockposition.east())) {
                    ++k;
                }

                if (generatoraccess.isEmpty(blockposition.north())) {
                    ++k;
                }

                if (generatoraccess.isEmpty(blockposition.south())) {
                    ++k;
                }

                if (j == 3 && k == 1) {
                    generatoraccess.setTypeAndData(blockposition, worldgenfeatureflowingconfiguration.a.getBlockData(), 2);
                    generatoraccess.getFluidTickList().a(blockposition, worldgenfeatureflowingconfiguration.a.getType(), 0);
                    ++i;
                }

                return i > 0;
            }
        }
    }
}

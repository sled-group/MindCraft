package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenHellLava extends WorldGenerator<WorldGenFeatureHellFlowingLavaConfiguration> {

    private static final IBlockData a = Blocks.NETHERRACK.getBlockData();

    public WorldGenHellLava(Function<Dynamic<?>, ? extends WorldGenFeatureHellFlowingLavaConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureHellFlowingLavaConfiguration worldgenfeaturehellflowinglavaconfiguration) {
        if (generatoraccess.getType(blockposition.up()) != WorldGenHellLava.a) {
            return false;
        } else if (!generatoraccess.getType(blockposition).isAir() && generatoraccess.getType(blockposition) != WorldGenHellLava.a) {
            return false;
        } else {
            int i = 0;

            if (generatoraccess.getType(blockposition.west()) == WorldGenHellLava.a) {
                ++i;
            }

            if (generatoraccess.getType(blockposition.east()) == WorldGenHellLava.a) {
                ++i;
            }

            if (generatoraccess.getType(blockposition.north()) == WorldGenHellLava.a) {
                ++i;
            }

            if (generatoraccess.getType(blockposition.south()) == WorldGenHellLava.a) {
                ++i;
            }

            if (generatoraccess.getType(blockposition.down()) == WorldGenHellLava.a) {
                ++i;
            }

            int j = 0;

            if (generatoraccess.isEmpty(blockposition.west())) {
                ++j;
            }

            if (generatoraccess.isEmpty(blockposition.east())) {
                ++j;
            }

            if (generatoraccess.isEmpty(blockposition.north())) {
                ++j;
            }

            if (generatoraccess.isEmpty(blockposition.south())) {
                ++j;
            }

            if (generatoraccess.isEmpty(blockposition.down())) {
                ++j;
            }

            if (!worldgenfeaturehellflowinglavaconfiguration.a && i == 4 && j == 1 || i == 5) {
                generatoraccess.setTypeAndData(blockposition, Blocks.LAVA.getBlockData(), 2);
                generatoraccess.getFluidTickList().a(blockposition, FluidTypes.LAVA, 0);
            }

            return true;
        }
    }
}

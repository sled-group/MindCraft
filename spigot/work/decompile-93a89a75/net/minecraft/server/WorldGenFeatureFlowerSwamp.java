package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureFlowerSwamp extends WorldGenFlowers {

    public WorldGenFeatureFlowerSwamp(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    public IBlockData a(Random random, BlockPosition blockposition) {
        return Blocks.BLUE_ORCHID.getBlockData();
    }
}

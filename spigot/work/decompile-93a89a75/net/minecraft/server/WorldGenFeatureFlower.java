package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureFlower extends WorldGenFlowers {

    public WorldGenFeatureFlower(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    public IBlockData a(Random random, BlockPosition blockposition) {
        return random.nextFloat() > 0.6666667F ? Blocks.DANDELION.getBlockData() : Blocks.POPPY.getBlockData();
    }
}

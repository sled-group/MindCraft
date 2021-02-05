package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFlowerValley extends WorldGenFlowers {

    public WorldGenFlowerValley(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    public IBlockData a(Random random, BlockPosition blockposition) {
        return Blocks.LILY_OF_THE_VALLEY.getBlockData();
    }
}

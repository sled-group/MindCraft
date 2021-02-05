package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureFlowerForest extends WorldGenFlowers {

    private static final Block[] a = new Block[]{Blocks.DANDELION, Blocks.POPPY, Blocks.BLUE_ORCHID, Blocks.ALLIUM, Blocks.AZURE_BLUET, Blocks.RED_TULIP, Blocks.ORANGE_TULIP, Blocks.WHITE_TULIP, Blocks.PINK_TULIP, Blocks.OXEYE_DAISY, Blocks.CORNFLOWER, Blocks.LILY_OF_THE_VALLEY};

    public WorldGenFeatureFlowerForest(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    public IBlockData a(Random random, BlockPosition blockposition) {
        double d0 = MathHelper.a((1.0D + BiomeBase.e.a((double) blockposition.getX() / 48.0D, (double) blockposition.getZ() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
        Block block = WorldGenFeatureFlowerForest.a[(int) (d0 * (double) WorldGenFeatureFlowerForest.a.length)];

        return block == Blocks.BLUE_ORCHID ? Blocks.POPPY.getBlockData() : block.getBlockData();
    }
}

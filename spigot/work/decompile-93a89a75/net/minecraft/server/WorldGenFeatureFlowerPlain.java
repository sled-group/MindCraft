package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureFlowerPlain extends WorldGenFlowers {

    public WorldGenFeatureFlowerPlain(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    public IBlockData a(Random random, BlockPosition blockposition) {
        double d0 = BiomeBase.e.a((double) blockposition.getX() / 200.0D, (double) blockposition.getZ() / 200.0D);
        int i;

        if (d0 < -0.8D) {
            i = random.nextInt(4);
            switch (i) {
                case 0:
                    return Blocks.ORANGE_TULIP.getBlockData();
                case 1:
                    return Blocks.RED_TULIP.getBlockData();
                case 2:
                    return Blocks.PINK_TULIP.getBlockData();
                case 3:
                default:
                    return Blocks.WHITE_TULIP.getBlockData();
            }
        } else if (random.nextInt(3) > 0) {
            i = random.nextInt(4);
            switch (i) {
                case 0:
                    return Blocks.POPPY.getBlockData();
                case 1:
                    return Blocks.AZURE_BLUET.getBlockData();
                case 2:
                    return Blocks.OXEYE_DAISY.getBlockData();
                case 3:
                default:
                    return Blocks.CORNFLOWER.getBlockData();
            }
        } else {
            return Blocks.DANDELION.getBlockData();
        }
    }
}

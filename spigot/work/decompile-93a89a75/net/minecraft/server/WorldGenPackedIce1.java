package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenPackedIce1 extends WorldGenerator<WorldGenFeatureRadiusConfiguration> {

    private final Block a;

    public WorldGenPackedIce1(Function<Dynamic<?>, ? extends WorldGenFeatureRadiusConfiguration> function) {
        super(function);
        this.a = Blocks.PACKED_ICE;
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureRadiusConfiguration worldgenfeatureradiusconfiguration) {
        while (generatoraccess.isEmpty(blockposition) && blockposition.getY() > 2) {
            blockposition = blockposition.down();
        }

        if (generatoraccess.getType(blockposition).getBlock() != Blocks.SNOW_BLOCK) {
            return false;
        } else {
            int i = random.nextInt(worldgenfeatureradiusconfiguration.a) + 2;
            boolean flag = true;

            for (int j = blockposition.getX() - i; j <= blockposition.getX() + i; ++j) {
                for (int k = blockposition.getZ() - i; k <= blockposition.getZ() + i; ++k) {
                    int l = j - blockposition.getX();
                    int i1 = k - blockposition.getZ();

                    if (l * l + i1 * i1 <= i * i) {
                        for (int j1 = blockposition.getY() - 1; j1 <= blockposition.getY() + 1; ++j1) {
                            BlockPosition blockposition1 = new BlockPosition(j, j1, k);
                            Block block = generatoraccess.getType(blockposition1).getBlock();

                            if (Block.c(block) || block == Blocks.SNOW_BLOCK || block == Blocks.ICE) {
                                generatoraccess.setTypeAndData(blockposition1, this.a.getBlockData(), 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}

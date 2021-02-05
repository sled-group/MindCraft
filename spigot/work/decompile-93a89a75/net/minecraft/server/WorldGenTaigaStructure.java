package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;

public class WorldGenTaigaStructure extends WorldGenerator<WorldGenFeatureBlockOffsetConfiguration> {

    public WorldGenTaigaStructure(Function<Dynamic<?>, ? extends WorldGenFeatureBlockOffsetConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureBlockOffsetConfiguration worldgenfeatureblockoffsetconfiguration) {
        while (true) {
            if (blockposition.getY() > 3) {
                label47:
                {
                    if (!generatoraccess.isEmpty(blockposition.down())) {
                        Block block = generatoraccess.getType(blockposition.down()).getBlock();

                        if (block == Blocks.GRASS_BLOCK || Block.c(block) || Block.b(block)) {
                            break label47;
                        }
                    }

                    blockposition = blockposition.down();
                    continue;
                }
            }

            if (blockposition.getY() <= 3) {
                return false;
            }

            int i = worldgenfeatureblockoffsetconfiguration.b;

            for (int j = 0; i >= 0 && j < 3; ++j) {
                int k = i + random.nextInt(2);
                int l = i + random.nextInt(2);
                int i1 = i + random.nextInt(2);
                float f = (float) (k + l + i1) * 0.333F + 0.5F;
                Iterator iterator = BlockPosition.a(blockposition.b(-k, -l, -i1), blockposition.b(k, l, i1)).iterator();

                while (iterator.hasNext()) {
                    BlockPosition blockposition1 = (BlockPosition) iterator.next();

                    if (blockposition1.m(blockposition) <= (double) (f * f)) {
                        generatoraccess.setTypeAndData(blockposition1, worldgenfeatureblockoffsetconfiguration.a, 4);
                    }
                }

                blockposition = blockposition.b(-(i + 1) + random.nextInt(2 + i * 2), 0 - random.nextInt(2), -(i + 1) + random.nextInt(2 + i * 2));
            }

            return true;
        }
    }
}

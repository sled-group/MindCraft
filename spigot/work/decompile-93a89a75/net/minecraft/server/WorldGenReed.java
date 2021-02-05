package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenReed extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenReed(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        int i = 0;

        for (int j = 0; j < 20; ++j) {
            BlockPosition blockposition1 = blockposition.b(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));

            if (generatoraccess.isEmpty(blockposition1)) {
                BlockPosition blockposition2 = blockposition1.down();

                if (generatoraccess.getFluid(blockposition2.west()).a(TagsFluid.WATER) || generatoraccess.getFluid(blockposition2.east()).a(TagsFluid.WATER) || generatoraccess.getFluid(blockposition2.north()).a(TagsFluid.WATER) || generatoraccess.getFluid(blockposition2.south()).a(TagsFluid.WATER)) {
                    int k = 2 + random.nextInt(random.nextInt(3) + 1);

                    for (int l = 0; l < k; ++l) {
                        if (Blocks.SUGAR_CANE.getBlockData().canPlace(generatoraccess, blockposition1)) {
                            generatoraccess.setTypeAndData(blockposition1.up(l), Blocks.SUGAR_CANE.getBlockData(), 2);
                            ++i;
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}

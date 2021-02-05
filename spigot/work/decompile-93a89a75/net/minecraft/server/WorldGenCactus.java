package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenCactus extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenCactus(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        for (int i = 0; i < 10; ++i) {
            BlockPosition blockposition1 = blockposition.b(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            if (generatoraccess.isEmpty(blockposition1)) {
                int j = 1 + random.nextInt(random.nextInt(3) + 1);

                for (int k = 0; k < j; ++k) {
                    if (Blocks.CACTUS.getBlockData().canPlace(generatoraccess, blockposition1)) {
                        generatoraccess.setTypeAndData(blockposition1.up(k), Blocks.CACTUS.getBlockData(), 2);
                    }
                }
            }
        }

        return true;
    }
}

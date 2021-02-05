package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public abstract class WorldGenFlowers extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenFlowers(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function, false);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        IBlockData iblockdata = this.a(random, blockposition);
        int i = 0;

        for (int j = 0; j < 64; ++j) {
            BlockPosition blockposition1 = blockposition.b(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            if (generatoraccess.isEmpty(blockposition1) && blockposition1.getY() < 255 && iblockdata.canPlace(generatoraccess, blockposition1)) {
                generatoraccess.setTypeAndData(blockposition1, iblockdata, 2);
                ++i;
            }
        }

        return i > 0;
    }

    public abstract IBlockData a(Random random, BlockPosition blockposition);
}

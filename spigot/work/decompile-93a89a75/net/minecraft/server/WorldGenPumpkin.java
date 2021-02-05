package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenPumpkin extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    protected final IBlockData a;

    public WorldGenPumpkin(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function, IBlockData iblockdata) {
        super(function);
        this.a = iblockdata;
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        int i = 0;

        for (int j = 0; j < 64; ++j) {
            BlockPosition blockposition1 = blockposition.b(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            if (generatoraccess.isEmpty(blockposition1) && generatoraccess.getType(blockposition1.down()).getBlock() == Blocks.GRASS_BLOCK) {
                generatoraccess.setTypeAndData(blockposition1, this.a, 2);
                ++i;
            }
        }

        return i > 0;
    }
}

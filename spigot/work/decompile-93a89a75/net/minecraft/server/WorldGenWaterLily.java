package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenWaterLily extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenWaterLily(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        BlockPosition blockposition1;

        for (BlockPosition blockposition2 = blockposition; blockposition2.getY() > 0; blockposition2 = blockposition1) {
            blockposition1 = blockposition2.down();
            if (!generatoraccess.isEmpty(blockposition1)) {
                break;
            }
        }

        for (int i = 0; i < 10; ++i) {
            BlockPosition blockposition3 = blockposition.b(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            IBlockData iblockdata = Blocks.LILY_PAD.getBlockData();

            if (generatoraccess.isEmpty(blockposition3) && iblockdata.canPlace(generatoraccess, blockposition3)) {
                generatoraccess.setTypeAndData(blockposition3, iblockdata, 2);
            }
        }

        return true;
    }
}

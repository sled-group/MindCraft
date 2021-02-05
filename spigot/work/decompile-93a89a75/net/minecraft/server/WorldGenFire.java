package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFire extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenFire(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        for (int i = 0; i < 64; ++i) {
            BlockPosition blockposition1 = blockposition.b(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            if (generatoraccess.isEmpty(blockposition1) && generatoraccess.getType(blockposition1.down()).getBlock() == Blocks.NETHERRACK) {
                generatoraccess.setTypeAndData(blockposition1, Blocks.FIRE.getBlockData(), 2);
            }
        }

        return true;
    }
}

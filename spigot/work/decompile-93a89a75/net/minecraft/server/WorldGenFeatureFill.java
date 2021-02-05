package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureFill extends WorldGenerator<WorldGenFeatureFillConfiguration> {

    public WorldGenFeatureFill(Function<Dynamic<?>, ? extends WorldGenFeatureFillConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureFillConfiguration worldgenfeaturefillconfiguration) {
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int k = blockposition.getX() + i;
                int l = blockposition.getZ() + j;
                int i1 = worldgenfeaturefillconfiguration.a;

                blockposition_mutableblockposition.d(k, i1, l);
                if (generatoraccess.getType(blockposition_mutableblockposition).isAir()) {
                    generatoraccess.setTypeAndData(blockposition_mutableblockposition, worldgenfeaturefillconfiguration.b, 2);
                }
            }
        }

        return true;
    }
}

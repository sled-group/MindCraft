package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureBlock extends WorldGenerator<WorldGenFeatureBlockConfiguration> {

    public WorldGenFeatureBlock(Function<Dynamic<?>, ? extends WorldGenFeatureBlockConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureBlockConfiguration worldgenfeatureblockconfiguration) {
        if (worldgenfeatureblockconfiguration.b.contains(generatoraccess.getType(blockposition.down())) && worldgenfeatureblockconfiguration.c.contains(generatoraccess.getType(blockposition)) && worldgenfeatureblockconfiguration.d.contains(generatoraccess.getType(blockposition.up()))) {
            generatoraccess.setTypeAndData(blockposition, worldgenfeatureblockconfiguration.a, 2);
            return true;
        } else {
            return false;
        }
    }
}

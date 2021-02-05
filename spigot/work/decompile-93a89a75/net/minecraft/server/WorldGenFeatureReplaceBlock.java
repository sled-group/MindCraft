package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureReplaceBlock extends WorldGenerator<WorldGenFeatureReplaceBlockConfiguration> {

    public WorldGenFeatureReplaceBlock(Function<Dynamic<?>, ? extends WorldGenFeatureReplaceBlockConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureReplaceBlockConfiguration worldgenfeaturereplaceblockconfiguration) {
        if (generatoraccess.getType(blockposition).getBlock() == worldgenfeaturereplaceblockconfiguration.a.getBlock()) {
            generatoraccess.setTypeAndData(blockposition, worldgenfeaturereplaceblockconfiguration.b, 2);
        }

        return true;
    }
}

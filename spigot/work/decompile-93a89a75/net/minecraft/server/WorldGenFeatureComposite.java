package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureComposite extends WorldGenerator<WorldGenFeatureCompositeConfiguration> {

    public WorldGenFeatureComposite(Function<Dynamic<?>, ? extends WorldGenFeatureCompositeConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureCompositeConfiguration worldgenfeaturecompositeconfiguration) {
        return worldgenfeaturecompositeconfiguration.b.a(generatoraccess, chunkgenerator, random, blockposition, worldgenfeaturecompositeconfiguration.a);
    }

    public String toString() {
        return String.format("< %s [%s] >", this.getClass().getSimpleName(), IRegistry.FEATURE.getKey(this));
    }
}

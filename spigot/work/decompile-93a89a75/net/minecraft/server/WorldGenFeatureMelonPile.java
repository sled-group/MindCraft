package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.function.Function;

public class WorldGenFeatureMelonPile extends WorldGenFeatureBlockPile {

    public WorldGenFeatureMelonPile(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    protected IBlockData a(GeneratorAccess generatoraccess) {
        return Blocks.MELON.getBlockData();
    }
}

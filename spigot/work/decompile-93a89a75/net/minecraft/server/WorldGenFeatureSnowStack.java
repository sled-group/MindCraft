package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.function.Function;

public class WorldGenFeatureSnowStack extends WorldGenFeatureBlockPile {

    public WorldGenFeatureSnowStack(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    protected IBlockData a(GeneratorAccess generatoraccess) {
        return Blocks.SNOW_BLOCK.getBlockData();
    }
}

package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.function.Function;

public class WorldGenFeaturePumpkinStack extends WorldGenFeatureBlockPile {

    public WorldGenFeaturePumpkinStack(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    protected IBlockData a(GeneratorAccess generatoraccess) {
        return generatoraccess.getRandom().nextFloat() < 0.95F ? Blocks.PUMPKIN.getBlockData() : Blocks.JACK_O_LANTERN.getBlockData();
    }
}

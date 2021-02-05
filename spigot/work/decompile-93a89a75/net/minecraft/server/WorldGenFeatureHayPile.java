package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.function.Function;

public class WorldGenFeatureHayPile extends WorldGenFeatureBlockPile {

    public WorldGenFeatureHayPile(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    protected IBlockData a(GeneratorAccess generatoraccess) {
        EnumDirection.EnumAxis enumdirection_enumaxis = EnumDirection.EnumAxis.a(generatoraccess.getRandom());

        return (IBlockData) Blocks.HAY_BLOCK.getBlockData().set(BlockRotatable.AXIS, enumdirection_enumaxis);
    }
}

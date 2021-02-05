package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.function.Function;

public class WorldGenFeatureIcePile extends WorldGenFeatureBlockPile {

    public WorldGenFeatureIcePile(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    protected IBlockData a(GeneratorAccess generatoraccess) {
        return generatoraccess.getRandom().nextInt(7) == 0 ? Blocks.BLUE_ICE.getBlockData() : Blocks.PACKED_ICE.getBlockData();
    }
}

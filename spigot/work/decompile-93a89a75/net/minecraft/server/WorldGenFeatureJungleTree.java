package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureJungleTree extends WorldGenTrees {

    public WorldGenFeatureJungleTree(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function, boolean flag, int i, IBlockData iblockdata, IBlockData iblockdata1, boolean flag1) {
        super(function, flag, i, iblockdata, iblockdata1, flag1);
    }

    @Override
    protected int a(Random random) {
        return this.a + random.nextInt(7);
    }
}

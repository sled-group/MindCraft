package net.minecraft.server;

import java.util.Random;
import javax.annotation.Nullable;

public class WorldGenMegaTreeProviderJungle extends WorldGenMegaTreeProvider {

    public WorldGenMegaTreeProviderJungle() {}

    @Nullable
    @Override
    protected WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> b(Random random) {
        return new WorldGenTrees(WorldGenFeatureEmptyConfiguration::a, true, 4 + random.nextInt(7), Blocks.JUNGLE_LOG.getBlockData(), Blocks.JUNGLE_LEAVES.getBlockData(), false);
    }

    @Nullable
    @Override
    protected WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> a(Random random) {
        return new WorldGenJungleTree(WorldGenFeatureEmptyConfiguration::a, true, 10, 20, Blocks.JUNGLE_LOG.getBlockData(), Blocks.JUNGLE_LEAVES.getBlockData());
    }
}

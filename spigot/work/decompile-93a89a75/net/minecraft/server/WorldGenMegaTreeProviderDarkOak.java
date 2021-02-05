package net.minecraft.server;

import java.util.Random;
import javax.annotation.Nullable;

public class WorldGenMegaTreeProviderDarkOak extends WorldGenMegaTreeProvider {

    public WorldGenMegaTreeProviderDarkOak() {}

    @Nullable
    @Override
    protected WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> b(Random random) {
        return null;
    }

    @Nullable
    @Override
    protected WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> a(Random random) {
        return new WorldGenForestTree(WorldGenFeatureEmptyConfiguration::a, true);
    }
}

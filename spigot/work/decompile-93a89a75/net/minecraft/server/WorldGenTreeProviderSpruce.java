package net.minecraft.server;

import java.util.Random;
import javax.annotation.Nullable;

public class WorldGenTreeProviderSpruce extends WorldGenMegaTreeProvider {

    public WorldGenTreeProviderSpruce() {}

    @Nullable
    @Override
    protected WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> b(Random random) {
        return new WorldGenTaiga2(WorldGenFeatureEmptyConfiguration::a, true);
    }

    @Nullable
    @Override
    protected WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> a(Random random) {
        return new WorldGenMegaTree(WorldGenFeatureEmptyConfiguration::a, false, random.nextBoolean());
    }
}

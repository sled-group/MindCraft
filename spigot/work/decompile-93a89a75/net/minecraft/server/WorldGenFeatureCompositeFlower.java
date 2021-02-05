package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.function.Function;

public class WorldGenFeatureCompositeFlower extends WorldGenFeatureComposite {

    public WorldGenFeatureCompositeFlower(Function<Dynamic<?>, ? extends WorldGenFeatureCompositeConfiguration> function) {
        super(function);
    }
}

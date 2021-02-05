package net.minecraft.server;

import java.util.function.Function;
import java.util.function.Supplier;

public class BiomeLayout<C extends BiomeLayoutConfiguration, T extends WorldChunkManager> {

    public static final BiomeLayout<BiomeLayoutCheckerboardConfiguration, WorldChunkManagerCheckerBoard> a = a("checkerboard", WorldChunkManagerCheckerBoard::new, BiomeLayoutCheckerboardConfiguration::new);
    public static final BiomeLayout<BiomeLayoutFixedConfiguration, WorldChunkManagerHell> b = a("fixed", WorldChunkManagerHell::new, BiomeLayoutFixedConfiguration::new);
    public static final BiomeLayout<BiomeLayoutOverworldConfiguration, WorldChunkManagerOverworld> c = a("vanilla_layered", WorldChunkManagerOverworld::new, BiomeLayoutOverworldConfiguration::new);
    public static final BiomeLayout<BiomeLayoutTheEndConfiguration, WorldChunkManagerTheEnd> d = a("the_end", WorldChunkManagerTheEnd::new, BiomeLayoutTheEndConfiguration::new);
    private final Function<C, T> e;
    private final Supplier<C> f;

    private static <C extends BiomeLayoutConfiguration, T extends WorldChunkManager> BiomeLayout<C, T> a(String s, Function<C, T> function, Supplier<C> supplier) {
        return (BiomeLayout) IRegistry.a(IRegistry.BIOME_SOURCE_TYPE, s, (Object) (new BiomeLayout<>(function, supplier)));
    }

    public BiomeLayout(Function<C, T> function, Supplier<C> supplier) {
        this.e = function;
        this.f = supplier;
    }

    public T a(C c0) {
        return (WorldChunkManager) this.e.apply(c0);
    }

    public C a() {
        return (BiomeLayoutConfiguration) this.f.get();
    }
}

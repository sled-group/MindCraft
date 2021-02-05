package net.minecraft.server;

import java.util.function.Supplier;

public class ChunkGeneratorType<C extends GeneratorSettingsDefault, T extends ChunkGenerator<C>> implements ChunkGeneratorFactory<C, T> {

    public static final ChunkGeneratorType<GeneratorSettingsOverworld, ChunkProviderGenerate> a = a("surface", ChunkProviderGenerate::new, GeneratorSettingsOverworld::new, true);
    public static final ChunkGeneratorType<GeneratorSettingsNether, ChunkProviderHell> b = a("caves", ChunkProviderHell::new, GeneratorSettingsNether::new, true);
    public static final ChunkGeneratorType<GeneratorSettingsEnd, ChunkProviderTheEnd> c = a("floating_islands", ChunkProviderTheEnd::new, GeneratorSettingsEnd::new, true);
    public static final ChunkGeneratorType<GeneratorSettingsDebug, ChunkProviderDebug> d = a("debug", ChunkProviderDebug::new, GeneratorSettingsDebug::new, false);
    public static final ChunkGeneratorType<GeneratorSettingsFlat, ChunkProviderFlat> e = a("flat", ChunkProviderFlat::new, GeneratorSettingsFlat::new, false);
    private final ChunkGeneratorFactory<C, T> f;
    private final boolean g;
    private final Supplier<C> h;

    private static <C extends GeneratorSettingsDefault, T extends ChunkGenerator<C>> ChunkGeneratorType<C, T> a(String s, ChunkGeneratorFactory<C, T> chunkgeneratorfactory, Supplier<C> supplier, boolean flag) {
        return (ChunkGeneratorType) IRegistry.a(IRegistry.CHUNK_GENERATOR_TYPE, s, (Object) (new ChunkGeneratorType<>(chunkgeneratorfactory, flag, supplier)));
    }

    public ChunkGeneratorType(ChunkGeneratorFactory<C, T> chunkgeneratorfactory, boolean flag, Supplier<C> supplier) {
        this.f = chunkgeneratorfactory;
        this.g = flag;
        this.h = supplier;
    }

    @Override
    public T create(World world, WorldChunkManager worldchunkmanager, C c0) {
        return this.f.create(world, worldchunkmanager, c0);
    }

    public C a() {
        return (GeneratorSettingsDefault) this.h.get();
    }
}

package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.io.File;
import java.util.function.BiFunction;
import javax.annotation.Nullable;

public class DimensionManager implements MinecraftSerializable {

    public static final DimensionManager OVERWORLD = register("overworld", new DimensionManager(1, "", "", WorldProviderNormal::new, true));
    public static final DimensionManager NETHER = register("the_nether", new DimensionManager(0, "_nether", "DIM-1", WorldProviderHell::new, false));
    public static final DimensionManager THE_END = register("the_end", new DimensionManager(2, "_end", "DIM1", WorldProviderTheEnd::new, false));
    private final int id;
    private final String suffix;
    public final String folder;
    public final BiFunction<World, DimensionManager, ? extends WorldProvider> providerFactory;
    private final boolean hasSkyLight;

    public static DimensionManager register(String s, DimensionManager dimensionmanager) {
        return (DimensionManager) IRegistry.a(IRegistry.DIMENSION_TYPE, dimensionmanager.id, s, dimensionmanager);
    }

    public DimensionManager(int i, String s, String s1, BiFunction<World, DimensionManager, ? extends WorldProvider> bifunction, boolean flag) {
        this.id = i;
        this.suffix = s;
        this.folder = s1;
        this.providerFactory = bifunction;
        this.hasSkyLight = flag;
    }

    public static DimensionManager a(Dynamic<?> dynamic) {
        return (DimensionManager) IRegistry.DIMENSION_TYPE.get(new MinecraftKey(dynamic.asString("")));
    }

    public static Iterable<DimensionManager> a() {
        return IRegistry.DIMENSION_TYPE;
    }

    public int getDimensionID() {
        return this.id + -1;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public File a(File file) {
        return this.folder.isEmpty() ? file : new File(file, this.folder);
    }

    public WorldProvider getWorldProvider(World world) {
        return (WorldProvider) this.providerFactory.apply(world, this);
    }

    public String toString() {
        return a(this).toString();
    }

    @Nullable
    public static DimensionManager a(int i) {
        return (DimensionManager) IRegistry.DIMENSION_TYPE.fromId(i - -1);
    }

    @Nullable
    public static DimensionManager a(MinecraftKey minecraftkey) {
        return (DimensionManager) IRegistry.DIMENSION_TYPE.get(minecraftkey);
    }

    @Nullable
    public static MinecraftKey a(DimensionManager dimensionmanager) {
        return IRegistry.DIMENSION_TYPE.getKey(dimensionmanager);
    }

    public boolean hasSkyLight() {
        return this.hasSkyLight;
    }

    @Override
    public <T> T a(DynamicOps<T> dynamicops) {
        return dynamicops.createString(IRegistry.DIMENSION_TYPE.getKey(this).toString());
    }
}

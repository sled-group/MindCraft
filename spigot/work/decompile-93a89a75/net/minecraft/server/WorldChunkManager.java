package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;

public abstract class WorldChunkManager {

    private static final List<BiomeBase> c = Lists.newArrayList(new BiomeBase[]{Biomes.FOREST, Biomes.PLAINS, Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.WOODED_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_HILLS});
    protected final Map<StructureGenerator<?>, Boolean> a = Maps.newHashMap();
    protected final Set<IBlockData> b = Sets.newHashSet();

    protected WorldChunkManager() {}

    public List<BiomeBase> a() {
        return WorldChunkManager.c;
    }

    public BiomeBase getBiome(BlockPosition blockposition) {
        return this.getBiome(blockposition.getX(), blockposition.getZ());
    }

    public abstract BiomeBase getBiome(int i, int j);

    public BiomeBase b(int i, int j) {
        return this.getBiome(i << 2, j << 2);
    }

    public BiomeBase[] getBiomeBlock(int i, int j, int k, int l) {
        return this.a(i, j, k, l, true);
    }

    public abstract BiomeBase[] a(int i, int j, int k, int l, boolean flag);

    public abstract Set<BiomeBase> a(int i, int j, int k);

    @Nullable
    public abstract BlockPosition a(int i, int j, int k, List<BiomeBase> list, Random random);

    public float c(int i, int j) {
        return 0.0F;
    }

    public abstract boolean a(StructureGenerator<?> structuregenerator);

    public abstract Set<IBlockData> b();
}

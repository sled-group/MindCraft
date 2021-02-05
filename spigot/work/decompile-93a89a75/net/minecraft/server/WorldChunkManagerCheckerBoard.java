package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;

public class WorldChunkManagerCheckerBoard extends WorldChunkManager {

    private final BiomeBase[] c;
    private final int d;

    public WorldChunkManagerCheckerBoard(BiomeLayoutCheckerboardConfiguration biomelayoutcheckerboardconfiguration) {
        this.c = biomelayoutcheckerboardconfiguration.a();
        this.d = biomelayoutcheckerboardconfiguration.b() + 4;
    }

    @Override
    public BiomeBase getBiome(int i, int j) {
        return this.c[Math.abs(((i >> this.d) + (j >> this.d)) % this.c.length)];
    }

    @Override
    public BiomeBase[] a(int i, int j, int k, int l, boolean flag) {
        BiomeBase[] abiomebase = new BiomeBase[k * l];

        for (int i1 = 0; i1 < l; ++i1) {
            for (int j1 = 0; j1 < k; ++j1) {
                int k1 = Math.abs(((i + i1 >> this.d) + (j + j1 >> this.d)) % this.c.length);
                BiomeBase biomebase = this.c[k1];

                abiomebase[i1 * k + j1] = biomebase;
            }
        }

        return abiomebase;
    }

    @Nullable
    @Override
    public BlockPosition a(int i, int j, int k, List<BiomeBase> list, Random random) {
        return null;
    }

    @Override
    public boolean a(StructureGenerator<?> structuregenerator) {
        return (Boolean) this.a.computeIfAbsent(structuregenerator, (structuregenerator1) -> {
            BiomeBase[] abiomebase = this.c;
            int i = abiomebase.length;

            for (int j = 0; j < i; ++j) {
                BiomeBase biomebase = abiomebase[j];

                if (biomebase.a(structuregenerator1)) {
                    return true;
                }
            }

            return false;
        });
    }

    @Override
    public Set<IBlockData> b() {
        if (this.b.isEmpty()) {
            BiomeBase[] abiomebase = this.c;
            int i = abiomebase.length;

            for (int j = 0; j < i; ++j) {
                BiomeBase biomebase = abiomebase[j];

                this.b.add(biomebase.q().a());
            }
        }

        return this.b;
    }

    @Override
    public Set<BiomeBase> a(int i, int j, int k) {
        return Sets.newHashSet(this.c);
    }
}

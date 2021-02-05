package net.minecraft.server;

import java.util.BitSet;
import java.util.Random;

public class WorldGenCarverWrapper<WC extends WorldGenCarverConfiguration> {

    public final WorldGenCarverAbstract<WC> a;
    public final WC b;

    public WorldGenCarverWrapper(WorldGenCarverAbstract<WC> worldgencarverabstract, WC wc) {
        this.a = worldgencarverabstract;
        this.b = wc;
    }

    public boolean a(Random random, int i, int j) {
        return this.a.a(random, i, j, this.b);
    }

    public boolean a(IChunkAccess ichunkaccess, Random random, int i, int j, int k, int l, int i1, BitSet bitset) {
        return this.a.a(ichunkaccess, random, i, j, k, l, i1, bitset, this.b);
    }
}

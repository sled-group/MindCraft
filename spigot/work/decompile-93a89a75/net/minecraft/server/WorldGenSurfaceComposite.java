package net.minecraft.server;

import java.util.Random;

public class WorldGenSurfaceComposite<SC extends WorldGenSurfaceConfiguration> {

    public final WorldGenSurface<SC> a;
    public final SC b;

    public WorldGenSurfaceComposite(WorldGenSurface<SC> worldgensurface, SC sc) {
        this.a = worldgensurface;
        this.b = sc;
    }

    public void a(Random random, IChunkAccess ichunkaccess, BiomeBase biomebase, int i, int j, int k, double d0, IBlockData iblockdata, IBlockData iblockdata1, int l, long i1) {
        this.a.a(random, ichunkaccess, biomebase, i, j, k, d0, iblockdata, iblockdata1, l, i1, this.b);
    }

    public void a(long i) {
        this.a.a(i);
    }

    public SC a() {
        return this.b;
    }
}

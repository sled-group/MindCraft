package net.minecraft.server;

public abstract class ChunkMap extends LightEngineGraph {

    protected ChunkMap(int i, int j, int k) {
        super(i, j, k);
    }

    @Override
    protected boolean a(long i) {
        return i == ChunkCoordIntPair.a;
    }

    @Override
    protected void a(long i, int j, boolean flag) {
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i);
        int k = chunkcoordintpair.x;
        int l = chunkcoordintpair.z;

        for (int i1 = -1; i1 <= 1; ++i1) {
            for (int j1 = -1; j1 <= 1; ++j1) {
                long k1 = ChunkCoordIntPair.pair(k + i1, l + j1);

                if (k1 != i) {
                    this.b(i, k1, j, flag);
                }
            }
        }

    }

    @Override
    protected int a(long i, long j, int k) {
        int l = k;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i);
        int i1 = chunkcoordintpair.x;
        int j1 = chunkcoordintpair.z;

        for (int k1 = -1; k1 <= 1; ++k1) {
            for (int l1 = -1; l1 <= 1; ++l1) {
                long i2 = ChunkCoordIntPair.pair(i1 + k1, j1 + l1);

                if (i2 == i) {
                    i2 = ChunkCoordIntPair.a;
                }

                if (i2 != j) {
                    int j2 = this.b(i2, i, this.c(i2));

                    if (l > j2) {
                        l = j2;
                    }

                    if (l == 0) {
                        return l;
                    }
                }
            }
        }

        return l;
    }

    @Override
    protected int b(long i, long j, int k) {
        return i == ChunkCoordIntPair.a ? this.b(j) : k + 1;
    }

    protected abstract int b(long i);

    public void b(long i, int j, boolean flag) {
        this.a(ChunkCoordIntPair.a, i, j, flag);
    }
}

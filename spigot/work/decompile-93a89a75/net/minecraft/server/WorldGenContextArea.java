package net.minecraft.server;

import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
import java.util.Random;

public class WorldGenContextArea implements AreaContextTransformed<AreaLazy> {

    private final Long2IntLinkedOpenHashMap c;
    private final int d;
    protected long a;
    protected NoiseGeneratorPerlin b;
    private long e;
    private long f;

    public WorldGenContextArea(int i, long j, long k) {
        this.a = k;
        this.a *= this.a * 6364136223846793005L + 1442695040888963407L;
        this.a += k;
        this.a *= this.a * 6364136223846793005L + 1442695040888963407L;
        this.a += k;
        this.a *= this.a * 6364136223846793005L + 1442695040888963407L;
        this.a += k;
        this.c = new Long2IntLinkedOpenHashMap(16, 0.25F);
        this.c.defaultReturnValue(Integer.MIN_VALUE);
        this.d = i;
        this.a(j);
    }

    @Override
    public AreaLazy a(AreaTransformer8 areatransformer8) {
        return new AreaLazy(this.c, this.d, areatransformer8);
    }

    public AreaLazy a(AreaTransformer8 areatransformer8, AreaLazy arealazy) {
        return new AreaLazy(this.c, Math.min(1024, arealazy.a() * 4), areatransformer8);
    }

    public AreaLazy a(AreaTransformer8 areatransformer8, AreaLazy arealazy, AreaLazy arealazy1) {
        return new AreaLazy(this.c, Math.min(1024, Math.max(arealazy.a(), arealazy1.a()) * 4), areatransformer8);
    }

    public void a(long i) {
        this.e = i;
        this.e *= this.e * 6364136223846793005L + 1442695040888963407L;
        this.e += this.a;
        this.e *= this.e * 6364136223846793005L + 1442695040888963407L;
        this.e += this.a;
        this.e *= this.e * 6364136223846793005L + 1442695040888963407L;
        this.e += this.a;
        this.b = new NoiseGeneratorPerlin(new Random(i));
    }

    @Override
    public void a(long i, long j) {
        this.f = this.e;
        this.f *= this.f * 6364136223846793005L + 1442695040888963407L;
        this.f += i;
        this.f *= this.f * 6364136223846793005L + 1442695040888963407L;
        this.f += j;
        this.f *= this.f * 6364136223846793005L + 1442695040888963407L;
        this.f += i;
        this.f *= this.f * 6364136223846793005L + 1442695040888963407L;
        this.f += j;
    }

    @Override
    public int a(int i) {
        int j = (int) ((this.f >> 24) % (long) i);

        if (j < 0) {
            j += i;
        }

        this.f *= this.f * 6364136223846793005L + 1442695040888963407L;
        this.f += this.e;
        return j;
    }

    @Override
    public NoiseGeneratorPerlin a() {
        return this.b;
    }
}

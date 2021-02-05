package net.minecraft.server;

import java.util.Random;

public class SeededRandom extends Random {

    private int a;

    public SeededRandom() {}

    public SeededRandom(long i) {
        super(i);
    }

    public void a(int i) {
        for (int j = 0; j < i; ++j) {
            this.next(1);
        }

    }

    protected int next(int i) {
        ++this.a;
        return super.next(i);
    }

    public long a(int i, int j) {
        long k = (long) i * 341873128712L + (long) j * 132897987541L;

        this.setSeed(k);
        return k;
    }

    public long a(long i, int j, int k) {
        this.setSeed(i);
        long l = this.nextLong() | 1L;
        long i1 = this.nextLong() | 1L;
        long j1 = (long) j * l + (long) k * i1 ^ i;

        this.setSeed(j1);
        return j1;
    }

    public long b(long i, int j, int k) {
        long l = i + (long) j + (long) (10000 * k);

        this.setSeed(l);
        return l;
    }

    public long c(long i, int j, int k) {
        this.setSeed(i);
        long l = this.nextLong();
        long i1 = this.nextLong();
        long j1 = (long) j * l ^ (long) k * i1 ^ i;

        this.setSeed(j1);
        return j1;
    }

    public long a(long i, int j, int k, int l) {
        long i1 = (long) j * 341873128712L + (long) k * 132897987541L + i + (long) l;

        this.setSeed(i1);
        return i1;
    }

    public static Random a(int i, int j, long k, long l) {
        return new Random(k + (long) (i * i * 4987142) + (long) (i * 5947611) + (long) (j * j) * 4392871L + (long) (j * 389711) ^ l);
    }
}

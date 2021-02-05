package net.minecraft.server;

import java.util.Random;

public final class NoiseGeneratorPerlin {

    private final byte[] d;
    public final double a;
    public final double b;
    public final double c;

    public NoiseGeneratorPerlin(Random random) {
        this.a = random.nextDouble() * 256.0D;
        this.b = random.nextDouble() * 256.0D;
        this.c = random.nextDouble() * 256.0D;
        this.d = new byte[256];

        int i;

        for (i = 0; i < 256; ++i) {
            this.d[i] = (byte) i;
        }

        for (i = 0; i < 256; ++i) {
            int j = random.nextInt(256 - i);
            byte b0 = this.d[i];

            this.d[i] = this.d[i + j];
            this.d[i + j] = b0;
        }

    }

    public double a(double d0, double d1, double d2, double d3, double d4) {
        double d5 = d0 + this.a;
        double d6 = d1 + this.b;
        double d7 = d2 + this.c;
        int i = MathHelper.floor(d5);
        int j = MathHelper.floor(d6);
        int k = MathHelper.floor(d7);
        double d8 = d5 - (double) i;
        double d9 = d6 - (double) j;
        double d10 = d7 - (double) k;
        double d11 = MathHelper.j(d8);
        double d12 = MathHelper.j(d9);
        double d13 = MathHelper.j(d10);
        double d14;

        if (d3 != 0.0D) {
            double d15 = Math.min(d4, d9);

            d14 = (double) MathHelper.floor(d15 / d3) * d3;
        } else {
            d14 = 0.0D;
        }

        return this.a(i, j, k, d8, d9 - d14, d10, d11, d12, d13);
    }

    private static double a(int i, double d0, double d1, double d2) {
        int j = i & 15;

        return NoiseGenerator3Handler.a(NoiseGenerator3Handler.a[j], d0, d1, d2);
    }

    private int a(int i) {
        return this.d[i & 255] & 255;
    }

    public double a(int i, int j, int k, double d0, double d1, double d2, double d3, double d4, double d5) {
        int l = this.a(i) + j;
        int i1 = this.a(l) + k;
        int j1 = this.a(l + 1) + k;
        int k1 = this.a(i + 1) + j;
        int l1 = this.a(k1) + k;
        int i2 = this.a(k1 + 1) + k;
        double d6 = a(this.a(i1), d0, d1, d2);
        double d7 = a(this.a(l1), d0 - 1.0D, d1, d2);
        double d8 = a(this.a(j1), d0, d1 - 1.0D, d2);
        double d9 = a(this.a(i2), d0 - 1.0D, d1 - 1.0D, d2);
        double d10 = a(this.a(i1 + 1), d0, d1, d2 - 1.0D);
        double d11 = a(this.a(l1 + 1), d0 - 1.0D, d1, d2 - 1.0D);
        double d12 = a(this.a(j1 + 1), d0, d1 - 1.0D, d2 - 1.0D);
        double d13 = a(this.a(i2 + 1), d0 - 1.0D, d1 - 1.0D, d2 - 1.0D);

        return MathHelper.a(d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13);
    }
}

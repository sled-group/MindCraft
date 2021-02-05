package net.minecraft.server;

import java.util.Random;

public class NoiseGeneratorOctaves implements NoiseGenerator {

    private final NoiseGeneratorPerlin[] a;

    public NoiseGeneratorOctaves(Random random, int i) {
        this.a = new NoiseGeneratorPerlin[i];

        for (int j = 0; j < i; ++j) {
            this.a[j] = new NoiseGeneratorPerlin(random);
        }

    }

    public double a(double d0, double d1, double d2) {
        return this.a(d0, d1, d2, 0.0D, 0.0D, false);
    }

    public double a(double d0, double d1, double d2, double d3, double d4, boolean flag) {
        double d5 = 0.0D;
        double d6 = 1.0D;
        NoiseGeneratorPerlin[] anoisegeneratorperlin = this.a;
        int i = anoisegeneratorperlin.length;

        for (int j = 0; j < i; ++j) {
            NoiseGeneratorPerlin noisegeneratorperlin = anoisegeneratorperlin[j];

            d5 += noisegeneratorperlin.a(a(d0 * d6), flag ? -noisegeneratorperlin.b : a(d1 * d6), a(d2 * d6), d3 * d6, d4 * d6) / d6;
            d6 /= 2.0D;
        }

        return d5;
    }

    public NoiseGeneratorPerlin a(int i) {
        return this.a[i];
    }

    public static double a(double d0) {
        return d0 - (double) MathHelper.d(d0 / 3.3554432E7D + 0.5D) * 3.3554432E7D;
    }

    @Override
    public double a(double d0, double d1, double d2, double d3) {
        return this.a(d0, d1, 0.0D, d2, d3, false);
    }
}

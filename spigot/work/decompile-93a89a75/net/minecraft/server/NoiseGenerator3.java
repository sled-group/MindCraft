package net.minecraft.server;

import java.util.Random;

public class NoiseGenerator3 implements NoiseGenerator {

    private final NoiseGenerator3Handler[] a;
    private final int b;

    public NoiseGenerator3(Random random, int i) {
        this.b = i;
        this.a = new NoiseGenerator3Handler[i];

        for (int j = 0; j < i; ++j) {
            this.a[j] = new NoiseGenerator3Handler(random);
        }

    }

    public double a(double d0, double d1) {
        return this.a(d0, d1, false);
    }

    public double a(double d0, double d1, boolean flag) {
        double d2 = 0.0D;
        double d3 = 1.0D;

        for (int i = 0; i < this.b; ++i) {
            d2 += this.a[i].a(d0 * d3 + (flag ? this.a[i].b : 0.0D), d1 * d3 + (flag ? this.a[i].c : 0.0D)) / d3;
            d3 /= 2.0D;
        }

        return d2;
    }

    @Override
    public double a(double d0, double d1, double d2, double d3) {
        return this.a(d0, d1, true) * 0.55D;
    }
}

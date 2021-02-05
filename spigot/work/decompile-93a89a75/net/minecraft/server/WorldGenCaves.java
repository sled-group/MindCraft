package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class WorldGenCaves extends WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> {

    public WorldGenCaves(Function<Dynamic<?>, ? extends WorldGenFeatureConfigurationChance> function, int i) {
        super(function, i);
    }

    public boolean a(Random random, int i, int j, WorldGenFeatureConfigurationChance worldgenfeatureconfigurationchance) {
        return random.nextFloat() <= worldgenfeatureconfigurationchance.b;
    }

    public boolean a(IChunkAccess ichunkaccess, Random random, int i, int j, int k, int l, int i1, BitSet bitset, WorldGenFeatureConfigurationChance worldgenfeatureconfigurationchance) {
        int j1 = (this.c() * 2 - 1) * 16;
        int k1 = random.nextInt(random.nextInt(random.nextInt(this.a()) + 1) + 1);

        for (int l1 = 0; l1 < k1; ++l1) {
            double d0 = (double) (j * 16 + random.nextInt(16));
            double d1 = (double) this.b(random);
            double d2 = (double) (k * 16 + random.nextInt(16));
            int i2 = 1;
            float f;

            if (random.nextInt(4) == 0) {
                double d3 = 0.5D;

                f = 1.0F + random.nextFloat() * 6.0F;
                this.a(ichunkaccess, random.nextLong(), i, l, i1, d0, d1, d2, f, 0.5D, bitset);
                i2 += random.nextInt(4);
            }

            for (int j2 = 0; j2 < i2; ++j2) {
                float f1 = random.nextFloat() * 6.2831855F;

                f = (random.nextFloat() - 0.5F) / 4.0F;
                float f2 = this.a(random);
                int k2 = j1 - random.nextInt(j1 / 4);
                boolean flag = false;

                this.a(ichunkaccess, random.nextLong(), i, l, i1, d0, d1, d2, f2, f1, f, 0, k2, this.b(), bitset);
            }
        }

        return true;
    }

    protected int a() {
        return 15;
    }

    protected float a(Random random) {
        float f = random.nextFloat() * 2.0F + random.nextFloat();

        if (random.nextInt(10) == 0) {
            f *= random.nextFloat() * random.nextFloat() * 3.0F + 1.0F;
        }

        return f;
    }

    protected double b() {
        return 1.0D;
    }

    protected int b(Random random) {
        return random.nextInt(random.nextInt(120) + 8);
    }

    protected void a(IChunkAccess ichunkaccess, long i, int j, int k, int l, double d0, double d1, double d2, float f, double d3, BitSet bitset) {
        double d4 = 1.5D + (double) (MathHelper.sin(1.5707964F) * f);
        double d5 = d4 * d3;

        this.a(ichunkaccess, i, j, k, l, d0 + 1.0D, d1, d2, d4, d5, bitset);
    }

    protected void a(IChunkAccess ichunkaccess, long i, int j, int k, int l, double d0, double d1, double d2, float f, float f1, float f2, int i1, int j1, double d3, BitSet bitset) {
        Random random = new Random(i);
        int k1 = random.nextInt(j1 / 2) + j1 / 4;
        boolean flag = random.nextInt(6) == 0;
        float f3 = 0.0F;
        float f4 = 0.0F;

        for (int l1 = i1; l1 < j1; ++l1) {
            double d4 = 1.5D + (double) (MathHelper.sin(3.1415927F * (float) l1 / (float) j1) * f);
            double d5 = d4 * d3;
            float f5 = MathHelper.cos(f2);

            d0 += (double) (MathHelper.cos(f1) * f5);
            d1 += (double) MathHelper.sin(f2);
            d2 += (double) (MathHelper.sin(f1) * f5);
            f2 *= flag ? 0.92F : 0.7F;
            f2 += f4 * 0.1F;
            f1 += f3 * 0.1F;
            f4 *= 0.9F;
            f3 *= 0.75F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (l1 == k1 && f > 1.0F) {
                this.a(ichunkaccess, random.nextLong(), j, k, l, d0, d1, d2, random.nextFloat() * 0.5F + 0.5F, f1 - 1.5707964F, f2 / 3.0F, l1, j1, 1.0D, bitset);
                this.a(ichunkaccess, random.nextLong(), j, k, l, d0, d1, d2, random.nextFloat() * 0.5F + 0.5F, f1 + 1.5707964F, f2 / 3.0F, l1, j1, 1.0D, bitset);
                return;
            }

            if (random.nextInt(4) != 0) {
                if (!this.a(k, l, d0, d2, l1, j1, f)) {
                    return;
                }

                this.a(ichunkaccess, i, j, k, l, d0, d1, d2, d4, d5, bitset);
            }
        }

    }

    @Override
    protected boolean a(double d0, double d1, double d2, int i) {
        return d1 <= -0.7D || d0 * d0 + d1 * d1 + d2 * d2 >= 1.0D;
    }
}

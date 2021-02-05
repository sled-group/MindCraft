package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class WorldGenCanyon extends WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> {

    private final float[] m = new float[1024];

    public WorldGenCanyon(Function<Dynamic<?>, ? extends WorldGenFeatureConfigurationChance> function) {
        super(function, 256);
    }

    public boolean a(Random random, int i, int j, WorldGenFeatureConfigurationChance worldgenfeatureconfigurationchance) {
        return random.nextFloat() <= worldgenfeatureconfigurationchance.b;
    }

    public boolean a(IChunkAccess ichunkaccess, Random random, int i, int j, int k, int l, int i1, BitSet bitset, WorldGenFeatureConfigurationChance worldgenfeatureconfigurationchance) {
        int j1 = (this.c() * 2 - 1) * 16;
        double d0 = (double) (j * 16 + random.nextInt(16));
        double d1 = (double) (random.nextInt(random.nextInt(40) + 8) + 20);
        double d2 = (double) (k * 16 + random.nextInt(16));
        float f = random.nextFloat() * 6.2831855F;
        float f1 = (random.nextFloat() - 0.5F) * 2.0F / 8.0F;
        double d3 = 3.0D;
        float f2 = (random.nextFloat() * 2.0F + random.nextFloat()) * 2.0F;
        int k1 = j1 - random.nextInt(j1 / 4);
        boolean flag = false;

        this.a(ichunkaccess, random.nextLong(), i, l, i1, d0, d1, d2, f2, f, f1, 0, k1, 3.0D, bitset);
        return true;
    }

    private void a(IChunkAccess ichunkaccess, long i, int j, int k, int l, double d0, double d1, double d2, float f, float f1, float f2, int i1, int j1, double d3, BitSet bitset) {
        Random random = new Random(i);
        float f3 = 1.0F;

        for (int k1 = 0; k1 < 256; ++k1) {
            if (k1 == 0 || random.nextInt(3) == 0) {
                f3 = 1.0F + random.nextFloat() * random.nextFloat();
            }

            this.m[k1] = f3 * f3;
        }

        float f4 = 0.0F;
        float f5 = 0.0F;

        for (int l1 = i1; l1 < j1; ++l1) {
            double d4 = 1.5D + (double) (MathHelper.sin((float) l1 * 3.1415927F / (float) j1) * f);
            double d5 = d4 * d3;

            d4 *= (double) random.nextFloat() * 0.25D + 0.75D;
            d5 *= (double) random.nextFloat() * 0.25D + 0.75D;
            float f6 = MathHelper.cos(f2);
            float f7 = MathHelper.sin(f2);

            d0 += (double) (MathHelper.cos(f1) * f6);
            d1 += (double) f7;
            d2 += (double) (MathHelper.sin(f1) * f6);
            f2 *= 0.7F;
            f2 += f5 * 0.05F;
            f1 += f4 * 0.05F;
            f5 *= 0.8F;
            f4 *= 0.5F;
            f5 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
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
        return (d0 * d0 + d2 * d2) * (double) this.m[i - 1] + d1 * d1 / 6.0D >= 1.0D;
    }
}

package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class WorldGenMinable extends WorldGenerator<WorldGenFeatureOreConfiguration> {

    public WorldGenMinable(Function<Dynamic<?>, ? extends WorldGenFeatureOreConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureOreConfiguration worldgenfeatureoreconfiguration) {
        float f = random.nextFloat() * 3.1415927F;
        float f1 = (float) worldgenfeatureoreconfiguration.b / 8.0F;
        int i = MathHelper.f(((float) worldgenfeatureoreconfiguration.b / 16.0F * 2.0F + 1.0F) / 2.0F);
        double d0 = (double) ((float) blockposition.getX() + MathHelper.sin(f) * f1);
        double d1 = (double) ((float) blockposition.getX() - MathHelper.sin(f) * f1);
        double d2 = (double) ((float) blockposition.getZ() + MathHelper.cos(f) * f1);
        double d3 = (double) ((float) blockposition.getZ() - MathHelper.cos(f) * f1);
        boolean flag = true;
        double d4 = (double) (blockposition.getY() + random.nextInt(3) - 2);
        double d5 = (double) (blockposition.getY() + random.nextInt(3) - 2);
        int j = blockposition.getX() - MathHelper.f(f1) - i;
        int k = blockposition.getY() - 2 - i;
        int l = blockposition.getZ() - MathHelper.f(f1) - i;
        int i1 = 2 * (MathHelper.f(f1) + i);
        int j1 = 2 * (2 + i);

        for (int k1 = j; k1 <= j + i1; ++k1) {
            for (int l1 = l; l1 <= l + i1; ++l1) {
                if (k <= generatoraccess.a(HeightMap.Type.OCEAN_FLOOR_WG, k1, l1)) {
                    return this.a(generatoraccess, random, worldgenfeatureoreconfiguration, d0, d1, d2, d3, d4, d5, j, k, l, i1, j1);
                }
            }
        }

        return false;
    }

    protected boolean a(GeneratorAccess generatoraccess, Random random, WorldGenFeatureOreConfiguration worldgenfeatureoreconfiguration, double d0, double d1, double d2, double d3, double d4, double d5, int i, int j, int k, int l, int i1) {
        int j1 = 0;
        BitSet bitset = new BitSet(l * i1 * l);
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
        double[] adouble = new double[worldgenfeatureoreconfiguration.b * 4];

        int k1;
        double d6;
        double d7;
        double d8;
        double d9;

        for (k1 = 0; k1 < worldgenfeatureoreconfiguration.b; ++k1) {
            float f = (float) k1 / (float) worldgenfeatureoreconfiguration.b;

            d6 = MathHelper.d((double) f, d0, d1);
            d7 = MathHelper.d((double) f, d4, d5);
            d8 = MathHelper.d((double) f, d2, d3);
            d9 = random.nextDouble() * (double) worldgenfeatureoreconfiguration.b / 16.0D;
            double d10 = ((double) (MathHelper.sin(3.1415927F * f) + 1.0F) * d9 + 1.0D) / 2.0D;

            adouble[k1 * 4 + 0] = d6;
            adouble[k1 * 4 + 1] = d7;
            adouble[k1 * 4 + 2] = d8;
            adouble[k1 * 4 + 3] = d10;
        }

        for (k1 = 0; k1 < worldgenfeatureoreconfiguration.b - 1; ++k1) {
            if (adouble[k1 * 4 + 3] > 0.0D) {
                for (int l1 = k1 + 1; l1 < worldgenfeatureoreconfiguration.b; ++l1) {
                    if (adouble[l1 * 4 + 3] > 0.0D) {
                        d6 = adouble[k1 * 4 + 0] - adouble[l1 * 4 + 0];
                        d7 = adouble[k1 * 4 + 1] - adouble[l1 * 4 + 1];
                        d8 = adouble[k1 * 4 + 2] - adouble[l1 * 4 + 2];
                        d9 = adouble[k1 * 4 + 3] - adouble[l1 * 4 + 3];
                        if (d9 * d9 > d6 * d6 + d7 * d7 + d8 * d8) {
                            if (d9 > 0.0D) {
                                adouble[l1 * 4 + 3] = -1.0D;
                            } else {
                                adouble[k1 * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        for (k1 = 0; k1 < worldgenfeatureoreconfiguration.b; ++k1) {
            double d11 = adouble[k1 * 4 + 3];

            if (d11 >= 0.0D) {
                double d12 = adouble[k1 * 4 + 0];
                double d13 = adouble[k1 * 4 + 1];
                double d14 = adouble[k1 * 4 + 2];
                int i2 = Math.max(MathHelper.floor(d12 - d11), i);
                int j2 = Math.max(MathHelper.floor(d13 - d11), j);
                int k2 = Math.max(MathHelper.floor(d14 - d11), k);
                int l2 = Math.max(MathHelper.floor(d12 + d11), i2);
                int i3 = Math.max(MathHelper.floor(d13 + d11), j2);
                int j3 = Math.max(MathHelper.floor(d14 + d11), k2);

                for (int k3 = i2; k3 <= l2; ++k3) {
                    double d15 = ((double) k3 + 0.5D - d12) / d11;

                    if (d15 * d15 < 1.0D) {
                        for (int l3 = j2; l3 <= i3; ++l3) {
                            double d16 = ((double) l3 + 0.5D - d13) / d11;

                            if (d15 * d15 + d16 * d16 < 1.0D) {
                                for (int i4 = k2; i4 <= j3; ++i4) {
                                    double d17 = ((double) i4 + 0.5D - d14) / d11;

                                    if (d15 * d15 + d16 * d16 + d17 * d17 < 1.0D) {
                                        int j4 = k3 - i + (l3 - j) * l + (i4 - k) * l * i1;

                                        if (!bitset.get(j4)) {
                                            bitset.set(j4);
                                            blockposition_mutableblockposition.d(k3, l3, i4);
                                            if (worldgenfeatureoreconfiguration.a.b().test(generatoraccess.getType(blockposition_mutableblockposition))) {
                                                generatoraccess.setTypeAndData(blockposition_mutableblockposition, worldgenfeatureoreconfiguration.c, 2);
                                                ++j1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return j1 > 0;
    }
}

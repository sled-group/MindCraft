package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenLakes extends WorldGenerator<WorldGenFeatureLakeConfiguration> {

    private static final IBlockData a = Blocks.CAVE_AIR.getBlockData();

    public WorldGenLakes(Function<Dynamic<?>, ? extends WorldGenFeatureLakeConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureLakeConfiguration worldgenfeaturelakeconfiguration) {
        while (blockposition.getY() > 5 && generatoraccess.isEmpty(blockposition)) {
            blockposition = blockposition.down();
        }

        if (blockposition.getY() <= 4) {
            return false;
        } else {
            blockposition = blockposition.down(4);
            ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(blockposition);

            if (!generatoraccess.getChunkAt(chunkcoordintpair.x, chunkcoordintpair.z, ChunkStatus.STRUCTURE_REFERENCES).b(WorldGenerator.VILLAGE.b()).isEmpty()) {
                return false;
            } else {
                boolean[] aboolean = new boolean[2048];
                int i = random.nextInt(4) + 4;

                int j;

                for (j = 0; j < i; ++j) {
                    double d0 = random.nextDouble() * 6.0D + 3.0D;
                    double d1 = random.nextDouble() * 4.0D + 2.0D;
                    double d2 = random.nextDouble() * 6.0D + 3.0D;
                    double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                    double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                    double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                    for (int k = 1; k < 15; ++k) {
                        for (int l = 1; l < 15; ++l) {
                            for (int i1 = 1; i1 < 7; ++i1) {
                                double d6 = ((double) k - d3) / (d0 / 2.0D);
                                double d7 = ((double) i1 - d4) / (d1 / 2.0D);
                                double d8 = ((double) l - d5) / (d2 / 2.0D);
                                double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                                if (d9 < 1.0D) {
                                    aboolean[(k * 16 + l) * 8 + i1] = true;
                                }
                            }
                        }
                    }
                }

                boolean flag;
                int j1;
                int k1;

                for (j = 0; j < 16; ++j) {
                    for (k1 = 0; k1 < 16; ++k1) {
                        for (j1 = 0; j1 < 8; ++j1) {
                            flag = !aboolean[(j * 16 + k1) * 8 + j1] && (j < 15 && aboolean[((j + 1) * 16 + k1) * 8 + j1] || j > 0 && aboolean[((j - 1) * 16 + k1) * 8 + j1] || k1 < 15 && aboolean[(j * 16 + k1 + 1) * 8 + j1] || k1 > 0 && aboolean[(j * 16 + (k1 - 1)) * 8 + j1] || j1 < 7 && aboolean[(j * 16 + k1) * 8 + j1 + 1] || j1 > 0 && aboolean[(j * 16 + k1) * 8 + (j1 - 1)]);
                            if (flag) {
                                Material material = generatoraccess.getType(blockposition.b(j, j1, k1)).getMaterial();

                                if (j1 >= 4 && material.isLiquid()) {
                                    return false;
                                }

                                if (j1 < 4 && !material.isBuildable() && generatoraccess.getType(blockposition.b(j, j1, k1)) != worldgenfeaturelakeconfiguration.a) {
                                    return false;
                                }
                            }
                        }
                    }
                }

                for (j = 0; j < 16; ++j) {
                    for (k1 = 0; k1 < 16; ++k1) {
                        for (j1 = 0; j1 < 8; ++j1) {
                            if (aboolean[(j * 16 + k1) * 8 + j1]) {
                                generatoraccess.setTypeAndData(blockposition.b(j, j1, k1), j1 >= 4 ? WorldGenLakes.a : worldgenfeaturelakeconfiguration.a, 2);
                            }
                        }
                    }
                }

                BlockPosition blockposition1;

                for (j = 0; j < 16; ++j) {
                    for (k1 = 0; k1 < 16; ++k1) {
                        for (j1 = 4; j1 < 8; ++j1) {
                            if (aboolean[(j * 16 + k1) * 8 + j1]) {
                                blockposition1 = blockposition.b(j, j1 - 1, k1);
                                if (Block.c(generatoraccess.getType(blockposition1).getBlock()) && generatoraccess.getBrightness(EnumSkyBlock.SKY, blockposition.b(j, j1, k1)) > 0) {
                                    BiomeBase biomebase = generatoraccess.getBiome(blockposition1);

                                    if (biomebase.q().a().getBlock() == Blocks.MYCELIUM) {
                                        generatoraccess.setTypeAndData(blockposition1, Blocks.MYCELIUM.getBlockData(), 2);
                                    } else {
                                        generatoraccess.setTypeAndData(blockposition1, Blocks.GRASS_BLOCK.getBlockData(), 2);
                                    }
                                }
                            }
                        }
                    }
                }

                if (worldgenfeaturelakeconfiguration.a.getMaterial() == Material.LAVA) {
                    for (j = 0; j < 16; ++j) {
                        for (k1 = 0; k1 < 16; ++k1) {
                            for (j1 = 0; j1 < 8; ++j1) {
                                flag = !aboolean[(j * 16 + k1) * 8 + j1] && (j < 15 && aboolean[((j + 1) * 16 + k1) * 8 + j1] || j > 0 && aboolean[((j - 1) * 16 + k1) * 8 + j1] || k1 < 15 && aboolean[(j * 16 + k1 + 1) * 8 + j1] || k1 > 0 && aboolean[(j * 16 + (k1 - 1)) * 8 + j1] || j1 < 7 && aboolean[(j * 16 + k1) * 8 + j1 + 1] || j1 > 0 && aboolean[(j * 16 + k1) * 8 + (j1 - 1)]);
                                if (flag && (j1 < 4 || random.nextInt(2) != 0) && generatoraccess.getType(blockposition.b(j, j1, k1)).getMaterial().isBuildable()) {
                                    generatoraccess.setTypeAndData(blockposition.b(j, j1, k1), Blocks.STONE.getBlockData(), 2);
                                }
                            }
                        }
                    }
                }

                if (worldgenfeaturelakeconfiguration.a.getMaterial() == Material.WATER) {
                    for (j = 0; j < 16; ++j) {
                        for (k1 = 0; k1 < 16; ++k1) {
                            boolean flag1 = true;

                            blockposition1 = blockposition.b(j, 4, k1);
                            if (generatoraccess.getBiome(blockposition1).a(generatoraccess, blockposition1, false)) {
                                generatoraccess.setTypeAndData(blockposition1, Blocks.ICE.getBlockData(), 2);
                            }
                        }
                    }
                }

                return true;
            }
        }
    }
}

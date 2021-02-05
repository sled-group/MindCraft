package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenHugeMushroomRed extends WorldGenerator<WorldGenHugeMushroomConfiguration> {

    public WorldGenHugeMushroomRed(Function<Dynamic<?>, ? extends WorldGenHugeMushroomConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenHugeMushroomConfiguration worldgenhugemushroomconfiguration) {
        int i = random.nextInt(3) + 4;

        if (random.nextInt(12) == 0) {
            i *= 2;
        }

        int j = blockposition.getY();

        if (j >= 1 && j + i + 1 < 256) {
            Block block = generatoraccess.getType(blockposition.down()).getBlock();

            if (!Block.c(block) && block != Blocks.GRASS_BLOCK && block != Blocks.MYCELIUM) {
                return false;
            } else {
                BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

                int k;

                for (int l = 0; l <= i; ++l) {
                    byte b0 = 0;

                    if (l < i && l >= i - 3) {
                        b0 = 2;
                    } else if (l == i) {
                        b0 = 1;
                    }

                    for (k = -b0; k <= b0; ++k) {
                        for (int i1 = -b0; i1 <= b0; ++i1) {
                            IBlockData iblockdata = generatoraccess.getType(blockposition_mutableblockposition.g(blockposition).e(k, l, i1));

                            if (!iblockdata.isAir() && !iblockdata.a(TagsBlock.LEAVES)) {
                                return false;
                            }
                        }
                    }
                }

                IBlockData iblockdata1 = (IBlockData) Blocks.RED_MUSHROOM_BLOCK.getBlockData().set(BlockHugeMushroom.f, false);

                for (int j1 = i - 3; j1 <= i; ++j1) {
                    k = j1 < i ? 2 : 1;
                    boolean flag = false;

                    for (int k1 = -k; k1 <= k; ++k1) {
                        for (int l1 = -k; l1 <= k; ++l1) {
                            boolean flag1 = k1 == -k;
                            boolean flag2 = k1 == k;
                            boolean flag3 = l1 == -k;
                            boolean flag4 = l1 == k;
                            boolean flag5 = flag1 || flag2;
                            boolean flag6 = flag3 || flag4;

                            if (j1 >= i || flag5 != flag6) {
                                blockposition_mutableblockposition.g(blockposition).e(k1, j1, l1);
                                if (!generatoraccess.getType(blockposition_mutableblockposition).g(generatoraccess, blockposition_mutableblockposition)) {
                                    this.a(generatoraccess, blockposition_mutableblockposition, (IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) iblockdata1.set(BlockHugeMushroom.e, j1 >= i - 1)).set(BlockHugeMushroom.d, k1 < 0)).set(BlockHugeMushroom.b, k1 > 0)).set(BlockHugeMushroom.a, l1 < 0)).set(BlockHugeMushroom.c, l1 > 0));
                                }
                            }
                        }
                    }
                }

                IBlockData iblockdata2 = (IBlockData) ((IBlockData) Blocks.MUSHROOM_STEM.getBlockData().set(BlockHugeMushroom.e, false)).set(BlockHugeMushroom.f, false);

                for (k = 0; k < i; ++k) {
                    blockposition_mutableblockposition.g(blockposition).c(EnumDirection.UP, k);
                    if (!generatoraccess.getType(blockposition_mutableblockposition).g(generatoraccess, blockposition_mutableblockposition)) {
                        if (worldgenhugemushroomconfiguration.a) {
                            generatoraccess.setTypeAndData(blockposition_mutableblockposition, iblockdata2, 3);
                        } else {
                            this.a(generatoraccess, blockposition_mutableblockposition, iblockdata2);
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }
}

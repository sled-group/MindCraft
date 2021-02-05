package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureBlueIce extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenFeatureBlueIce(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        if (blockposition.getY() > generatoraccess.getSeaLevel() - 1) {
            return false;
        } else if (generatoraccess.getType(blockposition).getBlock() != Blocks.WATER && generatoraccess.getType(blockposition.down()).getBlock() != Blocks.WATER) {
            return false;
        } else {
            boolean flag = false;
            EnumDirection[] aenumdirection = EnumDirection.values();
            int i = aenumdirection.length;

            int j;

            for (j = 0; j < i; ++j) {
                EnumDirection enumdirection = aenumdirection[j];

                if (enumdirection != EnumDirection.DOWN && generatoraccess.getType(blockposition.shift(enumdirection)).getBlock() == Blocks.PACKED_ICE) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                return false;
            } else {
                generatoraccess.setTypeAndData(blockposition, Blocks.BLUE_ICE.getBlockData(), 2);

                for (int k = 0; k < 200; ++k) {
                    i = random.nextInt(5) - random.nextInt(6);
                    j = 3;
                    if (i < 2) {
                        j += i / 2;
                    }

                    if (j >= 1) {
                        BlockPosition blockposition1 = blockposition.b(random.nextInt(j) - random.nextInt(j), i, random.nextInt(j) - random.nextInt(j));
                        IBlockData iblockdata = generatoraccess.getType(blockposition1);
                        Block block = iblockdata.getBlock();

                        if (iblockdata.getMaterial() == Material.AIR || block == Blocks.WATER || block == Blocks.PACKED_ICE || block == Blocks.ICE) {
                            EnumDirection[] aenumdirection1 = EnumDirection.values();
                            int l = aenumdirection1.length;

                            for (int i1 = 0; i1 < l; ++i1) {
                                EnumDirection enumdirection1 = aenumdirection1[i1];
                                Block block1 = generatoraccess.getType(blockposition1.shift(enumdirection1)).getBlock();

                                if (block1 == Blocks.BLUE_ICE) {
                                    generatoraccess.setTypeAndData(blockposition1, Blocks.BLUE_ICE.getBlockData(), 2);
                                    break;
                                }
                            }
                        }
                    }
                }

                return true;
            }
        }
    }
}

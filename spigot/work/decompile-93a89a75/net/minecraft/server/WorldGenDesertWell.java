package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;

public class WorldGenDesertWell extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    private static final BlockStatePredicate a = BlockStatePredicate.a(Blocks.SAND);
    private final IBlockData aS;
    private final IBlockData aT;
    private final IBlockData aU;

    public WorldGenDesertWell(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
        this.aS = Blocks.SANDSTONE_SLAB.getBlockData();
        this.aT = Blocks.SANDSTONE.getBlockData();
        this.aU = Blocks.WATER.getBlockData();
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        for (blockposition = blockposition.up(); generatoraccess.isEmpty(blockposition) && blockposition.getY() > 2; blockposition = blockposition.down()) {
            ;
        }

        if (!WorldGenDesertWell.a.test(generatoraccess.getType(blockposition))) {
            return false;
        } else {
            int i;
            int j;

            for (i = -2; i <= 2; ++i) {
                for (j = -2; j <= 2; ++j) {
                    if (generatoraccess.isEmpty(blockposition.b(i, -1, j)) && generatoraccess.isEmpty(blockposition.b(i, -2, j))) {
                        return false;
                    }
                }
            }

            for (i = -1; i <= 0; ++i) {
                for (j = -2; j <= 2; ++j) {
                    for (int k = -2; k <= 2; ++k) {
                        generatoraccess.setTypeAndData(blockposition.b(j, i, k), this.aT, 2);
                    }
                }
            }

            generatoraccess.setTypeAndData(blockposition, this.aU, 2);
            Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

            while (iterator.hasNext()) {
                EnumDirection enumdirection = (EnumDirection) iterator.next();

                generatoraccess.setTypeAndData(blockposition.shift(enumdirection), this.aU, 2);
            }

            for (i = -2; i <= 2; ++i) {
                for (j = -2; j <= 2; ++j) {
                    if (i == -2 || i == 2 || j == -2 || j == 2) {
                        generatoraccess.setTypeAndData(blockposition.b(i, 1, j), this.aT, 2);
                    }
                }
            }

            generatoraccess.setTypeAndData(blockposition.b(2, 1, 0), this.aS, 2);
            generatoraccess.setTypeAndData(blockposition.b(-2, 1, 0), this.aS, 2);
            generatoraccess.setTypeAndData(blockposition.b(0, 1, 2), this.aS, 2);
            generatoraccess.setTypeAndData(blockposition.b(0, 1, -2), this.aS, 2);

            for (i = -1; i <= 1; ++i) {
                for (j = -1; j <= 1; ++j) {
                    if (i == 0 && j == 0) {
                        generatoraccess.setTypeAndData(blockposition.b(i, 4, j), this.aT, 2);
                    } else {
                        generatoraccess.setTypeAndData(blockposition.b(i, 4, j), this.aS, 2);
                    }
                }
            }

            for (i = 1; i <= 3; ++i) {
                generatoraccess.setTypeAndData(blockposition.b(-1, i, -1), this.aT, 2);
                generatoraccess.setTypeAndData(blockposition.b(-1, i, 1), this.aT, 2);
                generatoraccess.setTypeAndData(blockposition.b(1, i, -1), this.aT, 2);
                generatoraccess.setTypeAndData(blockposition.b(1, i, 1), this.aT, 2);
            }

            return true;
        }
    }
}

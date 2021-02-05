package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureCircle extends WorldGenerator<WorldGenFeatureCircleConfiguration> {

    public WorldGenFeatureCircle(Function<Dynamic<?>, ? extends WorldGenFeatureCircleConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureCircleConfiguration worldgenfeaturecircleconfiguration) {
        if (!generatoraccess.getFluid(blockposition).a(TagsFluid.WATER)) {
            return false;
        } else {
            int i = 0;
            int j = random.nextInt(worldgenfeaturecircleconfiguration.b - 2) + 2;

            for (int k = blockposition.getX() - j; k <= blockposition.getX() + j; ++k) {
                for (int l = blockposition.getZ() - j; l <= blockposition.getZ() + j; ++l) {
                    int i1 = k - blockposition.getX();
                    int j1 = l - blockposition.getZ();

                    if (i1 * i1 + j1 * j1 <= j * j) {
                        int k1 = blockposition.getY() - worldgenfeaturecircleconfiguration.c;

                        while (k1 <= blockposition.getY() + worldgenfeaturecircleconfiguration.c) {
                            BlockPosition blockposition1 = new BlockPosition(k, k1, l);
                            IBlockData iblockdata = generatoraccess.getType(blockposition1);
                            Iterator iterator = worldgenfeaturecircleconfiguration.d.iterator();

                            while (true) {
                                if (iterator.hasNext()) {
                                    IBlockData iblockdata1 = (IBlockData) iterator.next();

                                    if (iblockdata1.getBlock() != iblockdata.getBlock()) {
                                        continue;
                                    }

                                    generatoraccess.setTypeAndData(blockposition1, worldgenfeaturecircleconfiguration.a, 2);
                                    ++i;
                                }

                                ++k1;
                                break;
                            }
                        }
                    }
                }
            }

            return i > 0;
        }
    }
}

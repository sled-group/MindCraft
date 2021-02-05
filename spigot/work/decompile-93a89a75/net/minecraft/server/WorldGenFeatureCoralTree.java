package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureCoralTree extends WorldGenFeatureCoral {

    public WorldGenFeatureCoralTree(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    protected boolean a(GeneratorAccess generatoraccess, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(blockposition);
        int i = random.nextInt(3) + 1;

        for (int j = 0; j < i; ++j) {
            if (!this.b(generatoraccess, random, blockposition_mutableblockposition, iblockdata)) {
                return true;
            }

            blockposition_mutableblockposition.c(EnumDirection.UP);
        }

        BlockPosition blockposition1 = blockposition_mutableblockposition.immutableCopy();
        int k = random.nextInt(3) + 2;
        List<EnumDirection> list = Lists.newArrayList(EnumDirection.EnumDirectionLimit.HORIZONTAL);

        Collections.shuffle(list, random);
        List<EnumDirection> list1 = list.subList(0, k);
        Iterator iterator = list1.iterator();

        while (iterator.hasNext()) {
            EnumDirection enumdirection = (EnumDirection) iterator.next();

            blockposition_mutableblockposition.g(blockposition1);
            blockposition_mutableblockposition.c(enumdirection);
            int l = random.nextInt(5) + 2;
            int i1 = 0;

            for (int j1 = 0; j1 < l && this.b(generatoraccess, random, blockposition_mutableblockposition, iblockdata); ++j1) {
                ++i1;
                blockposition_mutableblockposition.c(EnumDirection.UP);
                if (j1 == 0 || i1 >= 2 && random.nextFloat() < 0.25F) {
                    blockposition_mutableblockposition.c(enumdirection);
                    i1 = 0;
                }
            }
        }

        return true;
    }
}

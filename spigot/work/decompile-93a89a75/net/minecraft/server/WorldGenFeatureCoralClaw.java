package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureCoralClaw extends WorldGenFeatureCoral {

    public WorldGenFeatureCoralClaw(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    protected boolean a(GeneratorAccess generatoraccess, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        if (!this.b(generatoraccess, random, blockposition, iblockdata)) {
            return false;
        } else {
            EnumDirection enumdirection = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random);
            int i = random.nextInt(2) + 2;
            List<EnumDirection> list = Lists.newArrayList(new EnumDirection[]{enumdirection, enumdirection.e(), enumdirection.f()});

            Collections.shuffle(list, random);
            List<EnumDirection> list1 = list.subList(0, i);
            Iterator iterator = list1.iterator();

            while (iterator.hasNext()) {
                EnumDirection enumdirection1 = (EnumDirection) iterator.next();
                BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(blockposition);
                int j = random.nextInt(2) + 1;

                blockposition_mutableblockposition.c(enumdirection1);
                EnumDirection enumdirection2;
                int k;

                if (enumdirection1 == enumdirection) {
                    enumdirection2 = enumdirection;
                    k = random.nextInt(3) + 2;
                } else {
                    blockposition_mutableblockposition.c(EnumDirection.UP);
                    EnumDirection[] aenumdirection = new EnumDirection[]{enumdirection1, EnumDirection.UP};

                    enumdirection2 = aenumdirection[random.nextInt(aenumdirection.length)];
                    k = random.nextInt(3) + 3;
                }

                int l;

                for (l = 0; l < j && this.b(generatoraccess, random, blockposition_mutableblockposition, iblockdata); ++l) {
                    blockposition_mutableblockposition.c(enumdirection2);
                }

                blockposition_mutableblockposition.c(enumdirection2.opposite());
                blockposition_mutableblockposition.c(EnumDirection.UP);

                for (l = 0; l < k; ++l) {
                    blockposition_mutableblockposition.c(enumdirection);
                    if (!this.b(generatoraccess, random, blockposition_mutableblockposition, iblockdata)) {
                        break;
                    }

                    if (random.nextFloat() < 0.25F) {
                        blockposition_mutableblockposition.c(EnumDirection.UP);
                    }
                }
            }

            return true;
        }
    }
}

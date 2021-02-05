package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenVines extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    private static final EnumDirection[] a = EnumDirection.values();

    public WorldGenVines(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(blockposition);

        for (int i = blockposition.getY(); i < 256; ++i) {
            blockposition_mutableblockposition.g(blockposition);
            blockposition_mutableblockposition.e(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
            blockposition_mutableblockposition.p(i);
            if (generatoraccess.isEmpty(blockposition_mutableblockposition)) {
                EnumDirection[] aenumdirection = WorldGenVines.a;
                int j = aenumdirection.length;

                for (int k = 0; k < j; ++k) {
                    EnumDirection enumdirection = aenumdirection[k];

                    if (enumdirection != EnumDirection.DOWN && BlockVine.a((IBlockAccess) generatoraccess, (BlockPosition) blockposition_mutableblockposition, enumdirection)) {
                        generatoraccess.setTypeAndData(blockposition_mutableblockposition, (IBlockData) Blocks.VINE.getBlockData().set(BlockVine.getDirection(enumdirection), true), 2);
                        break;
                    }
                }
            }
        }

        return true;
    }
}

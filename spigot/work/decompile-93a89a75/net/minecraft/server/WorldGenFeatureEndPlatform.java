package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureEndPlatform extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    private static final BlockPosition a = new BlockPosition(8, 3, 8);
    private static final ChunkCoordIntPair aS = new ChunkCoordIntPair(WorldGenFeatureEndPlatform.a);

    public WorldGenFeatureEndPlatform(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    private static int a(int i, int j, int k, int l) {
        return Math.max(Math.abs(i - k), Math.abs(j - l));
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(blockposition);

        if (a(chunkcoordintpair.x, chunkcoordintpair.z, WorldGenFeatureEndPlatform.aS.x, WorldGenFeatureEndPlatform.aS.z) > 1) {
            return true;
        } else {
            BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

            for (int i = chunkcoordintpair.e(); i <= chunkcoordintpair.g(); ++i) {
                for (int j = chunkcoordintpair.d(); j <= chunkcoordintpair.f(); ++j) {
                    if (a(WorldGenFeatureEndPlatform.a.getX(), WorldGenFeatureEndPlatform.a.getZ(), j, i) <= 16) {
                        blockposition_mutableblockposition.d(j, WorldGenFeatureEndPlatform.a.getY(), i);
                        if (blockposition_mutableblockposition.equals(WorldGenFeatureEndPlatform.a)) {
                            generatoraccess.setTypeAndData(blockposition_mutableblockposition, Blocks.COBBLESTONE.getBlockData(), 2);
                        } else {
                            generatoraccess.setTypeAndData(blockposition_mutableblockposition, Blocks.STONE.getBlockData(), 2);
                        }
                    }
                }
            }

            return true;
        }
    }
}

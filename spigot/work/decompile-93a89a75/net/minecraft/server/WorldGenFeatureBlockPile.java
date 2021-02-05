package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;

public abstract class WorldGenFeatureBlockPile extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenFeatureBlockPile(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        if (blockposition.getY() < 5) {
            return false;
        } else {
            int i = 2 + random.nextInt(2);
            int j = 2 + random.nextInt(2);
            Iterator iterator = BlockPosition.a(blockposition.b(-i, 0, -j), blockposition.b(i, 1, j)).iterator();

            while (iterator.hasNext()) {
                BlockPosition blockposition1 = (BlockPosition) iterator.next();
                int k = blockposition.getX() - blockposition1.getX();
                int l = blockposition.getZ() - blockposition1.getZ();

                if ((float) (k * k + l * l) <= random.nextFloat() * 10.0F - random.nextFloat() * 6.0F) {
                    this.b(generatoraccess, blockposition1, random);
                } else if ((double) random.nextFloat() < 0.031D) {
                    this.b(generatoraccess, blockposition1, random);
                }
            }

            return true;
        }
    }

    private boolean a(GeneratorAccess generatoraccess, BlockPosition blockposition, Random random) {
        BlockPosition blockposition1 = blockposition.down();
        IBlockData iblockdata = generatoraccess.getType(blockposition1);

        return iblockdata.getBlock() == Blocks.GRASS_PATH ? random.nextBoolean() : iblockdata.d(generatoraccess, blockposition1, EnumDirection.UP);
    }

    private void b(GeneratorAccess generatoraccess, BlockPosition blockposition, Random random) {
        if (generatoraccess.isEmpty(blockposition) && this.a(generatoraccess, blockposition, random)) {
            generatoraccess.setTypeAndData(blockposition, this.a(generatoraccess), 4);
        }

    }

    protected abstract IBlockData a(GeneratorAccess generatoraccess);
}

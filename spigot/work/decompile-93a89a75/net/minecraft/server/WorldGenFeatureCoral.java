package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;

public abstract class WorldGenFeatureCoral extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenFeatureCoral(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        IBlockData iblockdata = ((Block) TagsBlock.CORAL_BLOCKS.a(random)).getBlockData();

        return this.a(generatoraccess, random, blockposition, iblockdata);
    }

    protected abstract boolean a(GeneratorAccess generatoraccess, Random random, BlockPosition blockposition, IBlockData iblockdata);

    protected boolean b(GeneratorAccess generatoraccess, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        BlockPosition blockposition1 = blockposition.up();
        IBlockData iblockdata1 = generatoraccess.getType(blockposition);

        if ((iblockdata1.getBlock() == Blocks.WATER || iblockdata1.a(TagsBlock.CORALS)) && generatoraccess.getType(blockposition1).getBlock() == Blocks.WATER) {
            generatoraccess.setTypeAndData(blockposition, iblockdata, 3);
            if (random.nextFloat() < 0.25F) {
                generatoraccess.setTypeAndData(blockposition1, ((Block) TagsBlock.CORALS.a(random)).getBlockData(), 2);
            } else if (random.nextFloat() < 0.05F) {
                generatoraccess.setTypeAndData(blockposition1, (IBlockData) Blocks.SEA_PICKLE.getBlockData().set(BlockSeaPickle.a, random.nextInt(4) + 1), 2);
            }

            Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

            while (iterator.hasNext()) {
                EnumDirection enumdirection = (EnumDirection) iterator.next();

                if (random.nextFloat() < 0.2F) {
                    BlockPosition blockposition2 = blockposition.shift(enumdirection);

                    if (generatoraccess.getType(blockposition2).getBlock() == Blocks.WATER) {
                        IBlockData iblockdata2 = (IBlockData) ((Block) TagsBlock.WALL_CORALS.a(random)).getBlockData().set(BlockCoralFanWallAbstract.a, enumdirection);

                        generatoraccess.setTypeAndData(blockposition2, iblockdata2, 2);
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }
}

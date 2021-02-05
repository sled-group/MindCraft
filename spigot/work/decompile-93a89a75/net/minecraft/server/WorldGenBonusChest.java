package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;

public class WorldGenBonusChest extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenBonusChest(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        for (IBlockData iblockdata = generatoraccess.getType(blockposition); (iblockdata.isAir() || iblockdata.a(TagsBlock.LEAVES)) && blockposition.getY() > 1; iblockdata = generatoraccess.getType(blockposition)) {
            blockposition = blockposition.down();
        }

        if (blockposition.getY() < 1) {
            return false;
        } else {
            blockposition = blockposition.up();

            for (int i = 0; i < 4; ++i) {
                BlockPosition blockposition1 = blockposition.b(random.nextInt(4) - random.nextInt(4), random.nextInt(3) - random.nextInt(3), random.nextInt(4) - random.nextInt(4));

                if (generatoraccess.isEmpty(blockposition1)) {
                    generatoraccess.setTypeAndData(blockposition1, Blocks.CHEST.getBlockData(), 2);
                    TileEntityLootable.a(generatoraccess, random, blockposition1, LootTables.b);
                    IBlockData iblockdata1 = Blocks.TORCH.getBlockData();
                    Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

                    while (iterator.hasNext()) {
                        EnumDirection enumdirection = (EnumDirection) iterator.next();
                        BlockPosition blockposition2 = blockposition1.shift(enumdirection);

                        if (iblockdata1.canPlace(generatoraccess, blockposition2)) {
                            generatoraccess.setTypeAndData(blockposition2, iblockdata1, 2);
                        }
                    }

                    return true;
                }
            }

            return false;
        }
    }
}

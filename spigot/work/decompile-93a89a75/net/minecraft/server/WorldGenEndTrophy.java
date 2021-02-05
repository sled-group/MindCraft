package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;

public class WorldGenEndTrophy extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    public static final BlockPosition a = BlockPosition.ZERO;
    private final boolean aS;

    public WorldGenEndTrophy(boolean flag) {
        super(WorldGenFeatureEmptyConfiguration::a);
        this.aS = flag;
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        Iterator iterator = BlockPosition.a(new BlockPosition(blockposition.getX() - 4, blockposition.getY() - 1, blockposition.getZ() - 4), new BlockPosition(blockposition.getX() + 4, blockposition.getY() + 32, blockposition.getZ() + 4)).iterator();

        while (iterator.hasNext()) {
            BlockPosition blockposition1 = (BlockPosition) iterator.next();
            boolean flag = blockposition1.a((BaseBlockPosition) blockposition, 2.5D);

            if (flag || blockposition1.a((BaseBlockPosition) blockposition, 3.5D)) {
                if (blockposition1.getY() < blockposition.getY()) {
                    if (flag) {
                        this.a(generatoraccess, blockposition1, Blocks.BEDROCK.getBlockData());
                    } else if (blockposition1.getY() < blockposition.getY()) {
                        this.a(generatoraccess, blockposition1, Blocks.END_STONE.getBlockData());
                    }
                } else if (blockposition1.getY() > blockposition.getY()) {
                    this.a(generatoraccess, blockposition1, Blocks.AIR.getBlockData());
                } else if (!flag) {
                    this.a(generatoraccess, blockposition1, Blocks.BEDROCK.getBlockData());
                } else if (this.aS) {
                    this.a(generatoraccess, new BlockPosition(blockposition1), Blocks.END_PORTAL.getBlockData());
                } else {
                    this.a(generatoraccess, new BlockPosition(blockposition1), Blocks.AIR.getBlockData());
                }
            }
        }

        for (int i = 0; i < 4; ++i) {
            this.a(generatoraccess, blockposition.up(i), Blocks.BEDROCK.getBlockData());
        }

        BlockPosition blockposition2 = blockposition.up(2);
        Iterator iterator1 = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

        while (iterator1.hasNext()) {
            EnumDirection enumdirection = (EnumDirection) iterator1.next();

            this.a(generatoraccess, blockposition2.shift(enumdirection), (IBlockData) Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, enumdirection));
        }

        return true;
    }
}

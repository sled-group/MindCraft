package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WorldGenJungleTree extends WorldGenMegaTreeAbstract<WorldGenFeatureEmptyConfiguration> {

    public WorldGenJungleTree(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function, boolean flag, int i, int j, IBlockData iblockdata, IBlockData iblockdata1) {
        super(function, flag, i, j, iblockdata, iblockdata1);
    }

    @Override
    public boolean a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        int i = this.a(random);

        if (!this.a(virtuallevelwritable, blockposition, i)) {
            return false;
        } else {
            this.c(virtuallevelwritable, blockposition.up(i), 2, structureboundingbox, set);

            for (int j = blockposition.getY() + i - 2 - random.nextInt(4); j > blockposition.getY() + i / 2; j -= 2 + random.nextInt(4)) {
                float f = random.nextFloat() * 6.2831855F;
                int k = blockposition.getX() + (int) (0.5F + MathHelper.cos(f) * 4.0F);
                int l = blockposition.getZ() + (int) (0.5F + MathHelper.sin(f) * 4.0F);

                int i1;

                for (i1 = 0; i1 < 5; ++i1) {
                    k = blockposition.getX() + (int) (1.5F + MathHelper.cos(f) * (float) i1);
                    l = blockposition.getZ() + (int) (1.5F + MathHelper.sin(f) * (float) i1);
                    this.a(set, (IWorldWriter) virtuallevelwritable, new BlockPosition(k, j - 3 + i1 / 2, l), this.aS, structureboundingbox);
                }

                i1 = 1 + random.nextInt(2);
                int j1 = j;

                for (int k1 = j - i1; k1 <= j1; ++k1) {
                    int l1 = k1 - j1;

                    this.b(virtuallevelwritable, new BlockPosition(k, k1, l), 1 - l1, structureboundingbox, set);
                }
            }

            for (int i2 = 0; i2 < i; ++i2) {
                BlockPosition blockposition1 = blockposition.up(i2);

                if (a((VirtualLevelReadable) virtuallevelwritable, blockposition1)) {
                    this.a(set, (IWorldWriter) virtuallevelwritable, blockposition1, this.aS, structureboundingbox);
                    if (i2 > 0) {
                        this.a(virtuallevelwritable, random, blockposition1.west(), BlockVine.EAST);
                        this.a(virtuallevelwritable, random, blockposition1.north(), BlockVine.SOUTH);
                    }
                }

                if (i2 < i - 1) {
                    BlockPosition blockposition2 = blockposition1.east();

                    if (a((VirtualLevelReadable) virtuallevelwritable, blockposition2)) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition2, this.aS, structureboundingbox);
                        if (i2 > 0) {
                            this.a(virtuallevelwritable, random, blockposition2.east(), BlockVine.WEST);
                            this.a(virtuallevelwritable, random, blockposition2.north(), BlockVine.SOUTH);
                        }
                    }

                    BlockPosition blockposition3 = blockposition1.south().east();

                    if (a((VirtualLevelReadable) virtuallevelwritable, blockposition3)) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition3, this.aS, structureboundingbox);
                        if (i2 > 0) {
                            this.a(virtuallevelwritable, random, blockposition3.east(), BlockVine.WEST);
                            this.a(virtuallevelwritable, random, blockposition3.south(), BlockVine.NORTH);
                        }
                    }

                    BlockPosition blockposition4 = blockposition1.south();

                    if (a((VirtualLevelReadable) virtuallevelwritable, blockposition4)) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition4, this.aS, structureboundingbox);
                        if (i2 > 0) {
                            this.a(virtuallevelwritable, random, blockposition4.west(), BlockVine.EAST);
                            this.a(virtuallevelwritable, random, blockposition4.south(), BlockVine.NORTH);
                        }
                    }
                }
            }

            return true;
        }
    }

    private void a(VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, BlockStateBoolean blockstateboolean) {
        if (random.nextInt(3) > 0 && b((VirtualLevelReadable) virtuallevelwritable, blockposition)) {
            this.a(virtuallevelwritable, blockposition, (IBlockData) Blocks.VINE.getBlockData().set(blockstateboolean, true));
        }

    }

    private void c(VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition, int i, StructureBoundingBox structureboundingbox, Set<BlockPosition> set) {
        boolean flag = true;

        for (int j = -2; j <= 0; ++j) {
            this.a(virtuallevelwritable, blockposition.up(j), i + 1 - j, structureboundingbox, set);
        }

    }
}

package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WorldGenMegaTree extends WorldGenMegaTreeAbstract<WorldGenFeatureEmptyConfiguration> {

    private static final IBlockData aV = Blocks.SPRUCE_LOG.getBlockData();
    private static final IBlockData aW = Blocks.SPRUCE_LEAVES.getBlockData();
    private static final IBlockData aX = Blocks.PODZOL.getBlockData();
    private final boolean aY;

    public WorldGenMegaTree(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function, boolean flag, boolean flag1) {
        super(function, flag, 13, 15, WorldGenMegaTree.aV, WorldGenMegaTree.aW);
        this.aY = flag1;
    }

    @Override
    public boolean a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        int i = this.a(random);

        if (!this.a(virtuallevelwritable, blockposition, i)) {
            return false;
        } else {
            this.a(virtuallevelwritable, blockposition.getX(), blockposition.getZ(), blockposition.getY() + i, 0, random, structureboundingbox, set);

            for (int j = 0; j < i; ++j) {
                if (g(virtuallevelwritable, blockposition.up(j))) {
                    this.a(set, (IWorldWriter) virtuallevelwritable, blockposition.up(j), this.aS, structureboundingbox);
                }

                if (j < i - 1) {
                    if (g(virtuallevelwritable, blockposition.b(1, j, 0))) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition.b(1, j, 0), this.aS, structureboundingbox);
                    }

                    if (g(virtuallevelwritable, blockposition.b(1, j, 1))) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition.b(1, j, 1), this.aS, structureboundingbox);
                    }

                    if (g(virtuallevelwritable, blockposition.b(0, j, 1))) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition.b(0, j, 1), this.aS, structureboundingbox);
                    }
                }
            }

            this.a(virtuallevelwritable, random, blockposition);
            return true;
        }
    }

    private void a(VirtualLevelWritable virtuallevelwritable, int i, int j, int k, int l, Random random, StructureBoundingBox structureboundingbox, Set<BlockPosition> set) {
        int i1 = random.nextInt(5) + (this.aY ? this.a : 3);
        int j1 = 0;

        for (int k1 = k - i1; k1 <= k; ++k1) {
            int l1 = k - k1;
            int i2 = l + MathHelper.d((float) l1 / (float) i1 * 3.5F);

            this.a(virtuallevelwritable, new BlockPosition(i, k1, j), i2 + (l1 > 0 && i2 == j1 && (k1 & 1) == 0 ? 1 : 0), structureboundingbox, set);
            j1 = i2;
        }

    }

    public void a(VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition) {
        this.b(virtuallevelwritable, blockposition.west().north());
        this.b(virtuallevelwritable, blockposition.east(2).north());
        this.b(virtuallevelwritable, blockposition.west().south(2));
        this.b(virtuallevelwritable, blockposition.east(2).south(2));

        for (int i = 0; i < 5; ++i) {
            int j = random.nextInt(64);
            int k = j % 8;
            int l = j / 8;

            if (k == 0 || k == 7 || l == 0 || l == 7) {
                this.b(virtuallevelwritable, blockposition.b(-3 + k, 0, -3 + l));
            }
        }

    }

    private void b(VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition) {
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                if (Math.abs(i) != 2 || Math.abs(j) != 2) {
                    this.c(virtuallevelwritable, blockposition.b(i, 0, j));
                }
            }
        }

    }

    private void c(VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition) {
        for (int i = 2; i >= -3; --i) {
            BlockPosition blockposition1 = blockposition.up(i);

            if (h(virtuallevelwritable, blockposition1)) {
                this.a((IWorldWriter) virtuallevelwritable, blockposition1, WorldGenMegaTree.aX);
                break;
            }

            if (!b((VirtualLevelReadable) virtuallevelwritable, blockposition1) && i < 0) {
                break;
            }
        }

    }
}

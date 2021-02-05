package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public abstract class WorldGenMegaTreeAbstract<T extends WorldGenFeatureConfiguration> extends WorldGenTreeAbstract<T> {

    protected final int a;
    protected final IBlockData aS;
    protected final IBlockData aT;
    protected final int aU;

    public WorldGenMegaTreeAbstract(Function<Dynamic<?>, ? extends T> function, boolean flag, int i, int j, IBlockData iblockdata, IBlockData iblockdata1) {
        super(function, flag);
        this.a = i;
        this.aU = j;
        this.aS = iblockdata;
        this.aT = iblockdata1;
    }

    protected int a(Random random) {
        int i = random.nextInt(3) + this.a;

        if (this.aU > 1) {
            i += random.nextInt(this.aU);
        }

        return i;
    }

    private boolean a(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition, int i) {
        boolean flag = true;

        if (blockposition.getY() >= 1 && blockposition.getY() + i + 1 <= 256) {
            for (int j = 0; j <= 1 + i; ++j) {
                byte b0 = 2;

                if (j == 0) {
                    b0 = 1;
                } else if (j >= 1 + i - 2) {
                    b0 = 2;
                }

                for (int k = -b0; k <= b0 && flag; ++k) {
                    for (int l = -b0; l <= b0 && flag; ++l) {
                        if (blockposition.getY() + j < 0 || blockposition.getY() + j >= 256 || !a(virtuallevelreadable, blockposition.b(k, j, l))) {
                            flag = false;
                        }
                    }
                }
            }

            return flag;
        } else {
            return false;
        }
    }

    private boolean b(VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition) {
        BlockPosition blockposition1 = blockposition.down();

        if (h(virtuallevelwritable, blockposition1) && blockposition.getY() >= 2) {
            this.a(virtuallevelwritable, blockposition1);
            this.a(virtuallevelwritable, blockposition1.east());
            this.a(virtuallevelwritable, blockposition1.south());
            this.a(virtuallevelwritable, blockposition1.south().east());
            return true;
        } else {
            return false;
        }
    }

    protected boolean a(VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition, int i) {
        return this.a((VirtualLevelReadable) virtuallevelwritable, blockposition, i) && this.b(virtuallevelwritable, blockposition);
    }

    protected void a(VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition, int i, StructureBoundingBox structureboundingbox, Set<BlockPosition> set) {
        int j = i * i;

        for (int k = -i; k <= i + 1; ++k) {
            for (int l = -i; l <= i + 1; ++l) {
                int i1 = Math.min(Math.abs(k), Math.abs(k - 1));
                int j1 = Math.min(Math.abs(l), Math.abs(l - 1));

                if (i1 + j1 < 7 && i1 * i1 + j1 * j1 <= j) {
                    BlockPosition blockposition1 = blockposition.b(k, 0, l);

                    if (g(virtuallevelwritable, blockposition1)) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition1, this.aT, structureboundingbox);
                    }
                }
            }
        }

    }

    protected void b(VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition, int i, StructureBoundingBox structureboundingbox, Set<BlockPosition> set) {
        int j = i * i;

        for (int k = -i; k <= i; ++k) {
            for (int l = -i; l <= i; ++l) {
                if (k * k + l * l <= j) {
                    BlockPosition blockposition1 = blockposition.b(k, 0, l);

                    if (g(virtuallevelwritable, blockposition1)) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition1, this.aT, structureboundingbox);
                    }
                }
            }
        }

    }
}

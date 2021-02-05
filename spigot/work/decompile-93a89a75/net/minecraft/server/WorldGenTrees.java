package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WorldGenTrees extends WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> {

    private static final IBlockData aS = Blocks.OAK_LOG.getBlockData();
    private static final IBlockData aT = Blocks.OAK_LEAVES.getBlockData();
    protected final int a;
    private final boolean aU;
    private final IBlockData aV;
    private final IBlockData aW;

    public WorldGenTrees(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function, boolean flag) {
        this(function, flag, 4, WorldGenTrees.aS, WorldGenTrees.aT, false);
    }

    public WorldGenTrees(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function, boolean flag, int i, IBlockData iblockdata, IBlockData iblockdata1, boolean flag1) {
        super(function, flag);
        this.a = i;
        this.aV = iblockdata;
        this.aW = iblockdata1;
        this.aU = flag1;
    }

    @Override
    public boolean a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        int i = this.a(random);
        boolean flag = true;

        if (blockposition.getY() >= 1 && blockposition.getY() + i + 1 <= 256) {
            int j;
            int k;

            for (int l = blockposition.getY(); l <= blockposition.getY() + 1 + i; ++l) {
                byte b0 = 1;

                if (l == blockposition.getY()) {
                    b0 = 0;
                }

                if (l >= blockposition.getY() + 1 + i - 2) {
                    b0 = 2;
                }

                BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

                for (j = blockposition.getX() - b0; j <= blockposition.getX() + b0 && flag; ++j) {
                    for (k = blockposition.getZ() - b0; k <= blockposition.getZ() + b0 && flag; ++k) {
                        if (l >= 0 && l < 256) {
                            if (!a((VirtualLevelReadable) virtuallevelwritable, (BlockPosition) blockposition_mutableblockposition.d(j, l, k))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else if (i(virtuallevelwritable, blockposition.down()) && blockposition.getY() < 256 - i - 1) {
                this.a(virtuallevelwritable, blockposition.down());
                boolean flag1 = true;
                boolean flag2 = false;

                int i1;
                int j1;
                int k1;
                BlockPosition blockposition1;

                for (i1 = blockposition.getY() - 3 + i; i1 <= blockposition.getY() + i; ++i1) {
                    j = i1 - (blockposition.getY() + i);
                    k = 1 - j / 2;

                    for (int l1 = blockposition.getX() - k; l1 <= blockposition.getX() + k; ++l1) {
                        j1 = l1 - blockposition.getX();

                        for (k1 = blockposition.getZ() - k; k1 <= blockposition.getZ() + k; ++k1) {
                            int i2 = k1 - blockposition.getZ();

                            if (Math.abs(j1) != k || Math.abs(i2) != k || random.nextInt(2) != 0 && j != 0) {
                                blockposition1 = new BlockPosition(l1, i1, k1);
                                if (g(virtuallevelwritable, blockposition1) || j(virtuallevelwritable, blockposition1)) {
                                    this.a(set, (IWorldWriter) virtuallevelwritable, blockposition1, this.aW, structureboundingbox);
                                }
                            }
                        }
                    }
                }

                for (i1 = 0; i1 < i; ++i1) {
                    if (g(virtuallevelwritable, blockposition.up(i1)) || j(virtuallevelwritable, blockposition.up(i1))) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition.up(i1), this.aV, structureboundingbox);
                        if (this.aU && i1 > 0) {
                            if (random.nextInt(3) > 0 && b(virtuallevelwritable, blockposition.b(-1, i1, 0))) {
                                this.a((IWorldWriter) virtuallevelwritable, blockposition.b(-1, i1, 0), BlockVine.EAST);
                            }

                            if (random.nextInt(3) > 0 && b(virtuallevelwritable, blockposition.b(1, i1, 0))) {
                                this.a((IWorldWriter) virtuallevelwritable, blockposition.b(1, i1, 0), BlockVine.WEST);
                            }

                            if (random.nextInt(3) > 0 && b(virtuallevelwritable, blockposition.b(0, i1, -1))) {
                                this.a((IWorldWriter) virtuallevelwritable, blockposition.b(0, i1, -1), BlockVine.SOUTH);
                            }

                            if (random.nextInt(3) > 0 && b(virtuallevelwritable, blockposition.b(0, i1, 1))) {
                                this.a((IWorldWriter) virtuallevelwritable, blockposition.b(0, i1, 1), BlockVine.NORTH);
                            }
                        }
                    }
                }

                if (this.aU) {
                    for (i1 = blockposition.getY() - 3 + i; i1 <= blockposition.getY() + i; ++i1) {
                        j = i1 - (blockposition.getY() + i);
                        k = 2 - j / 2;
                        BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = new BlockPosition.MutableBlockPosition();

                        for (j1 = blockposition.getX() - k; j1 <= blockposition.getX() + k; ++j1) {
                            for (k1 = blockposition.getZ() - k; k1 <= blockposition.getZ() + k; ++k1) {
                                blockposition_mutableblockposition1.d(j1, i1, k1);
                                if (f(virtuallevelwritable, blockposition_mutableblockposition1)) {
                                    BlockPosition blockposition2 = blockposition_mutableblockposition1.west();

                                    blockposition1 = blockposition_mutableblockposition1.east();
                                    BlockPosition blockposition3 = blockposition_mutableblockposition1.north();
                                    BlockPosition blockposition4 = blockposition_mutableblockposition1.south();

                                    if (random.nextInt(4) == 0 && b(virtuallevelwritable, blockposition2)) {
                                        this.a(virtuallevelwritable, blockposition2, BlockVine.EAST);
                                    }

                                    if (random.nextInt(4) == 0 && b(virtuallevelwritable, blockposition1)) {
                                        this.a(virtuallevelwritable, blockposition1, BlockVine.WEST);
                                    }

                                    if (random.nextInt(4) == 0 && b(virtuallevelwritable, blockposition3)) {
                                        this.a(virtuallevelwritable, blockposition3, BlockVine.SOUTH);
                                    }

                                    if (random.nextInt(4) == 0 && b(virtuallevelwritable, blockposition4)) {
                                        this.a(virtuallevelwritable, blockposition4, BlockVine.NORTH);
                                    }
                                }
                            }
                        }
                    }

                    if (random.nextInt(5) == 0 && i > 5) {
                        for (i1 = 0; i1 < 2; ++i1) {
                            Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

                            while (iterator.hasNext()) {
                                EnumDirection enumdirection = (EnumDirection) iterator.next();

                                if (random.nextInt(4 - i1) == 0) {
                                    EnumDirection enumdirection1 = enumdirection.opposite();

                                    this.a(virtuallevelwritable, random.nextInt(3), blockposition.b(enumdirection1.getAdjacentX(), i - 5 + i1, enumdirection1.getAdjacentZ()), enumdirection);
                                }
                            }
                        }
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    protected int a(Random random) {
        return this.a + random.nextInt(3);
    }

    private void a(IWorldWriter iworldwriter, int i, BlockPosition blockposition, EnumDirection enumdirection) {
        this.a(iworldwriter, blockposition, (IBlockData) ((IBlockData) Blocks.COCOA.getBlockData().set(BlockCocoa.AGE, i)).set(BlockCocoa.FACING, enumdirection));
    }

    private void a(IWorldWriter iworldwriter, BlockPosition blockposition, BlockStateBoolean blockstateboolean) {
        this.a(iworldwriter, blockposition, (IBlockData) Blocks.VINE.getBlockData().set(blockstateboolean, true));
    }

    private void a(VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition, BlockStateBoolean blockstateboolean) {
        this.a((IWorldWriter) virtuallevelwritable, blockposition, blockstateboolean);
        int i = 4;

        for (blockposition = blockposition.down(); b(virtuallevelwritable, blockposition) && i > 0; --i) {
            this.a((IWorldWriter) virtuallevelwritable, blockposition, blockstateboolean);
            blockposition = blockposition.down();
        }

    }
}

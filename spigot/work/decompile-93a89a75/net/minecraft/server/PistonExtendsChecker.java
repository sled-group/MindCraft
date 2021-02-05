package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;

public class PistonExtendsChecker {

    private final World a;
    private final BlockPosition b;
    private final boolean c;
    private final BlockPosition d;
    private final EnumDirection e;
    private final List<BlockPosition> f = Lists.newArrayList();
    private final List<BlockPosition> g = Lists.newArrayList();
    private final EnumDirection h;

    public PistonExtendsChecker(World world, BlockPosition blockposition, EnumDirection enumdirection, boolean flag) {
        this.a = world;
        this.b = blockposition;
        this.h = enumdirection;
        this.c = flag;
        if (flag) {
            this.e = enumdirection;
            this.d = blockposition.shift(enumdirection);
        } else {
            this.e = enumdirection.opposite();
            this.d = blockposition.shift(enumdirection, 2);
        }

    }

    public boolean a() {
        this.f.clear();
        this.g.clear();
        IBlockData iblockdata = this.a.getType(this.d);

        if (!BlockPiston.a(iblockdata, this.a, this.d, this.e, false, this.h)) {
            if (this.c && iblockdata.getPushReaction() == EnumPistonReaction.DESTROY) {
                this.g.add(this.d);
                return true;
            } else {
                return false;
            }
        } else if (!this.a(this.d, this.e)) {
            return false;
        } else {
            for (int i = 0; i < this.f.size(); ++i) {
                BlockPosition blockposition = (BlockPosition) this.f.get(i);

                if (this.a.getType(blockposition).getBlock() == Blocks.SLIME_BLOCK && !this.a(blockposition)) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean a(BlockPosition blockposition, EnumDirection enumdirection) {
        IBlockData iblockdata = this.a.getType(blockposition);
        Block block = iblockdata.getBlock();

        if (iblockdata.isAir()) {
            return true;
        } else if (!BlockPiston.a(iblockdata, this.a, blockposition, this.e, false, enumdirection)) {
            return true;
        } else if (blockposition.equals(this.b)) {
            return true;
        } else if (this.f.contains(blockposition)) {
            return true;
        } else {
            int i = 1;

            if (i + this.f.size() > 12) {
                return false;
            } else {
                while (block == Blocks.SLIME_BLOCK) {
                    BlockPosition blockposition1 = blockposition.shift(this.e.opposite(), i);

                    iblockdata = this.a.getType(blockposition1);
                    block = iblockdata.getBlock();
                    if (iblockdata.isAir() || !BlockPiston.a(iblockdata, this.a, blockposition1, this.e, false, this.e.opposite()) || blockposition1.equals(this.b)) {
                        break;
                    }

                    ++i;
                    if (i + this.f.size() > 12) {
                        return false;
                    }
                }

                int j = 0;

                int k;

                for (k = i - 1; k >= 0; --k) {
                    this.f.add(blockposition.shift(this.e.opposite(), k));
                    ++j;
                }

                k = 1;

                while (true) {
                    BlockPosition blockposition2 = blockposition.shift(this.e, k);
                    int l = this.f.indexOf(blockposition2);

                    if (l > -1) {
                        this.a(j, l);

                        for (int i1 = 0; i1 <= l + j; ++i1) {
                            BlockPosition blockposition3 = (BlockPosition) this.f.get(i1);

                            if (this.a.getType(blockposition3).getBlock() == Blocks.SLIME_BLOCK && !this.a(blockposition3)) {
                                return false;
                            }
                        }

                        return true;
                    }

                    iblockdata = this.a.getType(blockposition2);
                    if (iblockdata.isAir()) {
                        return true;
                    }

                    if (!BlockPiston.a(iblockdata, this.a, blockposition2, this.e, true, this.e) || blockposition2.equals(this.b)) {
                        return false;
                    }

                    if (iblockdata.getPushReaction() == EnumPistonReaction.DESTROY) {
                        this.g.add(blockposition2);
                        return true;
                    }

                    if (this.f.size() >= 12) {
                        return false;
                    }

                    this.f.add(blockposition2);
                    ++j;
                    ++k;
                }
            }
        }
    }

    private void a(int i, int j) {
        List<BlockPosition> list = Lists.newArrayList();
        List<BlockPosition> list1 = Lists.newArrayList();
        List<BlockPosition> list2 = Lists.newArrayList();

        list.addAll(this.f.subList(0, j));
        list1.addAll(this.f.subList(this.f.size() - i, this.f.size()));
        list2.addAll(this.f.subList(j, this.f.size() - i));
        this.f.clear();
        this.f.addAll(list);
        this.f.addAll(list1);
        this.f.addAll(list2);
    }

    private boolean a(BlockPosition blockposition) {
        EnumDirection[] aenumdirection = EnumDirection.values();
        int i = aenumdirection.length;

        for (int j = 0; j < i; ++j) {
            EnumDirection enumdirection = aenumdirection[j];

            if (enumdirection.k() != this.e.k() && !this.a(blockposition.shift(enumdirection), enumdirection)) {
                return false;
            }
        }

        return true;
    }

    public List<BlockPosition> getMovedBlocks() {
        return this.f;
    }

    public List<BlockPosition> getBrokenBlocks() {
        return this.g;
    }
}

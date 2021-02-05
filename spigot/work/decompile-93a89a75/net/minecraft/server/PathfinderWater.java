package net.minecraft.server;

import javax.annotation.Nullable;

public class PathfinderWater extends PathfinderAbstract {

    private final boolean j;

    public PathfinderWater(boolean flag) {
        this.j = flag;
    }

    @Override
    public PathPoint b() {
        return super.a(MathHelper.floor(this.b.getBoundingBox().minX), MathHelper.floor(this.b.getBoundingBox().minY + 0.5D), MathHelper.floor(this.b.getBoundingBox().minZ));
    }

    @Override
    public PathDestination a(double d0, double d1, double d2) {
        return new PathDestination(super.a(MathHelper.floor(d0 - (double) (this.b.getWidth() / 2.0F)), MathHelper.floor(d1 + 0.5D), MathHelper.floor(d2 - (double) (this.b.getWidth() / 2.0F))));
    }

    @Override
    public int a(PathPoint[] apathpoint, PathPoint pathpoint) {
        int i = 0;
        EnumDirection[] aenumdirection = EnumDirection.values();
        int j = aenumdirection.length;

        for (int k = 0; k < j; ++k) {
            EnumDirection enumdirection = aenumdirection[k];
            PathPoint pathpoint1 = this.b(pathpoint.a + enumdirection.getAdjacentX(), pathpoint.b + enumdirection.getAdjacentY(), pathpoint.c + enumdirection.getAdjacentZ());

            if (pathpoint1 != null && !pathpoint1.i) {
                apathpoint[i++] = pathpoint1;
            }
        }

        return i;
    }

    @Override
    public PathType a(IBlockAccess iblockaccess, int i, int j, int k, EntityInsentient entityinsentient, int l, int i1, int j1, boolean flag, boolean flag1) {
        return this.a(iblockaccess, i, j, k);
    }

    @Override
    public PathType a(IBlockAccess iblockaccess, int i, int j, int k) {
        BlockPosition blockposition = new BlockPosition(i, j, k);
        Fluid fluid = iblockaccess.getFluid(blockposition);
        IBlockData iblockdata = iblockaccess.getType(blockposition);

        return fluid.isEmpty() && iblockdata.a(iblockaccess, blockposition.down(), PathMode.WATER) && iblockdata.isAir() ? PathType.BREACH : (fluid.a(TagsFluid.WATER) && iblockdata.a(iblockaccess, blockposition, PathMode.WATER) ? PathType.WATER : PathType.BLOCKED);
    }

    @Nullable
    private PathPoint b(int i, int j, int k) {
        PathType pathtype = this.c(i, j, k);

        return (!this.j || pathtype != PathType.BREACH) && pathtype != PathType.WATER ? null : this.a(i, j, k);
    }

    @Nullable
    @Override
    protected PathPoint a(int i, int j, int k) {
        PathPoint pathpoint = null;
        PathType pathtype = this.a(this.b.world, i, j, k);
        float f = this.b.a(pathtype);

        if (f >= 0.0F) {
            pathpoint = super.a(i, j, k);
            pathpoint.l = pathtype;
            pathpoint.k = Math.max(pathpoint.k, f);
            if (this.a.getFluid(new BlockPosition(i, j, k)).isEmpty()) {
                pathpoint.k += 8.0F;
            }
        }

        return pathtype == PathType.OPEN ? pathpoint : pathpoint;
    }

    private PathType c(int i, int j, int k) {
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

        for (int l = i; l < i + this.d; ++l) {
            for (int i1 = j; i1 < j + this.e; ++i1) {
                for (int j1 = k; j1 < k + this.f; ++j1) {
                    Fluid fluid = this.a.getFluid(blockposition_mutableblockposition.d(l, i1, j1));
                    IBlockData iblockdata = this.a.getType(blockposition_mutableblockposition.d(l, i1, j1));

                    if (fluid.isEmpty() && iblockdata.a((IBlockAccess) this.a, blockposition_mutableblockposition.down(), PathMode.WATER) && iblockdata.isAir()) {
                        return PathType.BREACH;
                    }

                    if (!fluid.a(TagsFluid.WATER)) {
                        return PathType.BLOCKED;
                    }
                }
            }
        }

        IBlockData iblockdata1 = this.a.getType(blockposition_mutableblockposition);

        if (iblockdata1.a((IBlockAccess) this.a, blockposition_mutableblockposition, PathMode.WATER)) {
            return PathType.WATER;
        } else {
            return PathType.BLOCKED;
        }
    }
}

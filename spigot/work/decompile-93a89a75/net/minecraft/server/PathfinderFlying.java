package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

public class PathfinderFlying extends PathfinderNormal {

    public PathfinderFlying() {}

    @Override
    public void a(IWorldReader iworldreader, EntityInsentient entityinsentient) {
        super.a(iworldreader, entityinsentient);
        this.j = entityinsentient.a(PathType.WATER);
    }

    @Override
    public void a() {
        this.b.a(PathType.WATER, this.j);
        super.a();
    }

    @Override
    public PathPoint b() {
        int i;

        if (this.e() && this.b.isInWater()) {
            i = MathHelper.floor(this.b.getBoundingBox().minY);
            BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(this.b.locX, (double) i, this.b.locZ);

            for (Block block = this.a.getType(blockposition_mutableblockposition).getBlock(); block == Blocks.WATER; block = this.a.getType(blockposition_mutableblockposition).getBlock()) {
                ++i;
                blockposition_mutableblockposition.c(this.b.locX, (double) i, this.b.locZ);
            }
        } else {
            i = MathHelper.floor(this.b.getBoundingBox().minY + 0.5D);
        }

        BlockPosition blockposition = new BlockPosition(this.b);
        PathType pathtype = this.a(this.b, blockposition.getX(), i, blockposition.getZ());

        if (this.b.a(pathtype) < 0.0F) {
            Set<BlockPosition> set = Sets.newHashSet();

            set.add(new BlockPosition(this.b.getBoundingBox().minX, (double) i, this.b.getBoundingBox().minZ));
            set.add(new BlockPosition(this.b.getBoundingBox().minX, (double) i, this.b.getBoundingBox().maxZ));
            set.add(new BlockPosition(this.b.getBoundingBox().maxX, (double) i, this.b.getBoundingBox().minZ));
            set.add(new BlockPosition(this.b.getBoundingBox().maxX, (double) i, this.b.getBoundingBox().maxZ));
            Iterator iterator = set.iterator();

            while (iterator.hasNext()) {
                BlockPosition blockposition1 = (BlockPosition) iterator.next();
                PathType pathtype1 = this.a(this.b, blockposition1);

                if (this.b.a(pathtype1) >= 0.0F) {
                    return super.a(blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
                }
            }
        }

        return super.a(blockposition.getX(), i, blockposition.getZ());
    }

    @Override
    public PathDestination a(double d0, double d1, double d2) {
        return new PathDestination(super.a(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2)));
    }

    @Override
    public int a(PathPoint[] apathpoint, PathPoint pathpoint) {
        int i = 0;
        PathPoint pathpoint1 = this.a(pathpoint.a, pathpoint.b, pathpoint.c + 1);
        PathPoint pathpoint2 = this.a(pathpoint.a - 1, pathpoint.b, pathpoint.c);
        PathPoint pathpoint3 = this.a(pathpoint.a + 1, pathpoint.b, pathpoint.c);
        PathPoint pathpoint4 = this.a(pathpoint.a, pathpoint.b, pathpoint.c - 1);
        PathPoint pathpoint5 = this.a(pathpoint.a, pathpoint.b + 1, pathpoint.c);
        PathPoint pathpoint6 = this.a(pathpoint.a, pathpoint.b - 1, pathpoint.c);

        if (pathpoint1 != null && !pathpoint1.i) {
            apathpoint[i++] = pathpoint1;
        }

        if (pathpoint2 != null && !pathpoint2.i) {
            apathpoint[i++] = pathpoint2;
        }

        if (pathpoint3 != null && !pathpoint3.i) {
            apathpoint[i++] = pathpoint3;
        }

        if (pathpoint4 != null && !pathpoint4.i) {
            apathpoint[i++] = pathpoint4;
        }

        if (pathpoint5 != null && !pathpoint5.i) {
            apathpoint[i++] = pathpoint5;
        }

        if (pathpoint6 != null && !pathpoint6.i) {
            apathpoint[i++] = pathpoint6;
        }

        boolean flag = pathpoint4 == null || pathpoint4.k != 0.0F;
        boolean flag1 = pathpoint1 == null || pathpoint1.k != 0.0F;
        boolean flag2 = pathpoint3 == null || pathpoint3.k != 0.0F;
        boolean flag3 = pathpoint2 == null || pathpoint2.k != 0.0F;
        boolean flag4 = pathpoint5 == null || pathpoint5.k != 0.0F;
        boolean flag5 = pathpoint6 == null || pathpoint6.k != 0.0F;
        PathPoint pathpoint7;

        if (flag && flag3) {
            pathpoint7 = this.a(pathpoint.a - 1, pathpoint.b, pathpoint.c - 1);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag && flag2) {
            pathpoint7 = this.a(pathpoint.a + 1, pathpoint.b, pathpoint.c - 1);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag1 && flag3) {
            pathpoint7 = this.a(pathpoint.a - 1, pathpoint.b, pathpoint.c + 1);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag1 && flag2) {
            pathpoint7 = this.a(pathpoint.a + 1, pathpoint.b, pathpoint.c + 1);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag && flag4) {
            pathpoint7 = this.a(pathpoint.a, pathpoint.b + 1, pathpoint.c - 1);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag1 && flag4) {
            pathpoint7 = this.a(pathpoint.a, pathpoint.b + 1, pathpoint.c + 1);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag2 && flag4) {
            pathpoint7 = this.a(pathpoint.a + 1, pathpoint.b + 1, pathpoint.c);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag3 && flag4) {
            pathpoint7 = this.a(pathpoint.a - 1, pathpoint.b + 1, pathpoint.c);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag && flag5) {
            pathpoint7 = this.a(pathpoint.a, pathpoint.b - 1, pathpoint.c - 1);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag1 && flag5) {
            pathpoint7 = this.a(pathpoint.a, pathpoint.b - 1, pathpoint.c + 1);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag2 && flag5) {
            pathpoint7 = this.a(pathpoint.a + 1, pathpoint.b - 1, pathpoint.c);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        if (flag3 && flag5) {
            pathpoint7 = this.a(pathpoint.a - 1, pathpoint.b - 1, pathpoint.c);
            if (pathpoint7 != null && !pathpoint7.i) {
                apathpoint[i++] = pathpoint7;
            }
        }

        return i;
    }

    @Nullable
    @Override
    protected PathPoint a(int i, int j, int k) {
        PathPoint pathpoint = null;
        PathType pathtype = this.a(this.b, i, j, k);
        float f = this.b.a(pathtype);

        if (f >= 0.0F) {
            pathpoint = super.a(i, j, k);
            pathpoint.l = pathtype;
            pathpoint.k = Math.max(pathpoint.k, f);
            if (pathtype == PathType.WALKABLE) {
                ++pathpoint.k;
            }
        }

        return pathtype != PathType.OPEN && pathtype != PathType.WALKABLE ? pathpoint : pathpoint;
    }

    @Override
    public PathType a(IBlockAccess iblockaccess, int i, int j, int k, EntityInsentient entityinsentient, int l, int i1, int j1, boolean flag, boolean flag1) {
        EnumSet<PathType> enumset = EnumSet.noneOf(PathType.class);
        PathType pathtype = PathType.BLOCKED;
        BlockPosition blockposition = new BlockPosition(entityinsentient);

        pathtype = this.a(iblockaccess, i, j, k, l, i1, j1, flag, flag1, enumset, pathtype, blockposition);
        if (enumset.contains(PathType.FENCE)) {
            return PathType.FENCE;
        } else {
            PathType pathtype1 = PathType.BLOCKED;
            Iterator iterator = enumset.iterator();

            while (iterator.hasNext()) {
                PathType pathtype2 = (PathType) iterator.next();

                if (entityinsentient.a(pathtype2) < 0.0F) {
                    return pathtype2;
                }

                if (entityinsentient.a(pathtype2) >= entityinsentient.a(pathtype1)) {
                    pathtype1 = pathtype2;
                }
            }

            if (pathtype == PathType.OPEN && entityinsentient.a(pathtype1) == 0.0F) {
                return PathType.OPEN;
            } else {
                return pathtype1;
            }
        }
    }

    @Override
    public PathType a(IBlockAccess iblockaccess, int i, int j, int k) {
        PathType pathtype = this.b(iblockaccess, i, j, k);

        if (pathtype == PathType.OPEN && j >= 1) {
            Block block = iblockaccess.getType(new BlockPosition(i, j - 1, k)).getBlock();
            PathType pathtype1 = this.b(iblockaccess, i, j - 1, k);

            if (pathtype1 != PathType.DAMAGE_FIRE && block != Blocks.MAGMA_BLOCK && pathtype1 != PathType.LAVA && block != Blocks.CAMPFIRE) {
                if (pathtype1 == PathType.DAMAGE_CACTUS) {
                    pathtype = PathType.DAMAGE_CACTUS;
                } else if (pathtype1 == PathType.DAMAGE_OTHER) {
                    pathtype = PathType.DAMAGE_OTHER;
                } else {
                    pathtype = pathtype1 != PathType.WALKABLE && pathtype1 != PathType.OPEN && pathtype1 != PathType.WATER ? PathType.WALKABLE : PathType.OPEN;
                }
            } else {
                pathtype = PathType.DAMAGE_FIRE;
            }
        }

        pathtype = this.a(iblockaccess, i, j, k, pathtype);
        return pathtype;
    }

    private PathType a(EntityInsentient entityinsentient, BlockPosition blockposition) {
        return this.a(entityinsentient, blockposition.getX(), blockposition.getY(), blockposition.getZ());
    }

    private PathType a(EntityInsentient entityinsentient, int i, int j, int k) {
        return this.a(this.a, i, j, k, entityinsentient, this.d, this.e, this.f, this.d(), this.c());
    }
}

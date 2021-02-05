package net.minecraft.server;

public abstract class PathfinderGoalDoorInteract extends PathfinderGoal {

    protected EntityInsentient entity;
    protected BlockPosition door;
    protected boolean f;
    private boolean a;
    private float b;
    private float c;

    public PathfinderGoalDoorInteract(EntityInsentient entityinsentient) {
        this.door = BlockPosition.ZERO;
        this.entity = entityinsentient;
        if (!(entityinsentient.getNavigation() instanceof Navigation)) {
            throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
        }
    }

    protected boolean g() {
        if (!this.f) {
            return false;
        } else {
            IBlockData iblockdata = this.entity.world.getType(this.door);

            if (!(iblockdata.getBlock() instanceof BlockDoor)) {
                this.f = false;
                return false;
            } else {
                return (Boolean) iblockdata.get(BlockDoor.OPEN);
            }
        }
    }

    protected void a(boolean flag) {
        if (this.f) {
            IBlockData iblockdata = this.entity.world.getType(this.door);

            if (iblockdata.getBlock() instanceof BlockDoor) {
                ((BlockDoor) iblockdata.getBlock()).setDoor(this.entity.world, this.door, flag);
            }
        }

    }

    @Override
    public boolean a() {
        if (!this.entity.positionChanged) {
            return false;
        } else {
            Navigation navigation = (Navigation) this.entity.getNavigation();
            PathEntity pathentity = navigation.l();

            if (pathentity != null && !pathentity.b() && navigation.f()) {
                for (int i = 0; i < Math.min(pathentity.f() + 2, pathentity.e()); ++i) {
                    PathPoint pathpoint = pathentity.a(i);

                    this.door = new BlockPosition(pathpoint.a, pathpoint.b + 1, pathpoint.c);
                    if (this.entity.e((double) this.door.getX(), this.entity.locY, (double) this.door.getZ()) <= 2.25D) {
                        this.f = a(this.entity.world, this.door);
                        if (this.f) {
                            return true;
                        }
                    }
                }

                this.door = (new BlockPosition(this.entity)).up();
                this.f = a(this.entity.world, this.door);
                return this.f;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean b() {
        return !this.a;
    }

    @Override
    public void c() {
        this.a = false;
        this.b = (float) ((double) ((float) this.door.getX() + 0.5F) - this.entity.locX);
        this.c = (float) ((double) ((float) this.door.getZ() + 0.5F) - this.entity.locZ);
    }

    @Override
    public void e() {
        float f = (float) ((double) ((float) this.door.getX() + 0.5F) - this.entity.locX);
        float f1 = (float) ((double) ((float) this.door.getZ() + 0.5F) - this.entity.locZ);
        float f2 = this.b * f + this.c * f1;

        if (f2 < 0.0F) {
            this.a = true;
        }

    }

    public static boolean a(World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        return iblockdata.getBlock() instanceof BlockDoor && iblockdata.getMaterial() == Material.WOOD;
    }
}

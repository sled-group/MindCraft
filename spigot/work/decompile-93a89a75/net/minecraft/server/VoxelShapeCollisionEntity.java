package net.minecraft.server;

public class VoxelShapeCollisionEntity implements VoxelShapeCollision {

    protected static final VoxelShapeCollision a = new VoxelShapeCollisionEntity(false, -1.7976931348623157E308D, Items.AIR) {
        @Override
        public boolean a(VoxelShape voxelshape, BlockPosition blockposition, boolean flag) {
            return flag;
        }
    };
    private final boolean b;
    private final double c;
    private final Item d;

    protected VoxelShapeCollisionEntity(boolean flag, double d0, Item item) {
        this.b = flag;
        this.c = d0;
        this.d = item;
    }

    @Deprecated
    protected VoxelShapeCollisionEntity(Entity entity) {
        this(entity.isSneaking(), entity.getBoundingBox().minY, entity instanceof EntityLiving ? ((EntityLiving) entity).getItemInMainHand().getItem() : Items.AIR);
    }

    @Override
    public boolean a(Item item) {
        return this.d == item;
    }

    @Override
    public boolean b() {
        return this.b;
    }

    @Override
    public boolean a(VoxelShape voxelshape, BlockPosition blockposition, boolean flag) {
        return this.c > (double) blockposition.getY() + voxelshape.c(EnumDirection.EnumAxis.Y) - 9.999999747378752E-6D;
    }
}

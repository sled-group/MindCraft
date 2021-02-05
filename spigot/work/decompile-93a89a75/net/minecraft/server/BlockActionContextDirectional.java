package net.minecraft.server;

public class BlockActionContextDirectional extends BlockActionContext {

    private final EnumDirection g;

    public BlockActionContextDirectional(World world, BlockPosition blockposition, EnumDirection enumdirection, ItemStack itemstack, EnumDirection enumdirection1) {
        super(world, (EntityHuman) null, EnumHand.MAIN_HAND, itemstack, new MovingObjectPositionBlock(new Vec3D((double) blockposition.getX() + 0.5D, (double) blockposition.getY(), (double) blockposition.getZ() + 0.5D), enumdirection1, blockposition, false));
        this.g = enumdirection;
    }

    @Override
    public BlockPosition getClickPosition() {
        return this.d.getBlockPosition();
    }

    @Override
    public boolean b() {
        return this.e.getType(this.d.getBlockPosition()).a((BlockActionContext) this);
    }

    @Override
    public boolean c() {
        return this.b();
    }

    @Override
    public EnumDirection d() {
        return EnumDirection.DOWN;
    }

    @Override
    public EnumDirection[] e() {
        switch (this.g) {
            case DOWN:
            default:
                return new EnumDirection[]{EnumDirection.DOWN, EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.WEST, EnumDirection.UP};
            case UP:
                return new EnumDirection[]{EnumDirection.DOWN, EnumDirection.UP, EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.WEST};
            case NORTH:
                return new EnumDirection[]{EnumDirection.DOWN, EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.WEST, EnumDirection.UP, EnumDirection.SOUTH};
            case SOUTH:
                return new EnumDirection[]{EnumDirection.DOWN, EnumDirection.SOUTH, EnumDirection.EAST, EnumDirection.WEST, EnumDirection.UP, EnumDirection.NORTH};
            case WEST:
                return new EnumDirection[]{EnumDirection.DOWN, EnumDirection.WEST, EnumDirection.SOUTH, EnumDirection.UP, EnumDirection.NORTH, EnumDirection.EAST};
            case EAST:
                return new EnumDirection[]{EnumDirection.DOWN, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.UP, EnumDirection.NORTH, EnumDirection.WEST};
        }
    }

    @Override
    public EnumDirection f() {
        return this.g.k() == EnumDirection.EnumAxis.Y ? EnumDirection.NORTH : this.g;
    }

    @Override
    public boolean isSneaking() {
        return false;
    }

    @Override
    public float h() {
        return (float) (this.g.get2DRotationValue() * 90);
    }
}

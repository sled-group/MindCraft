package net.minecraft.server;

public class FluidTypeEmpty extends FluidType {

    public FluidTypeEmpty() {}

    @Override
    public Item b() {
        return Items.AIR;
    }

    @Override
    public boolean a(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition, FluidType fluidtype, EnumDirection enumdirection) {
        return true;
    }

    @Override
    public Vec3D a(IBlockAccess iblockaccess, BlockPosition blockposition, Fluid fluid) {
        return Vec3D.a;
    }

    @Override
    public int a(IWorldReader iworldreader) {
        return 0;
    }

    @Override
    protected boolean c() {
        return true;
    }

    @Override
    protected float d() {
        return 0.0F;
    }

    @Override
    public float a(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return 0.0F;
    }

    @Override
    public float a(Fluid fluid) {
        return 0.0F;
    }

    @Override
    protected IBlockData b(Fluid fluid) {
        return Blocks.AIR.getBlockData();
    }

    @Override
    public boolean c(Fluid fluid) {
        return false;
    }

    @Override
    public int d(Fluid fluid) {
        return 0;
    }

    @Override
    public VoxelShape b(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return VoxelShapes.a();
    }
}

package net.minecraft.server;

import java.util.Random;

public abstract class FluidType {

    public static final RegistryBlockID<Fluid> c = new RegistryBlockID<>();
    protected final BlockStateList<FluidType, Fluid> d;
    private Fluid a;

    protected FluidType() {
        BlockStateList.a<FluidType, Fluid> blockstatelist_a = new BlockStateList.a<>(this);

        this.a(blockstatelist_a);
        this.d = blockstatelist_a.a(FluidImpl::new);
        this.f((Fluid) this.d.getBlockData());
    }

    protected void a(BlockStateList.a<FluidType, Fluid> blockstatelist_a) {}

    public BlockStateList<FluidType, Fluid> h() {
        return this.d;
    }

    protected final void f(Fluid fluid) {
        this.a = fluid;
    }

    public final Fluid i() {
        return this.a;
    }

    public abstract Item b();

    protected void a(World world, BlockPosition blockposition, Fluid fluid) {}

    protected void b(World world, BlockPosition blockposition, Fluid fluid, Random random) {}

    protected abstract boolean a(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition, FluidType fluidtype, EnumDirection enumdirection);

    protected abstract Vec3D a(IBlockAccess iblockaccess, BlockPosition blockposition, Fluid fluid);

    public abstract int a(IWorldReader iworldreader);

    protected boolean k() {
        return false;
    }

    protected boolean c() {
        return false;
    }

    protected abstract float d();

    public abstract float a(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition);

    public abstract float a(Fluid fluid);

    protected abstract IBlockData b(Fluid fluid);

    public abstract boolean c(Fluid fluid);

    public abstract int d(Fluid fluid);

    public boolean a(FluidType fluidtype) {
        return fluidtype == this;
    }

    public boolean a(Tag<FluidType> tag) {
        return tag.isTagged(this);
    }

    public abstract VoxelShape b(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition);
}

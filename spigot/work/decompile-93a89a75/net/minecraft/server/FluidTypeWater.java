package net.minecraft.server;

public abstract class FluidTypeWater extends FluidTypeFlowing {

    public FluidTypeWater() {}

    @Override
    public FluidType e() {
        return FluidTypes.FLOWING_WATER;
    }

    @Override
    public FluidType f() {
        return FluidTypes.WATER;
    }

    @Override
    public Item b() {
        return Items.WATER_BUCKET;
    }

    @Override
    protected boolean g() {
        return true;
    }

    @Override
    protected void a(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata) {
        TileEntity tileentity = iblockdata.getBlock().isTileEntity() ? generatoraccess.getTileEntity(blockposition) : null;

        Block.a(iblockdata, generatoraccess.getMinecraftWorld(), blockposition, tileentity);
    }

    @Override
    public int b(IWorldReader iworldreader) {
        return 4;
    }

    @Override
    public IBlockData b(Fluid fluid) {
        return (IBlockData) Blocks.WATER.getBlockData().set(BlockFluids.LEVEL, e(fluid));
    }

    @Override
    public boolean a(FluidType fluidtype) {
        return fluidtype == FluidTypes.WATER || fluidtype == FluidTypes.FLOWING_WATER;
    }

    @Override
    public int c(IWorldReader iworldreader) {
        return 1;
    }

    @Override
    public int a(IWorldReader iworldreader) {
        return 5;
    }

    @Override
    public boolean a(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition, FluidType fluidtype, EnumDirection enumdirection) {
        return enumdirection == EnumDirection.DOWN && !fluidtype.a(TagsFluid.WATER);
    }

    @Override
    protected float d() {
        return 100.0F;
    }

    public static class a extends FluidTypeWater {

        public a() {}

        @Override
        protected void a(BlockStateList.a<FluidType, Fluid> blockstatelist_a) {
            super.a(blockstatelist_a);
            blockstatelist_a.a(FluidTypeWater.a.LEVEL);
        }

        @Override
        public int d(Fluid fluid) {
            return (Integer) fluid.get(FluidTypeWater.a.LEVEL);
        }

        @Override
        public boolean c(Fluid fluid) {
            return false;
        }
    }

    public static class b extends FluidTypeWater {

        public b() {}

        @Override
        public int d(Fluid fluid) {
            return 8;
        }

        @Override
        public boolean c(Fluid fluid) {
            return true;
        }
    }
}

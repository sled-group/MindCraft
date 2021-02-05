package net.minecraft.server;

public class BlockIronBars extends BlockTall {

    protected BlockIronBars(Block.Info block_info) {
        super(1.0F, 1.0F, 16.0F, 16.0F, 16.0F, block_info);
        this.o((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockIronBars.NORTH, false)).set(BlockIronBars.EAST, false)).set(BlockIronBars.SOUTH, false)).set(BlockIronBars.WEST, false)).set(BlockIronBars.e, false));
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        World world = blockactioncontext.getWorld();
        BlockPosition blockposition = blockactioncontext.getClickPosition();
        Fluid fluid = blockactioncontext.getWorld().getFluid(blockactioncontext.getClickPosition());
        BlockPosition blockposition1 = blockposition.north();
        BlockPosition blockposition2 = blockposition.south();
        BlockPosition blockposition3 = blockposition.west();
        BlockPosition blockposition4 = blockposition.east();
        IBlockData iblockdata = world.getType(blockposition1);
        IBlockData iblockdata1 = world.getType(blockposition2);
        IBlockData iblockdata2 = world.getType(blockposition3);
        IBlockData iblockdata3 = world.getType(blockposition4);

        return (IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) this.getBlockData().set(BlockIronBars.NORTH, this.a(iblockdata, iblockdata.d(world, blockposition1, EnumDirection.SOUTH)))).set(BlockIronBars.SOUTH, this.a(iblockdata1, iblockdata1.d(world, blockposition2, EnumDirection.NORTH)))).set(BlockIronBars.WEST, this.a(iblockdata2, iblockdata2.d(world, blockposition3, EnumDirection.EAST)))).set(BlockIronBars.EAST, this.a(iblockdata3, iblockdata3.d(world, blockposition4, EnumDirection.WEST)))).set(BlockIronBars.e, fluid.getType() == FluidTypes.WATER);
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        if ((Boolean) iblockdata.get(BlockIronBars.e)) {
            generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a((IWorldReader) generatoraccess));
        }

        return enumdirection.k().c() ? (IBlockData) iblockdata.set((IBlockState) BlockIronBars.f.get(enumdirection), this.a(iblockdata1, iblockdata1.d(generatoraccess, blockposition1, enumdirection.opposite()))) : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    public final boolean a(IBlockData iblockdata, boolean flag) {
        Block block = iblockdata.getBlock();

        return !a(block) && flag || block instanceof BlockIronBars;
    }

    @Override
    public TextureType c() {
        return TextureType.CUTOUT_MIPPED;
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockIronBars.NORTH, BlockIronBars.EAST, BlockIronBars.WEST, BlockIronBars.SOUTH, BlockIronBars.e);
    }
}

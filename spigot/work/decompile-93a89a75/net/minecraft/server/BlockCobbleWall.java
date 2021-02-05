package net.minecraft.server;

public class BlockCobbleWall extends BlockTall {

    public static final BlockStateBoolean UP = BlockProperties.F;
    private final VoxelShape[] j;
    private final VoxelShape[] k;

    public BlockCobbleWall(Block.Info block_info) {
        super(0.0F, 3.0F, 0.0F, 14.0F, 24.0F, block_info);
        this.o((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockCobbleWall.UP, true)).set(BlockCobbleWall.NORTH, false)).set(BlockCobbleWall.EAST, false)).set(BlockCobbleWall.SOUTH, false)).set(BlockCobbleWall.WEST, false)).set(BlockCobbleWall.e, false));
        this.j = this.a(4.0F, 3.0F, 16.0F, 0.0F, 14.0F);
        this.k = this.a(4.0F, 3.0F, 24.0F, 0.0F, 24.0F);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return (Boolean) iblockdata.get(BlockCobbleWall.UP) ? this.j[this.j(iblockdata)] : super.a(iblockdata, iblockaccess, blockposition, voxelshapecollision);
    }

    @Override
    public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return (Boolean) iblockdata.get(BlockCobbleWall.UP) ? this.k[this.j(iblockdata)] : super.b(iblockdata, iblockaccess, blockposition, voxelshapecollision);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        return false;
    }

    private boolean a(IBlockData iblockdata, boolean flag, EnumDirection enumdirection) {
        Block block = iblockdata.getBlock();
        boolean flag1 = block.a(TagsBlock.WALLS) || block instanceof BlockFenceGate && BlockFenceGate.a(iblockdata, enumdirection);

        return !a(block) && flag || flag1;
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        World world = blockactioncontext.getWorld();
        BlockPosition blockposition = blockactioncontext.getClickPosition();
        Fluid fluid = blockactioncontext.getWorld().getFluid(blockactioncontext.getClickPosition());
        BlockPosition blockposition1 = blockposition.north();
        BlockPosition blockposition2 = blockposition.east();
        BlockPosition blockposition3 = blockposition.south();
        BlockPosition blockposition4 = blockposition.west();
        IBlockData iblockdata = world.getType(blockposition1);
        IBlockData iblockdata1 = world.getType(blockposition2);
        IBlockData iblockdata2 = world.getType(blockposition3);
        IBlockData iblockdata3 = world.getType(blockposition4);
        boolean flag = this.a(iblockdata, iblockdata.d(world, blockposition1, EnumDirection.SOUTH), EnumDirection.SOUTH);
        boolean flag1 = this.a(iblockdata1, iblockdata1.d(world, blockposition2, EnumDirection.WEST), EnumDirection.WEST);
        boolean flag2 = this.a(iblockdata2, iblockdata2.d(world, blockposition3, EnumDirection.NORTH), EnumDirection.NORTH);
        boolean flag3 = this.a(iblockdata3, iblockdata3.d(world, blockposition4, EnumDirection.EAST), EnumDirection.EAST);
        boolean flag4 = (!flag || flag1 || !flag2 || flag3) && (flag || !flag1 || flag2 || !flag3);

        return (IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) this.getBlockData().set(BlockCobbleWall.UP, flag4 || !world.isEmpty(blockposition.up()))).set(BlockCobbleWall.NORTH, flag)).set(BlockCobbleWall.EAST, flag1)).set(BlockCobbleWall.SOUTH, flag2)).set(BlockCobbleWall.WEST, flag3)).set(BlockCobbleWall.e, fluid.getType() == FluidTypes.WATER);
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        if ((Boolean) iblockdata.get(BlockCobbleWall.e)) {
            generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a((IWorldReader) generatoraccess));
        }

        if (enumdirection == EnumDirection.DOWN) {
            return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
        } else {
            EnumDirection enumdirection1 = enumdirection.opposite();
            boolean flag = enumdirection == EnumDirection.NORTH ? this.a(iblockdata1, iblockdata1.d(generatoraccess, blockposition1, enumdirection1), enumdirection1) : (Boolean) iblockdata.get(BlockCobbleWall.NORTH);
            boolean flag1 = enumdirection == EnumDirection.EAST ? this.a(iblockdata1, iblockdata1.d(generatoraccess, blockposition1, enumdirection1), enumdirection1) : (Boolean) iblockdata.get(BlockCobbleWall.EAST);
            boolean flag2 = enumdirection == EnumDirection.SOUTH ? this.a(iblockdata1, iblockdata1.d(generatoraccess, blockposition1, enumdirection1), enumdirection1) : (Boolean) iblockdata.get(BlockCobbleWall.SOUTH);
            boolean flag3 = enumdirection == EnumDirection.WEST ? this.a(iblockdata1, iblockdata1.d(generatoraccess, blockposition1, enumdirection1), enumdirection1) : (Boolean) iblockdata.get(BlockCobbleWall.WEST);
            boolean flag4 = (!flag || flag1 || !flag2 || flag3) && (flag || !flag1 || flag2 || !flag3);

            return (IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) iblockdata.set(BlockCobbleWall.UP, flag4 || !generatoraccess.isEmpty(blockposition.up()))).set(BlockCobbleWall.NORTH, flag)).set(BlockCobbleWall.EAST, flag1)).set(BlockCobbleWall.SOUTH, flag2)).set(BlockCobbleWall.WEST, flag3);
        }
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockCobbleWall.UP, BlockCobbleWall.NORTH, BlockCobbleWall.EAST, BlockCobbleWall.WEST, BlockCobbleWall.SOUTH, BlockCobbleWall.e);
    }
}

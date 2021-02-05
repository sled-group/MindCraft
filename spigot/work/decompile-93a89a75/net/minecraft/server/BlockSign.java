package net.minecraft.server;

public abstract class BlockSign extends BlockTileEntity implements IBlockWaterlogged {

    public static final BlockStateBoolean a = BlockProperties.C;
    protected static final VoxelShape b = Block.a(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    protected BlockSign(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        if ((Boolean) iblockdata.get(BlockSign.a)) {
            generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a((IWorldReader) generatoraccess));
        }

        return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockSign.b;
    }

    @Override
    public boolean S_() {
        return true;
    }

    @Override
    public TileEntity createTile(IBlockAccess iblockaccess) {
        return new TileEntitySign();
    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        if (world.isClientSide) {
            return true;
        } else {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntitySign) {
                TileEntitySign tileentitysign = (TileEntitySign) tileentity;
                ItemStack itemstack = entityhuman.b(enumhand);

                if (itemstack.getItem() instanceof ItemDye && entityhuman.abilities.mayBuild) {
                    boolean flag = tileentitysign.setColor(((ItemDye) itemstack.getItem()).d());

                    if (flag && !entityhuman.isCreative()) {
                        itemstack.subtract(1);
                    }
                }

                return tileentitysign.b(entityhuman);
            } else {
                return false;
            }
        }
    }

    @Override
    public Fluid g(IBlockData iblockdata) {
        return (Boolean) iblockdata.get(BlockSign.a) ? FluidTypes.WATER.a(false) : super.g(iblockdata);
    }
}

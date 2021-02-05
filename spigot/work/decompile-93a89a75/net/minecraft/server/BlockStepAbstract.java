package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockStepAbstract extends Block implements IBlockWaterlogged {

    public static final BlockStateEnum<BlockPropertySlabType> a = BlockProperties.aC;
    public static final BlockStateBoolean b = BlockProperties.C;
    protected static final VoxelShape c = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape d = Block.a(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public BlockStepAbstract(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) this.getBlockData().set(BlockStepAbstract.a, BlockPropertySlabType.BOTTOM)).set(BlockStepAbstract.b, false));
    }

    @Override
    public boolean n(IBlockData iblockdata) {
        return iblockdata.get(BlockStepAbstract.a) != BlockPropertySlabType.DOUBLE;
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockStepAbstract.a, BlockStepAbstract.b);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        BlockPropertySlabType blockpropertyslabtype = (BlockPropertySlabType) iblockdata.get(BlockStepAbstract.a);

        switch (blockpropertyslabtype) {
            case DOUBLE:
                return VoxelShapes.b();
            case TOP:
                return BlockStepAbstract.d;
            default:
                return BlockStepAbstract.c;
        }
    }

    @Nullable
    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        BlockPosition blockposition = blockactioncontext.getClickPosition();
        IBlockData iblockdata = blockactioncontext.getWorld().getType(blockposition);

        if (iblockdata.getBlock() == this) {
            return (IBlockData) ((IBlockData) iblockdata.set(BlockStepAbstract.a, BlockPropertySlabType.DOUBLE)).set(BlockStepAbstract.b, false);
        } else {
            Fluid fluid = blockactioncontext.getWorld().getFluid(blockposition);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) this.getBlockData().set(BlockStepAbstract.a, BlockPropertySlabType.BOTTOM)).set(BlockStepAbstract.b, fluid.getType() == FluidTypes.WATER);
            EnumDirection enumdirection = blockactioncontext.getClickedFace();

            return enumdirection != EnumDirection.DOWN && (enumdirection == EnumDirection.UP || blockactioncontext.j().y - (double) blockposition.getY() <= 0.5D) ? iblockdata1 : (IBlockData) iblockdata1.set(BlockStepAbstract.a, BlockPropertySlabType.TOP);
        }
    }

    @Override
    public boolean a(IBlockData iblockdata, BlockActionContext blockactioncontext) {
        ItemStack itemstack = blockactioncontext.getItemStack();
        BlockPropertySlabType blockpropertyslabtype = (BlockPropertySlabType) iblockdata.get(BlockStepAbstract.a);

        if (blockpropertyslabtype != BlockPropertySlabType.DOUBLE && itemstack.getItem() == this.getItem()) {
            if (blockactioncontext.c()) {
                boolean flag = blockactioncontext.j().y - (double) blockactioncontext.getClickPosition().getY() > 0.5D;
                EnumDirection enumdirection = blockactioncontext.getClickedFace();

                return blockpropertyslabtype == BlockPropertySlabType.BOTTOM ? enumdirection == EnumDirection.UP || flag && enumdirection.k().c() : enumdirection == EnumDirection.DOWN || !flag && enumdirection.k().c();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public Fluid g(IBlockData iblockdata) {
        return (Boolean) iblockdata.get(BlockStepAbstract.b) ? FluidTypes.WATER.a(false) : super.g(iblockdata);
    }

    @Override
    public boolean place(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata, Fluid fluid) {
        return iblockdata.get(BlockStepAbstract.a) != BlockPropertySlabType.DOUBLE ? IBlockWaterlogged.super.place(generatoraccess, blockposition, iblockdata, fluid) : false;
    }

    @Override
    public boolean canPlace(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, FluidType fluidtype) {
        return iblockdata.get(BlockStepAbstract.a) != BlockPropertySlabType.DOUBLE ? IBlockWaterlogged.super.canPlace(iblockaccess, blockposition, iblockdata, fluidtype) : false;
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        if ((Boolean) iblockdata.get(BlockStepAbstract.b)) {
            generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a((IWorldReader) generatoraccess));
        }

        return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        switch (pathmode) {
            case LAND:
                return false;
            case WATER:
                return iblockaccess.getFluid(blockposition).a(TagsFluid.WATER);
            case AIR:
                return false;
            default:
                return false;
        }
    }
}

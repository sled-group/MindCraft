package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockTallSeaGrass extends BlockTallPlantShearable implements IFluidContainer {

    public static final BlockStateEnum<BlockPropertyDoubleBlockHalf> c = BlockTallPlantShearable.b;
    protected static final VoxelShape d = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public BlockTallSeaGrass(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockTallSeaGrass.d;
    }

    @Override
    protected boolean a_(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return iblockdata.d(iblockaccess, blockposition, EnumDirection.UP) && iblockdata.getBlock() != Blocks.MAGMA_BLOCK;
    }

    @Nullable
    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        IBlockData iblockdata = super.getPlacedState(blockactioncontext);

        if (iblockdata != null) {
            Fluid fluid = blockactioncontext.getWorld().getFluid(blockactioncontext.getClickPosition().up());

            if (fluid.a(TagsFluid.WATER) && fluid.g() == 8) {
                return iblockdata;
            }
        }

        return null;
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        if (iblockdata.get(BlockTallSeaGrass.c) == BlockPropertyDoubleBlockHalf.UPPER) {
            IBlockData iblockdata1 = iworldreader.getType(blockposition.down());

            return iblockdata1.getBlock() == this && iblockdata1.get(BlockTallSeaGrass.c) == BlockPropertyDoubleBlockHalf.LOWER;
        } else {
            Fluid fluid = iworldreader.getFluid(blockposition);

            return super.canPlace(iblockdata, iworldreader, blockposition) && fluid.a(TagsFluid.WATER) && fluid.g() == 8;
        }
    }

    @Override
    public Fluid g(IBlockData iblockdata) {
        return FluidTypes.WATER.a(false);
    }

    @Override
    public boolean canPlace(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, FluidType fluidtype) {
        return false;
    }

    @Override
    public boolean place(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata, Fluid fluid) {
        return false;
    }
}

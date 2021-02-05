package net.minecraft.server;

public interface IBlockWaterlogged extends IFluidSource, IFluidContainer {

    @Override
    default boolean canPlace(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, FluidType fluidtype) {
        return !(Boolean) iblockdata.get(BlockProperties.C) && fluidtype == FluidTypes.WATER;
    }

    @Override
    default boolean place(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata, Fluid fluid) {
        if (!(Boolean) iblockdata.get(BlockProperties.C) && fluid.getType() == FluidTypes.WATER) {
            if (!generatoraccess.e()) {
                generatoraccess.setTypeAndData(blockposition, (IBlockData) iblockdata.set(BlockProperties.C, true), 3);
                generatoraccess.getFluidTickList().a(blockposition, fluid.getType(), fluid.getType().a((IWorldReader) generatoraccess));
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    default FluidType removeFluid(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata) {
        if ((Boolean) iblockdata.get(BlockProperties.C)) {
            generatoraccess.setTypeAndData(blockposition, (IBlockData) iblockdata.set(BlockProperties.C, false), 3);
            return FluidTypes.WATER;
        } else {
            return FluidTypes.EMPTY;
        }
    }
}

package net.minecraft.server;

public interface IFluidSource {

    FluidType removeFluid(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata);
}

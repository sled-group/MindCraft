package net.minecraft.server;

import java.util.Random;

public class BlockKelpPlant extends Block implements IFluidContainer {

    private final BlockKelp a;

    protected BlockKelpPlant(BlockKelp blockkelp, Block.Info block_info) {
        super(block_info);
        this.a = blockkelp;
    }

    @Override
    public TextureType c() {
        return TextureType.CUTOUT;
    }

    @Override
    public Fluid g(IBlockData iblockdata) {
        return FluidTypes.WATER.a(false);
    }

    @Override
    public void tick(IBlockData iblockdata, World world, BlockPosition blockposition, Random random) {
        if (!iblockdata.canPlace(world, blockposition)) {
            world.b(blockposition, true);
        }

        super.tick(iblockdata, world, blockposition, random);
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        if (enumdirection == EnumDirection.DOWN && !iblockdata.canPlace(generatoraccess, blockposition)) {
            generatoraccess.getBlockTickList().a(blockposition, this, 1);
        }

        if (enumdirection == EnumDirection.UP) {
            Block block = iblockdata1.getBlock();

            if (block != this && block != this.a) {
                return this.a.a(generatoraccess);
            }
        }

        generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a((IWorldReader) generatoraccess));
        return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        BlockPosition blockposition1 = blockposition.down();
        IBlockData iblockdata1 = iworldreader.getType(blockposition1);
        Block block = iblockdata1.getBlock();

        return block != Blocks.MAGMA_BLOCK && (block == this || iblockdata1.d(iworldreader, blockposition1, EnumDirection.UP));
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

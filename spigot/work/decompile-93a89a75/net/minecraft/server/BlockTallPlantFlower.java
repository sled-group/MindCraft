package net.minecraft.server;

import java.util.Random;

public class BlockTallPlantFlower extends BlockTallPlant implements IBlockFragilePlantElement {

    public BlockTallPlantFlower(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public boolean a(IBlockData iblockdata, BlockActionContext blockactioncontext) {
        return false;
    }

    @Override
    public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
        return true;
    }

    @Override
    public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        return true;
    }

    @Override
    public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        a(world, blockposition, new ItemStack(this));
    }
}

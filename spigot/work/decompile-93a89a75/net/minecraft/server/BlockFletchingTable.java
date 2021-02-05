package net.minecraft.server;

public class BlockFletchingTable extends BlockWorkbench {

    protected BlockFletchingTable(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        return false;
    }
}

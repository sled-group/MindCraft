package net.minecraft.server;

public class BlockPowered extends Block {

    public BlockPowered(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public boolean isPowerSource(IBlockData iblockdata) {
        return true;
    }

    @Override
    public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
        return 15;
    }
}

package net.minecraft.server;

public class BlockBedrock extends Block {

    public BlockBedrock(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EntityTypes<?> entitytypes) {
        return false;
    }
}

package net.minecraft.server;

public class BlockEndGateway extends BlockTileEntity {

    protected BlockEndGateway(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public TileEntity createTile(IBlockAccess iblockaccess) {
        return new TileEntityEndGateway();
    }
}

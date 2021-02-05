package net.minecraft.server;

public abstract class BlockSkullAbstract extends BlockTileEntity {

    private final BlockSkull.a a;

    public BlockSkullAbstract(BlockSkull.a blockskull_a, Block.Info block_info) {
        super(block_info);
        this.a = blockskull_a;
    }

    @Override
    public TileEntity createTile(IBlockAccess iblockaccess) {
        return new TileEntitySkull();
    }
}

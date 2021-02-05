package net.minecraft.server;

public class BlockLogAbstract extends BlockRotatable {

    private final MaterialMapColor b;

    public BlockLogAbstract(MaterialMapColor materialmapcolor, Block.Info block_info) {
        super(block_info);
        this.b = materialmapcolor;
    }

    @Override
    public MaterialMapColor e(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return iblockdata.get(BlockLogAbstract.AXIS) == EnumDirection.EnumAxis.Y ? this.b : this.t;
    }
}

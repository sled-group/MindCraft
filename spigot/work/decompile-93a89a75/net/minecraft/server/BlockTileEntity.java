package net.minecraft.server;

import javax.annotation.Nullable;

public abstract class BlockTileEntity extends Block implements ITileEntity {

    protected BlockTileEntity(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public EnumRenderType c(IBlockData iblockdata) {
        return EnumRenderType.INVISIBLE;
    }

    @Override
    public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, int i, int j) {
        super.a(iblockdata, world, blockposition, i, j);
        TileEntity tileentity = world.getTileEntity(blockposition);

        return tileentity == null ? false : tileentity.setProperty(i, j);
    }

    @Nullable
    @Override
    public ITileInventory getInventory(IBlockData iblockdata, World world, BlockPosition blockposition) {
        TileEntity tileentity = world.getTileEntity(blockposition);

        return tileentity instanceof ITileInventory ? (ITileInventory) tileentity : null;
    }
}

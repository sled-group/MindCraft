package net.minecraft.server;

import javax.annotation.Nullable;

public interface ITileEntity {

    @Nullable
    TileEntity createTile(IBlockAccess iblockaccess);
}

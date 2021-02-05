package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockCartographyTable extends Block {

    private static final ChatMessage a = new ChatMessage("container.cartography_table", new Object[0]);

    protected BlockCartographyTable(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        entityhuman.openContainer(iblockdata.b(world, blockposition));
        entityhuman.a(StatisticList.INTERACT_WITH_CARTOGRAPHY_TABLE);
        return true;
    }

    @Nullable
    @Override
    public ITileInventory getInventory(IBlockData iblockdata, World world, BlockPosition blockposition) {
        return new TileInventory((i, playerinventory, entityhuman) -> {
            return new ContainerCartography(i, playerinventory, ContainerAccess.at(world, blockposition));
        }, BlockCartographyTable.a);
    }
}

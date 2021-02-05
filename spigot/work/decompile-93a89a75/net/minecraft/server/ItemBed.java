package net.minecraft.server;

public class ItemBed extends ItemBlock {

    public ItemBed(Block block, Item.Info item_info) {
        super(block, item_info);
    }

    @Override
    protected boolean a(BlockActionContext blockactioncontext, IBlockData iblockdata) {
        return blockactioncontext.getWorld().setTypeAndData(blockactioncontext.getClickPosition(), iblockdata, 26);
    }
}

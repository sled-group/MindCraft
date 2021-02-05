package net.minecraft.server;

public class ItemBisected extends ItemBlock {

    public ItemBisected(Block block, Item.Info item_info) {
        super(block, item_info);
    }

    @Override
    protected boolean a(BlockActionContext blockactioncontext, IBlockData iblockdata) {
        blockactioncontext.getWorld().setTypeAndData(blockactioncontext.getClickPosition().up(), Blocks.AIR.getBlockData(), 27);
        return super.a(blockactioncontext, iblockdata);
    }
}

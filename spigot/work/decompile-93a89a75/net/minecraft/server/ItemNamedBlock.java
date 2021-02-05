package net.minecraft.server;

public class ItemNamedBlock extends ItemBlock {

    public ItemNamedBlock(Block block, Item.Info item_info) {
        super(block, item_info);
    }

    @Override
    public String getName() {
        return this.l();
    }
}

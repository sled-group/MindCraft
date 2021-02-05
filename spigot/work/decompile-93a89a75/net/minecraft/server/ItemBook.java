package net.minecraft.server;

public class ItemBook extends Item {

    public ItemBook(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public boolean g_(ItemStack itemstack) {
        return itemstack.getCount() == 1;
    }

    @Override
    public int c() {
        return 1;
    }
}

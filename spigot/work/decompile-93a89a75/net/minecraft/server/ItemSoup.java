package net.minecraft.server;

public class ItemSoup extends Item {

    public ItemSoup(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public ItemStack a(ItemStack itemstack, World world, EntityLiving entityliving) {
        super.a(itemstack, world, entityliving);
        return new ItemStack(Items.BOWL);
    }
}

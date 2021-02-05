package net.minecraft.server;

import javax.annotation.Nullable;

public class ItemWorldMapBase extends Item {

    public ItemWorldMapBase(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public boolean O_() {
        return true;
    }

    @Nullable
    public Packet<?> a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        return null;
    }
}

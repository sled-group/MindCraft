package net.minecraft.server;

public class ItemItemFrame extends ItemHanging {

    public ItemItemFrame(Item.Info item_info) {
        super(EntityTypes.ITEM_FRAME, item_info);
    }

    @Override
    protected boolean a(EntityHuman entityhuman, EnumDirection enumdirection, ItemStack itemstack, BlockPosition blockposition) {
        return !World.isOutsideWorld(blockposition) && entityhuman.a(blockposition, enumdirection, itemstack);
    }
}

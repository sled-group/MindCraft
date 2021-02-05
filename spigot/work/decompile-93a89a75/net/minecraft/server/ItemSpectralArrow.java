package net.minecraft.server;

public class ItemSpectralArrow extends ItemArrow {

    public ItemSpectralArrow(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public EntityArrow a(World world, ItemStack itemstack, EntityLiving entityliving) {
        return new EntitySpectralArrow(world, entityliving);
    }
}

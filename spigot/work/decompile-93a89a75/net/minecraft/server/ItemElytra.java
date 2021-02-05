package net.minecraft.server;

public class ItemElytra extends Item {

    public ItemElytra(Item.Info item_info) {
        super(item_info);
        this.a(new MinecraftKey("broken"), (itemstack, world, entityliving) -> {
            return e(itemstack) ? 0.0F : 1.0F;
        });
        BlockDispenser.a((IMaterial) this, ItemArmor.a);
    }

    public static boolean e(ItemStack itemstack) {
        return itemstack.getDamage() < itemstack.h() - 1;
    }

    @Override
    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return itemstack1.getItem() == Items.PHANTOM_MEMBRANE;
    }

    @Override
    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);
        EnumItemSlot enumitemslot = EntityInsentient.h(itemstack);
        ItemStack itemstack1 = entityhuman.getEquipment(enumitemslot);

        if (itemstack1.isEmpty()) {
            entityhuman.setSlot(enumitemslot, itemstack.cloneItemStack());
            itemstack.setCount(0);
            return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
        } else {
            return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
        }
    }
}

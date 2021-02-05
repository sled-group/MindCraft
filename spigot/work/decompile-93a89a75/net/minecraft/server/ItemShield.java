package net.minecraft.server;

public class ItemShield extends Item {

    public ItemShield(Item.Info item_info) {
        super(item_info);
        this.a(new MinecraftKey("blocking"), (itemstack, world, entityliving) -> {
            return entityliving != null && entityliving.isHandRaised() && entityliving.dl() == itemstack ? 1.0F : 0.0F;
        });
        BlockDispenser.a((IMaterial) this, ItemArmor.a);
    }

    @Override
    public String f(ItemStack itemstack) {
        return itemstack.b("BlockEntityTag") != null ? this.getName() + '.' + e(itemstack).b() : super.f(itemstack);
    }

    @Override
    public EnumAnimation e_(ItemStack itemstack) {
        return EnumAnimation.BLOCK;
    }

    @Override
    public int f_(ItemStack itemstack) {
        return 72000;
    }

    @Override
    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);

        entityhuman.c(enumhand);
        return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
    }

    @Override
    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return TagsItem.PLANKS.isTagged(itemstack1.getItem()) || super.a(itemstack, itemstack1);
    }

    public static EnumColor e(ItemStack itemstack) {
        return EnumColor.fromColorIndex(itemstack.a("BlockEntityTag").getInt("Base"));
    }
}

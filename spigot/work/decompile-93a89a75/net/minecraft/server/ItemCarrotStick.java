package net.minecraft.server;

public class ItemCarrotStick extends Item {

    public ItemCarrotStick(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);

        if (world.isClientSide) {
            return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
        } else {
            if (entityhuman.isPassenger() && entityhuman.getVehicle() instanceof EntityPig) {
                EntityPig entitypig = (EntityPig) entityhuman.getVehicle();

                if (itemstack.h() - itemstack.getDamage() >= 7 && entitypig.dW()) {
                    itemstack.damage(7, entityhuman, (entityhuman1) -> {
                        entityhuman1.d(enumhand);
                    });
                    if (itemstack.isEmpty()) {
                        ItemStack itemstack1 = new ItemStack(Items.FISHING_ROD);

                        itemstack1.setTag(itemstack.getTag());
                        return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack1);
                    }

                    return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
                }
            }

            entityhuman.b(StatisticList.ITEM_USED.b(this));
            return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
        }
    }
}

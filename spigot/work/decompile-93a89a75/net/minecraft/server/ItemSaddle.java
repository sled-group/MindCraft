package net.minecraft.server;

public class ItemSaddle extends Item {

    public ItemSaddle(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public boolean a(ItemStack itemstack, EntityHuman entityhuman, EntityLiving entityliving, EnumHand enumhand) {
        if (entityliving instanceof EntityPig) {
            EntityPig entitypig = (EntityPig) entityliving;

            if (entitypig.isAlive() && !entitypig.hasSaddle() && !entitypig.isBaby()) {
                entitypig.setSaddle(true);
                entitypig.world.playSound(entityhuman, entitypig.locX, entitypig.locY, entitypig.locZ, SoundEffects.ENTITY_PIG_SADDLE, SoundCategory.NEUTRAL, 0.5F, 1.0F);
                itemstack.subtract(1);
            }

            return true;
        } else {
            return false;
        }
    }
}

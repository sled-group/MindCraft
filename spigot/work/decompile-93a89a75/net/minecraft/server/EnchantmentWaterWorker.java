package net.minecraft.server;

public class EnchantmentWaterWorker extends Enchantment {

    public EnchantmentWaterWorker(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.ARMOR_HEAD, aenumitemslot);
    }

    @Override
    public int a(int i) {
        return 1;
    }

    @Override
    public int b(int i) {
        return this.a(i) + 40;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}

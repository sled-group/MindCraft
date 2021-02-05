package net.minecraft.server;

public class EnchantmentArrowKnockback extends Enchantment {

    public EnchantmentArrowKnockback(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.BOW, aenumitemslot);
    }

    @Override
    public int a(int i) {
        return 12 + (i - 1) * 20;
    }

    @Override
    public int b(int i) {
        return this.a(i) + 25;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}

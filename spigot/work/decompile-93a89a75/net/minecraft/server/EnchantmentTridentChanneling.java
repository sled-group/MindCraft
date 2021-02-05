package net.minecraft.server;

public class EnchantmentTridentChanneling extends Enchantment {

    public EnchantmentTridentChanneling(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.TRIDENT, aenumitemslot);
    }

    @Override
    public int a(int i) {
        return 25;
    }

    @Override
    public int b(int i) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean a(Enchantment enchantment) {
        return super.a(enchantment);
    }
}

package net.minecraft.server;

public class EnchantmentKnockback extends Enchantment {

    protected EnchantmentKnockback(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.WEAPON, aenumitemslot);
    }

    @Override
    public int a(int i) {
        return 5 + 20 * (i - 1);
    }

    @Override
    public int b(int i) {
        return super.a(i) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}

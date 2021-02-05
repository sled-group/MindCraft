package net.minecraft.server;

public class EnchantmentSweeping extends Enchantment {

    public EnchantmentSweeping(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.WEAPON, aenumitemslot);
    }

    @Override
    public int a(int i) {
        return 5 + (i - 1) * 9;
    }

    @Override
    public int b(int i) {
        return this.a(i) + 15;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public static float e(int i) {
        return 1.0F - 1.0F / (float) (i + 1);
    }
}

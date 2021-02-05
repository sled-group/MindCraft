package net.minecraft.server;

public class EnchantmentQuickCharge extends Enchantment {

    public EnchantmentQuickCharge(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.CROSSBOW, aenumitemslot);
    }

    @Override
    public int a(int i) {
        return 12 + (i - 1) * 20;
    }

    @Override
    public int b(int i) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}

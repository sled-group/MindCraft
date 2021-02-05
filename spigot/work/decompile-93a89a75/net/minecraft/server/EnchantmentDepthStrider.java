package net.minecraft.server;

public class EnchantmentDepthStrider extends Enchantment {

    public EnchantmentDepthStrider(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.ARMOR_FEET, aenumitemslot);
    }

    @Override
    public int a(int i) {
        return i * 10;
    }

    @Override
    public int b(int i) {
        return this.a(i) + 15;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean a(Enchantment enchantment) {
        return super.a(enchantment) && enchantment != Enchantments.FROST_WALKER;
    }
}

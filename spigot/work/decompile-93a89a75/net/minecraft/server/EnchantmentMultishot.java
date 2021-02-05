package net.minecraft.server;

public class EnchantmentMultishot extends Enchantment {

    public EnchantmentMultishot(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.CROSSBOW, aenumitemslot);
    }

    @Override
    public int a(int i) {
        return 20;
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
        return super.a(enchantment) && enchantment != Enchantments.PIERCING;
    }
}

package net.minecraft.server;

public class EnchantmentSilkTouch extends Enchantment {

    protected EnchantmentSilkTouch(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.DIGGER, aenumitemslot);
    }

    @Override
    public int a(int i) {
        return 15;
    }

    @Override
    public int b(int i) {
        return super.a(i) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean a(Enchantment enchantment) {
        return super.a(enchantment) && enchantment != Enchantments.LOOT_BONUS_BLOCKS;
    }
}

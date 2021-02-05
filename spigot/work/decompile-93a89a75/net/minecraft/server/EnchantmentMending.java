package net.minecraft.server;

public class EnchantmentMending extends Enchantment {

    public EnchantmentMending(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.BREAKABLE, aenumitemslot);
    }

    @Override
    public int a(int i) {
        return i * 25;
    }

    @Override
    public int b(int i) {
        return this.a(i) + 50;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}

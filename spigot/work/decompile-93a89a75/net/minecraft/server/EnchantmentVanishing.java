package net.minecraft.server;

public class EnchantmentVanishing extends Enchantment {

    public EnchantmentVanishing(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.ALL, aenumitemslot);
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
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean c() {
        return true;
    }
}

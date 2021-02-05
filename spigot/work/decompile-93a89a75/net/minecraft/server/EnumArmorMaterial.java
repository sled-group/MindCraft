package net.minecraft.server;

import java.util.function.Supplier;

public enum EnumArmorMaterial implements ArmorMaterial {

    LEATHER("leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEffects.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> {
        return RecipeItemStack.a(Items.LEATHER);
    }), CHAIN("chainmail", 15, new int[]{1, 4, 5, 2}, 12, SoundEffects.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, () -> {
        return RecipeItemStack.a(Items.IRON_INGOT);
    }), IRON("iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEffects.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> {
        return RecipeItemStack.a(Items.IRON_INGOT);
    }), GOLD("gold", 7, new int[]{1, 3, 5, 2}, 25, SoundEffects.ITEM_ARMOR_EQUIP_GOLD, 0.0F, () -> {
        return RecipeItemStack.a(Items.GOLD_INGOT);
    }), DIAMOND("diamond", 33, new int[]{3, 6, 8, 3}, 10, SoundEffects.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, () -> {
        return RecipeItemStack.a(Items.DIAMOND);
    }), TURTLE("turtle", 25, new int[]{2, 5, 6, 2}, 9, SoundEffects.ITEM_ARMOR_EQUIP_TURTLE, 0.0F, () -> {
        return RecipeItemStack.a(Items.SCUTE);
    });

    private static final int[] g = new int[]{13, 15, 16, 11};
    private final String h;
    private final int i;
    private final int[] j;
    private final int k;
    private final SoundEffect l;
    private final float m;
    private final LazyInitVar<RecipeItemStack> n;

    private EnumArmorMaterial(String s, int i, int[] aint, int j, SoundEffect soundeffect, float f, Supplier supplier) {
        this.h = s;
        this.i = i;
        this.j = aint;
        this.k = j;
        this.l = soundeffect;
        this.m = f;
        this.n = new LazyInitVar<>(supplier);
    }

    @Override
    public int a(EnumItemSlot enumitemslot) {
        return EnumArmorMaterial.g[enumitemslot.b()] * this.i;
    }

    @Override
    public int b(EnumItemSlot enumitemslot) {
        return this.j[enumitemslot.b()];
    }

    @Override
    public int a() {
        return this.k;
    }

    @Override
    public SoundEffect b() {
        return this.l;
    }

    @Override
    public RecipeItemStack c() {
        return (RecipeItemStack) this.n.a();
    }

    @Override
    public float e() {
        return this.m;
    }
}

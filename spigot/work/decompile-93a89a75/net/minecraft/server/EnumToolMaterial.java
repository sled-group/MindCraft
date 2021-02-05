package net.minecraft.server;

import java.util.function.Supplier;

public enum EnumToolMaterial implements ToolMaterial {

    WOOD(0, 59, 2.0F, 0.0F, 15, () -> {
        return RecipeItemStack.a(TagsItem.PLANKS);
    }), STONE(1, 131, 4.0F, 1.0F, 5, () -> {
        return RecipeItemStack.a(Blocks.COBBLESTONE);
    }), IRON(2, 250, 6.0F, 2.0F, 14, () -> {
        return RecipeItemStack.a(Items.IRON_INGOT);
    }), DIAMOND(3, 1561, 8.0F, 3.0F, 10, () -> {
        return RecipeItemStack.a(Items.DIAMOND);
    }), GOLD(0, 32, 12.0F, 0.0F, 22, () -> {
        return RecipeItemStack.a(Items.GOLD_INGOT);
    });

    private final int f;
    private final int g;
    private final float h;
    private final float i;
    private final int j;
    private final LazyInitVar<RecipeItemStack> k;

    private EnumToolMaterial(int i, int j, float f, float f1, int k, Supplier supplier) {
        this.f = i;
        this.g = j;
        this.h = f;
        this.i = f1;
        this.j = k;
        this.k = new LazyInitVar<>(supplier);
    }

    @Override
    public int a() {
        return this.g;
    }

    @Override
    public float b() {
        return this.h;
    }

    @Override
    public float c() {
        return this.i;
    }

    @Override
    public int d() {
        return this.f;
    }

    @Override
    public int e() {
        return this.j;
    }

    @Override
    public RecipeItemStack f() {
        return (RecipeItemStack) this.k.a();
    }
}

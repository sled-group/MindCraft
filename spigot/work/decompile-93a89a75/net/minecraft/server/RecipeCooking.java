package net.minecraft.server;

public abstract class RecipeCooking implements IRecipe<IInventory> {

    protected final Recipes<?> a;
    protected final MinecraftKey key;
    protected final String group;
    protected final RecipeItemStack ingredient;
    protected final ItemStack result;
    protected final float experience;
    protected final int cookingTime;

    public RecipeCooking(Recipes<?> recipes, MinecraftKey minecraftkey, String s, RecipeItemStack recipeitemstack, ItemStack itemstack, float f, int i) {
        this.a = recipes;
        this.key = minecraftkey;
        this.group = s;
        this.ingredient = recipeitemstack;
        this.result = itemstack;
        this.experience = f;
        this.cookingTime = i;
    }

    @Override
    public boolean a(IInventory iinventory, World world) {
        return this.ingredient.test(iinventory.getItem(0));
    }

    @Override
    public ItemStack a(IInventory iinventory) {
        return this.result.cloneItemStack();
    }

    @Override
    public NonNullList<RecipeItemStack> a() {
        NonNullList<RecipeItemStack> nonnulllist = NonNullList.a();

        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    public float b() {
        return this.experience;
    }

    @Override
    public ItemStack c() {
        return this.result;
    }

    public int e() {
        return this.cookingTime;
    }

    @Override
    public MinecraftKey getKey() {
        return this.key;
    }

    @Override
    public Recipes<?> g() {
        return this.a;
    }
}

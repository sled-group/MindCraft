package net.minecraft.server;

public interface RecipeCrafting extends IRecipe<InventoryCrafting> {

    @Override
    default Recipes<?> g() {
        return Recipes.CRAFTING;
    }
}

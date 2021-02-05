package net.minecraft.server;

import java.util.Optional;

public interface Recipes<T extends IRecipe<?>> {

    Recipes<RecipeCrafting> CRAFTING = a("crafting");
    Recipes<FurnaceRecipe> SMELTING = a("smelting");
    Recipes<RecipeBlasting> BLASTING = a("blasting");
    Recipes<RecipeSmoking> SMOKING = a("smoking");
    Recipes<RecipeCampfire> CAMPFIRE_COOKING = a("campfire_cooking");
    Recipes<RecipeStonecutting> STONECUTTING = a("stonecutting");

    static <T extends IRecipe<?>> Recipes<T> a(final String s) {
        return (Recipes) IRegistry.a(IRegistry.RECIPE_TYPE, new MinecraftKey(s), (Object) (new Recipes<T>() {
            public String toString() {
                return s;
            }
        }));
    }

    default <C extends IInventory> Optional<T> a(IRecipe<C> irecipe, World world, C c0) {
        return irecipe.a(c0, world) ? Optional.of(irecipe) : Optional.empty();
    }
}

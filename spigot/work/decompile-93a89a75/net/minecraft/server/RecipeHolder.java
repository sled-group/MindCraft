package net.minecraft.server;

import java.util.Collections;
import javax.annotation.Nullable;

public interface RecipeHolder {

    void a(@Nullable IRecipe<?> irecipe);

    @Nullable
    IRecipe<?> U_();

    default void b(EntityHuman entityhuman) {
        IRecipe<?> irecipe = this.U_();

        if (irecipe != null && !irecipe.isComplex()) {
            entityhuman.discoverRecipes(Collections.singleton(irecipe));
            this.a((IRecipe) null);
        }

    }

    default boolean a(World world, EntityPlayer entityplayer, IRecipe<?> irecipe) {
        if (!irecipe.isComplex() && world.getGameRules().getBoolean(GameRules.DO_LIMITED_CRAFTING) && !entityplayer.B().b(irecipe)) {
            return false;
        } else {
            this.a(irecipe);
            return true;
        }
    }
}

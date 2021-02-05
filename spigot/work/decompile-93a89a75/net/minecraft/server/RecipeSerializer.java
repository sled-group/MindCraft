package net.minecraft.server;

import com.google.gson.JsonObject;

public interface RecipeSerializer<T extends IRecipe<?>> {

    RecipeSerializer<ShapedRecipes> a = a("crafting_shaped", (RecipeSerializer) (new ShapedRecipes.a()));
    RecipeSerializer<ShapelessRecipes> b = a("crafting_shapeless", (RecipeSerializer) (new ShapelessRecipes.a()));
    RecipeSerializerComplex<RecipeArmorDye> c = (RecipeSerializerComplex) a("crafting_special_armordye", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeArmorDye::new)));
    RecipeSerializerComplex<RecipeBookClone> d = (RecipeSerializerComplex) a("crafting_special_bookcloning", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeBookClone::new)));
    RecipeSerializerComplex<RecipeMapClone> e = (RecipeSerializerComplex) a("crafting_special_mapcloning", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeMapClone::new)));
    RecipeSerializerComplex<RecipeMapExtend> f = (RecipeSerializerComplex) a("crafting_special_mapextending", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeMapExtend::new)));
    RecipeSerializerComplex<RecipeFireworks> g = (RecipeSerializerComplex) a("crafting_special_firework_rocket", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeFireworks::new)));
    RecipeSerializerComplex<RecipeFireworksStar> h = (RecipeSerializerComplex) a("crafting_special_firework_star", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeFireworksStar::new)));
    RecipeSerializerComplex<RecipeFireworksFade> i = (RecipeSerializerComplex) a("crafting_special_firework_star_fade", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeFireworksFade::new)));
    RecipeSerializerComplex<RecipeTippedArrow> j = (RecipeSerializerComplex) a("crafting_special_tippedarrow", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeTippedArrow::new)));
    RecipeSerializerComplex<RecipeBannerDuplicate> k = (RecipeSerializerComplex) a("crafting_special_bannerduplicate", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeBannerDuplicate::new)));
    RecipeSerializerComplex<RecipiesShield> l = (RecipeSerializerComplex) a("crafting_special_shielddecoration", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipiesShield::new)));
    RecipeSerializerComplex<RecipeShulkerBox> m = (RecipeSerializerComplex) a("crafting_special_shulkerboxcoloring", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeShulkerBox::new)));
    RecipeSerializerComplex<RecipeSuspiciousStew> n = (RecipeSerializerComplex) a("crafting_special_suspiciousstew", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeSuspiciousStew::new)));
    RecipeSerializerComplex<RecipeRepair> o = (RecipeSerializerComplex) a("crafting_special_repairitem", (RecipeSerializer) (new RecipeSerializerComplex<>(RecipeRepair::new)));
    RecipeSerializerCooking<FurnaceRecipe> p = (RecipeSerializerCooking) a("smelting", (RecipeSerializer) (new RecipeSerializerCooking<>(FurnaceRecipe::new, 200)));
    RecipeSerializerCooking<RecipeBlasting> q = (RecipeSerializerCooking) a("blasting", (RecipeSerializer) (new RecipeSerializerCooking<>(RecipeBlasting::new, 100)));
    RecipeSerializerCooking<RecipeSmoking> r = (RecipeSerializerCooking) a("smoking", (RecipeSerializer) (new RecipeSerializerCooking<>(RecipeSmoking::new, 100)));
    RecipeSerializerCooking<RecipeCampfire> s = (RecipeSerializerCooking) a("campfire_cooking", (RecipeSerializer) (new RecipeSerializerCooking<>(RecipeCampfire::new, 100)));
    RecipeSerializer<RecipeStonecutting> t = a("stonecutting", (RecipeSerializer) (new RecipeSingleItem.a<>(RecipeStonecutting::new)));

    T a(MinecraftKey minecraftkey, JsonObject jsonobject);

    T a(MinecraftKey minecraftkey, PacketDataSerializer packetdataserializer);

    void a(PacketDataSerializer packetdataserializer, T t0);

    static <S extends RecipeSerializer<T>, T extends IRecipe<?>> S a(String s, S s0) {
        return (RecipeSerializer) IRegistry.a(IRegistry.RECIPE_SERIALIZER, s, (Object) s0);
    }
}

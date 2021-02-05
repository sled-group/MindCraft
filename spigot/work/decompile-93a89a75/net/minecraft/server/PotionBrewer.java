package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class PotionBrewer {

    private static final List<PotionBrewer.PredicatedCombination<PotionRegistry>> a = Lists.newArrayList();
    private static final List<PotionBrewer.PredicatedCombination<Item>> b = Lists.newArrayList();
    private static final List<RecipeItemStack> c = Lists.newArrayList();
    private static final Predicate<ItemStack> d = (itemstack) -> {
        Iterator iterator = PotionBrewer.c.iterator();

        RecipeItemStack recipeitemstack;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            recipeitemstack = (RecipeItemStack) iterator.next();
        } while (!recipeitemstack.test(itemstack));

        return true;
    };

    public static boolean a(ItemStack itemstack) {
        return b(itemstack) || c(itemstack);
    }

    protected static boolean b(ItemStack itemstack) {
        int i = 0;

        for (int j = PotionBrewer.b.size(); i < j; ++i) {
            if (((PotionBrewer.PredicatedCombination) PotionBrewer.b.get(i)).b.test(itemstack)) {
                return true;
            }
        }

        return false;
    }

    protected static boolean c(ItemStack itemstack) {
        int i = 0;

        for (int j = PotionBrewer.a.size(); i < j; ++i) {
            if (((PotionBrewer.PredicatedCombination) PotionBrewer.a.get(i)).b.test(itemstack)) {
                return true;
            }
        }

        return false;
    }

    public static boolean a(PotionRegistry potionregistry) {
        int i = 0;

        for (int j = PotionBrewer.a.size(); i < j; ++i) {
            if (((PotionBrewer.PredicatedCombination) PotionBrewer.a.get(i)).c == potionregistry) {
                return true;
            }
        }

        return false;
    }

    public static boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return !PotionBrewer.d.test(itemstack) ? false : b(itemstack, itemstack1) || c(itemstack, itemstack1);
    }

    protected static boolean b(ItemStack itemstack, ItemStack itemstack1) {
        Item item = itemstack.getItem();
        int i = 0;

        for (int j = PotionBrewer.b.size(); i < j; ++i) {
            PotionBrewer.PredicatedCombination<Item> potionbrewer_predicatedcombination = (PotionBrewer.PredicatedCombination) PotionBrewer.b.get(i);

            if (potionbrewer_predicatedcombination.a == item && potionbrewer_predicatedcombination.b.test(itemstack1)) {
                return true;
            }
        }

        return false;
    }

    protected static boolean c(ItemStack itemstack, ItemStack itemstack1) {
        PotionRegistry potionregistry = PotionUtil.d(itemstack);
        int i = 0;

        for (int j = PotionBrewer.a.size(); i < j; ++i) {
            PotionBrewer.PredicatedCombination<PotionRegistry> potionbrewer_predicatedcombination = (PotionBrewer.PredicatedCombination) PotionBrewer.a.get(i);

            if (potionbrewer_predicatedcombination.a == potionregistry && potionbrewer_predicatedcombination.b.test(itemstack1)) {
                return true;
            }
        }

        return false;
    }

    public static ItemStack d(ItemStack itemstack, ItemStack itemstack1) {
        if (!itemstack1.isEmpty()) {
            PotionRegistry potionregistry = PotionUtil.d(itemstack1);
            Item item = itemstack1.getItem();
            int i = 0;

            PotionBrewer.PredicatedCombination potionbrewer_predicatedcombination;
            int j;

            for (j = PotionBrewer.b.size(); i < j; ++i) {
                potionbrewer_predicatedcombination = (PotionBrewer.PredicatedCombination) PotionBrewer.b.get(i);
                if (potionbrewer_predicatedcombination.a == item && potionbrewer_predicatedcombination.b.test(itemstack)) {
                    return PotionUtil.a(new ItemStack((IMaterial) potionbrewer_predicatedcombination.c), potionregistry);
                }
            }

            i = 0;

            for (j = PotionBrewer.a.size(); i < j; ++i) {
                potionbrewer_predicatedcombination = (PotionBrewer.PredicatedCombination) PotionBrewer.a.get(i);
                if (potionbrewer_predicatedcombination.a == potionregistry && potionbrewer_predicatedcombination.b.test(itemstack)) {
                    return PotionUtil.a(new ItemStack(item), (PotionRegistry) potionbrewer_predicatedcombination.c);
                }
            }
        }

        return itemstack1;
    }

    public static void a() {
        a(Items.POTION);
        a(Items.SPLASH_POTION);
        a(Items.LINGERING_POTION);
        a(Items.POTION, Items.GUNPOWDER, Items.SPLASH_POTION);
        a(Items.SPLASH_POTION, Items.DRAGON_BREATH, Items.LINGERING_POTION);
        a(Potions.WATER, Items.GLISTERING_MELON_SLICE, Potions.MUNDANE);
        a(Potions.WATER, Items.GHAST_TEAR, Potions.MUNDANE);
        a(Potions.WATER, Items.RABBIT_FOOT, Potions.MUNDANE);
        a(Potions.WATER, Items.BLAZE_POWDER, Potions.MUNDANE);
        a(Potions.WATER, Items.SPIDER_EYE, Potions.MUNDANE);
        a(Potions.WATER, Items.SUGAR, Potions.MUNDANE);
        a(Potions.WATER, Items.MAGMA_CREAM, Potions.MUNDANE);
        a(Potions.WATER, Items.GLOWSTONE_DUST, Potions.THICK);
        a(Potions.WATER, Items.REDSTONE, Potions.MUNDANE);
        a(Potions.WATER, Items.NETHER_WART, Potions.AWKWARD);
        a(Potions.AWKWARD, Items.GOLDEN_CARROT, Potions.NIGHT_VISION);
        a(Potions.NIGHT_VISION, Items.REDSTONE, Potions.LONG_NIGHT_VISION);
        a(Potions.NIGHT_VISION, Items.FERMENTED_SPIDER_EYE, Potions.INVISIBILITY);
        a(Potions.LONG_NIGHT_VISION, Items.FERMENTED_SPIDER_EYE, Potions.LONG_INVISIBILITY);
        a(Potions.INVISIBILITY, Items.REDSTONE, Potions.LONG_INVISIBILITY);
        a(Potions.AWKWARD, Items.MAGMA_CREAM, Potions.FIRE_RESISTANCE);
        a(Potions.FIRE_RESISTANCE, Items.REDSTONE, Potions.LONG_FIRE_RESISTANCE);
        a(Potions.AWKWARD, Items.RABBIT_FOOT, Potions.LEAPING);
        a(Potions.LEAPING, Items.REDSTONE, Potions.LONG_LEAPING);
        a(Potions.LEAPING, Items.GLOWSTONE_DUST, Potions.STRONG_LEAPING);
        a(Potions.LEAPING, Items.FERMENTED_SPIDER_EYE, Potions.SLOWNESS);
        a(Potions.LONG_LEAPING, Items.FERMENTED_SPIDER_EYE, Potions.LONG_SLOWNESS);
        a(Potions.SLOWNESS, Items.REDSTONE, Potions.LONG_SLOWNESS);
        a(Potions.SLOWNESS, Items.GLOWSTONE_DUST, Potions.STRONG_SLOWNESS);
        a(Potions.AWKWARD, Items.TURTLE_HELMET, Potions.TURTLE_MASTER);
        a(Potions.TURTLE_MASTER, Items.REDSTONE, Potions.LONG_TURTLE_MASTER);
        a(Potions.TURTLE_MASTER, Items.GLOWSTONE_DUST, Potions.STRONG_TURTLE_MASTER);
        a(Potions.SWIFTNESS, Items.FERMENTED_SPIDER_EYE, Potions.SLOWNESS);
        a(Potions.LONG_SWIFTNESS, Items.FERMENTED_SPIDER_EYE, Potions.LONG_SLOWNESS);
        a(Potions.AWKWARD, Items.SUGAR, Potions.SWIFTNESS);
        a(Potions.SWIFTNESS, Items.REDSTONE, Potions.LONG_SWIFTNESS);
        a(Potions.SWIFTNESS, Items.GLOWSTONE_DUST, Potions.STRONG_SWIFTNESS);
        a(Potions.AWKWARD, Items.PUFFERFISH, Potions.WATER_BREATHING);
        a(Potions.WATER_BREATHING, Items.REDSTONE, Potions.LONG_WATER_BREATHING);
        a(Potions.AWKWARD, Items.GLISTERING_MELON_SLICE, Potions.HEALING);
        a(Potions.HEALING, Items.GLOWSTONE_DUST, Potions.STRONG_HEALING);
        a(Potions.HEALING, Items.FERMENTED_SPIDER_EYE, Potions.HARMING);
        a(Potions.STRONG_HEALING, Items.FERMENTED_SPIDER_EYE, Potions.STRONG_HARMING);
        a(Potions.HARMING, Items.GLOWSTONE_DUST, Potions.STRONG_HARMING);
        a(Potions.POISON, Items.FERMENTED_SPIDER_EYE, Potions.HARMING);
        a(Potions.LONG_POISON, Items.FERMENTED_SPIDER_EYE, Potions.HARMING);
        a(Potions.STRONG_POISON, Items.FERMENTED_SPIDER_EYE, Potions.STRONG_HARMING);
        a(Potions.AWKWARD, Items.SPIDER_EYE, Potions.POISON);
        a(Potions.POISON, Items.REDSTONE, Potions.LONG_POISON);
        a(Potions.POISON, Items.GLOWSTONE_DUST, Potions.STRONG_POISON);
        a(Potions.AWKWARD, Items.GHAST_TEAR, Potions.REGENERATION);
        a(Potions.REGENERATION, Items.REDSTONE, Potions.LONG_REGENERATION);
        a(Potions.REGENERATION, Items.GLOWSTONE_DUST, Potions.STRONG_REGENERATION);
        a(Potions.AWKWARD, Items.BLAZE_POWDER, Potions.STRENGTH);
        a(Potions.STRENGTH, Items.REDSTONE, Potions.LONG_STRENGTH);
        a(Potions.STRENGTH, Items.GLOWSTONE_DUST, Potions.STRONG_STRENGTH);
        a(Potions.WATER, Items.FERMENTED_SPIDER_EYE, Potions.WEAKNESS);
        a(Potions.WEAKNESS, Items.REDSTONE, Potions.LONG_WEAKNESS);
        a(Potions.AWKWARD, Items.PHANTOM_MEMBRANE, Potions.SLOW_FALLING);
        a(Potions.SLOW_FALLING, Items.REDSTONE, Potions.LONG_SLOW_FALLING);
    }

    private static void a(Item item, Item item1, Item item2) {
        if (!(item instanceof ItemPotion)) {
            throw new IllegalArgumentException("Expected a potion, got: " + IRegistry.ITEM.getKey(item));
        } else if (!(item2 instanceof ItemPotion)) {
            throw new IllegalArgumentException("Expected a potion, got: " + IRegistry.ITEM.getKey(item2));
        } else {
            PotionBrewer.b.add(new PotionBrewer.PredicatedCombination<>(item, RecipeItemStack.a(item1), item2));
        }
    }

    private static void a(Item item) {
        if (!(item instanceof ItemPotion)) {
            throw new IllegalArgumentException("Expected a potion, got: " + IRegistry.ITEM.getKey(item));
        } else {
            PotionBrewer.c.add(RecipeItemStack.a(item));
        }
    }

    private static void a(PotionRegistry potionregistry, Item item, PotionRegistry potionregistry1) {
        PotionBrewer.a.add(new PotionBrewer.PredicatedCombination<>(potionregistry, RecipeItemStack.a(item), potionregistry1));
    }

    static class PredicatedCombination<T> {

        private final T a;
        private final RecipeItemStack b;
        private final T c;

        public PredicatedCombination(T t0, RecipeItemStack recipeitemstack, T t1) {
            this.a = t0;
            this.b = recipeitemstack;
            this.c = t1;
        }
    }
}

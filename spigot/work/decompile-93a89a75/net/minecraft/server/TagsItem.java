package net.minecraft.server;

import java.util.Collection;
import java.util.Optional;

public class TagsItem {

    private static Tags<Item> N = new Tags<>((minecraftkey) -> {
        return Optional.empty();
    }, "", false, "");
    private static int O;
    public static final Tag<Item> WOOL = a("wool");
    public static final Tag<Item> PLANKS = a("planks");
    public static final Tag<Item> STONE_BRICKS = a("stone_bricks");
    public static final Tag<Item> WOODEN_BUTTONS = a("wooden_buttons");
    public static final Tag<Item> BUTTONS = a("buttons");
    public static final Tag<Item> CARPETS = a("carpets");
    public static final Tag<Item> WOODEN_DOORS = a("wooden_doors");
    public static final Tag<Item> WOODEN_STAIRS = a("wooden_stairs");
    public static final Tag<Item> WOODEN_SLABS = a("wooden_slabs");
    public static final Tag<Item> WOODEN_FENCES = a("wooden_fences");
    public static final Tag<Item> WOODEN_PRESSURE_PLATES = a("wooden_pressure_plates");
    public static final Tag<Item> WOODEN_TRAPDOORS = a("wooden_trapdoors");
    public static final Tag<Item> DOORS = a("doors");
    public static final Tag<Item> SAPLINGS = a("saplings");
    public static final Tag<Item> LOGS = a("logs");
    public static final Tag<Item> DARK_OAK_LOGS = a("dark_oak_logs");
    public static final Tag<Item> OAK_LOGS = a("oak_logs");
    public static final Tag<Item> BIRCH_LOGS = a("birch_logs");
    public static final Tag<Item> ACACIA_LOGS = a("acacia_logs");
    public static final Tag<Item> JUNGLE_LOGS = a("jungle_logs");
    public static final Tag<Item> SPRUCE_LOGS = a("spruce_logs");
    public static final Tag<Item> BANNERS = a("banners");
    public static final Tag<Item> SAND = a("sand");
    public static final Tag<Item> STAIRS = a("stairs");
    public static final Tag<Item> SLABS = a("slabs");
    public static final Tag<Item> WALLS = a("walls");
    public static final Tag<Item> ANVIL = a("anvil");
    public static final Tag<Item> RAILS = a("rails");
    public static final Tag<Item> LEAVES = a("leaves");
    public static final Tag<Item> TRAPDOORS = a("trapdoors");
    public static final Tag<Item> SMALL_FLOWERS = a("small_flowers");
    public static final Tag<Item> BEDS = a("beds");
    public static final Tag<Item> FENCES = a("fences");
    public static final Tag<Item> BOATS = a("boats");
    public static final Tag<Item> FISHES = a("fishes");
    public static final Tag<Item> SIGNS = a("signs");
    public static final Tag<Item> MUSIC_DISCS = a("music_discs");
    public static final Tag<Item> COALS = a("coals");
    public static final Tag<Item> ARROWS = a("arrows");

    public static void a(Tags<Item> tags) {
        TagsItem.N = tags;
        ++TagsItem.O;
    }

    public static Tags<Item> a() {
        return TagsItem.N;
    }

    private static Tag<Item> a(String s) {
        return new TagsItem.a(new MinecraftKey(s));
    }

    public static class a extends Tag<Item> {

        private int a = -1;
        private Tag<Item> b;

        public a(MinecraftKey minecraftkey) {
            super(minecraftkey);
        }

        public boolean a(Item item) {
            if (this.a != TagsItem.O) {
                this.b = TagsItem.N.b(this.c());
                this.a = TagsItem.O;
            }

            return this.b.isTagged(item);
        }

        @Override
        public Collection<Item> a() {
            if (this.a != TagsItem.O) {
                this.b = TagsItem.N.b(this.c());
                this.a = TagsItem.O;
            }

            return this.b.a();
        }

        @Override
        public Collection<Tag.b<Item>> b() {
            if (this.a != TagsItem.O) {
                this.b = TagsItem.N.b(this.c());
                this.a = TagsItem.O;
            }

            return this.b.b();
        }
    }
}

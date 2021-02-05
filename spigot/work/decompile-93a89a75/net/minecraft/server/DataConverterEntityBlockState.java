package net.minecraft.server;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class DataConverterEntityBlockState extends DataFix {

    private static final Map<String, Integer> a = (Map) DataFixUtils.make(Maps.newHashMap(), (hashmap) -> {
        hashmap.put("minecraft:air", 0);
        hashmap.put("minecraft:stone", 1);
        hashmap.put("minecraft:grass", 2);
        hashmap.put("minecraft:dirt", 3);
        hashmap.put("minecraft:cobblestone", 4);
        hashmap.put("minecraft:planks", 5);
        hashmap.put("minecraft:sapling", 6);
        hashmap.put("minecraft:bedrock", 7);
        hashmap.put("minecraft:flowing_water", 8);
        hashmap.put("minecraft:water", 9);
        hashmap.put("minecraft:flowing_lava", 10);
        hashmap.put("minecraft:lava", 11);
        hashmap.put("minecraft:sand", 12);
        hashmap.put("minecraft:gravel", 13);
        hashmap.put("minecraft:gold_ore", 14);
        hashmap.put("minecraft:iron_ore", 15);
        hashmap.put("minecraft:coal_ore", 16);
        hashmap.put("minecraft:log", 17);
        hashmap.put("minecraft:leaves", 18);
        hashmap.put("minecraft:sponge", 19);
        hashmap.put("minecraft:glass", 20);
        hashmap.put("minecraft:lapis_ore", 21);
        hashmap.put("minecraft:lapis_block", 22);
        hashmap.put("minecraft:dispenser", 23);
        hashmap.put("minecraft:sandstone", 24);
        hashmap.put("minecraft:noteblock", 25);
        hashmap.put("minecraft:bed", 26);
        hashmap.put("minecraft:golden_rail", 27);
        hashmap.put("minecraft:detector_rail", 28);
        hashmap.put("minecraft:sticky_piston", 29);
        hashmap.put("minecraft:web", 30);
        hashmap.put("minecraft:tallgrass", 31);
        hashmap.put("minecraft:deadbush", 32);
        hashmap.put("minecraft:piston", 33);
        hashmap.put("minecraft:piston_head", 34);
        hashmap.put("minecraft:wool", 35);
        hashmap.put("minecraft:piston_extension", 36);
        hashmap.put("minecraft:yellow_flower", 37);
        hashmap.put("minecraft:red_flower", 38);
        hashmap.put("minecraft:brown_mushroom", 39);
        hashmap.put("minecraft:red_mushroom", 40);
        hashmap.put("minecraft:gold_block", 41);
        hashmap.put("minecraft:iron_block", 42);
        hashmap.put("minecraft:double_stone_slab", 43);
        hashmap.put("minecraft:stone_slab", 44);
        hashmap.put("minecraft:brick_block", 45);
        hashmap.put("minecraft:tnt", 46);
        hashmap.put("minecraft:bookshelf", 47);
        hashmap.put("minecraft:mossy_cobblestone", 48);
        hashmap.put("minecraft:obsidian", 49);
        hashmap.put("minecraft:torch", 50);
        hashmap.put("minecraft:fire", 51);
        hashmap.put("minecraft:mob_spawner", 52);
        hashmap.put("minecraft:oak_stairs", 53);
        hashmap.put("minecraft:chest", 54);
        hashmap.put("minecraft:redstone_wire", 55);
        hashmap.put("minecraft:diamond_ore", 56);
        hashmap.put("minecraft:diamond_block", 57);
        hashmap.put("minecraft:crafting_table", 58);
        hashmap.put("minecraft:wheat", 59);
        hashmap.put("minecraft:farmland", 60);
        hashmap.put("minecraft:furnace", 61);
        hashmap.put("minecraft:lit_furnace", 62);
        hashmap.put("minecraft:standing_sign", 63);
        hashmap.put("minecraft:wooden_door", 64);
        hashmap.put("minecraft:ladder", 65);
        hashmap.put("minecraft:rail", 66);
        hashmap.put("minecraft:stone_stairs", 67);
        hashmap.put("minecraft:wall_sign", 68);
        hashmap.put("minecraft:lever", 69);
        hashmap.put("minecraft:stone_pressure_plate", 70);
        hashmap.put("minecraft:iron_door", 71);
        hashmap.put("minecraft:wooden_pressure_plate", 72);
        hashmap.put("minecraft:redstone_ore", 73);
        hashmap.put("minecraft:lit_redstone_ore", 74);
        hashmap.put("minecraft:unlit_redstone_torch", 75);
        hashmap.put("minecraft:redstone_torch", 76);
        hashmap.put("minecraft:stone_button", 77);
        hashmap.put("minecraft:snow_layer", 78);
        hashmap.put("minecraft:ice", 79);
        hashmap.put("minecraft:snow", 80);
        hashmap.put("minecraft:cactus", 81);
        hashmap.put("minecraft:clay", 82);
        hashmap.put("minecraft:reeds", 83);
        hashmap.put("minecraft:jukebox", 84);
        hashmap.put("minecraft:fence", 85);
        hashmap.put("minecraft:pumpkin", 86);
        hashmap.put("minecraft:netherrack", 87);
        hashmap.put("minecraft:soul_sand", 88);
        hashmap.put("minecraft:glowstone", 89);
        hashmap.put("minecraft:portal", 90);
        hashmap.put("minecraft:lit_pumpkin", 91);
        hashmap.put("minecraft:cake", 92);
        hashmap.put("minecraft:unpowered_repeater", 93);
        hashmap.put("minecraft:powered_repeater", 94);
        hashmap.put("minecraft:stained_glass", 95);
        hashmap.put("minecraft:trapdoor", 96);
        hashmap.put("minecraft:monster_egg", 97);
        hashmap.put("minecraft:stonebrick", 98);
        hashmap.put("minecraft:brown_mushroom_block", 99);
        hashmap.put("minecraft:red_mushroom_block", 100);
        hashmap.put("minecraft:iron_bars", 101);
        hashmap.put("minecraft:glass_pane", 102);
        hashmap.put("minecraft:melon_block", 103);
        hashmap.put("minecraft:pumpkin_stem", 104);
        hashmap.put("minecraft:melon_stem", 105);
        hashmap.put("minecraft:vine", 106);
        hashmap.put("minecraft:fence_gate", 107);
        hashmap.put("minecraft:brick_stairs", 108);
        hashmap.put("minecraft:stone_brick_stairs", 109);
        hashmap.put("minecraft:mycelium", 110);
        hashmap.put("minecraft:waterlily", 111);
        hashmap.put("minecraft:nether_brick", 112);
        hashmap.put("minecraft:nether_brick_fence", 113);
        hashmap.put("minecraft:nether_brick_stairs", 114);
        hashmap.put("minecraft:nether_wart", 115);
        hashmap.put("minecraft:enchanting_table", 116);
        hashmap.put("minecraft:brewing_stand", 117);
        hashmap.put("minecraft:cauldron", 118);
        hashmap.put("minecraft:end_portal", 119);
        hashmap.put("minecraft:end_portal_frame", 120);
        hashmap.put("minecraft:end_stone", 121);
        hashmap.put("minecraft:dragon_egg", 122);
        hashmap.put("minecraft:redstone_lamp", 123);
        hashmap.put("minecraft:lit_redstone_lamp", 124);
        hashmap.put("minecraft:double_wooden_slab", 125);
        hashmap.put("minecraft:wooden_slab", 126);
        hashmap.put("minecraft:cocoa", 127);
        hashmap.put("minecraft:sandstone_stairs", 128);
        hashmap.put("minecraft:emerald_ore", 129);
        hashmap.put("minecraft:ender_chest", 130);
        hashmap.put("minecraft:tripwire_hook", 131);
        hashmap.put("minecraft:tripwire", 132);
        hashmap.put("minecraft:emerald_block", 133);
        hashmap.put("minecraft:spruce_stairs", 134);
        hashmap.put("minecraft:birch_stairs", 135);
        hashmap.put("minecraft:jungle_stairs", 136);
        hashmap.put("minecraft:command_block", 137);
        hashmap.put("minecraft:beacon", 138);
        hashmap.put("minecraft:cobblestone_wall", 139);
        hashmap.put("minecraft:flower_pot", 140);
        hashmap.put("minecraft:carrots", 141);
        hashmap.put("minecraft:potatoes", 142);
        hashmap.put("minecraft:wooden_button", 143);
        hashmap.put("minecraft:skull", 144);
        hashmap.put("minecraft:anvil", 145);
        hashmap.put("minecraft:trapped_chest", 146);
        hashmap.put("minecraft:light_weighted_pressure_plate", 147);
        hashmap.put("minecraft:heavy_weighted_pressure_plate", 148);
        hashmap.put("minecraft:unpowered_comparator", 149);
        hashmap.put("minecraft:powered_comparator", 150);
        hashmap.put("minecraft:daylight_detector", 151);
        hashmap.put("minecraft:redstone_block", 152);
        hashmap.put("minecraft:quartz_ore", 153);
        hashmap.put("minecraft:hopper", 154);
        hashmap.put("minecraft:quartz_block", 155);
        hashmap.put("minecraft:quartz_stairs", 156);
        hashmap.put("minecraft:activator_rail", 157);
        hashmap.put("minecraft:dropper", 158);
        hashmap.put("minecraft:stained_hardened_clay", 159);
        hashmap.put("minecraft:stained_glass_pane", 160);
        hashmap.put("minecraft:leaves2", 161);
        hashmap.put("minecraft:log2", 162);
        hashmap.put("minecraft:acacia_stairs", 163);
        hashmap.put("minecraft:dark_oak_stairs", 164);
        hashmap.put("minecraft:slime", 165);
        hashmap.put("minecraft:barrier", 166);
        hashmap.put("minecraft:iron_trapdoor", 167);
        hashmap.put("minecraft:prismarine", 168);
        hashmap.put("minecraft:sea_lantern", 169);
        hashmap.put("minecraft:hay_block", 170);
        hashmap.put("minecraft:carpet", 171);
        hashmap.put("minecraft:hardened_clay", 172);
        hashmap.put("minecraft:coal_block", 173);
        hashmap.put("minecraft:packed_ice", 174);
        hashmap.put("minecraft:double_plant", 175);
        hashmap.put("minecraft:standing_banner", 176);
        hashmap.put("minecraft:wall_banner", 177);
        hashmap.put("minecraft:daylight_detector_inverted", 178);
        hashmap.put("minecraft:red_sandstone", 179);
        hashmap.put("minecraft:red_sandstone_stairs", 180);
        hashmap.put("minecraft:double_stone_slab2", 181);
        hashmap.put("minecraft:stone_slab2", 182);
        hashmap.put("minecraft:spruce_fence_gate", 183);
        hashmap.put("minecraft:birch_fence_gate", 184);
        hashmap.put("minecraft:jungle_fence_gate", 185);
        hashmap.put("minecraft:dark_oak_fence_gate", 186);
        hashmap.put("minecraft:acacia_fence_gate", 187);
        hashmap.put("minecraft:spruce_fence", 188);
        hashmap.put("minecraft:birch_fence", 189);
        hashmap.put("minecraft:jungle_fence", 190);
        hashmap.put("minecraft:dark_oak_fence", 191);
        hashmap.put("minecraft:acacia_fence", 192);
        hashmap.put("minecraft:spruce_door", 193);
        hashmap.put("minecraft:birch_door", 194);
        hashmap.put("minecraft:jungle_door", 195);
        hashmap.put("minecraft:acacia_door", 196);
        hashmap.put("minecraft:dark_oak_door", 197);
        hashmap.put("minecraft:end_rod", 198);
        hashmap.put("minecraft:chorus_plant", 199);
        hashmap.put("minecraft:chorus_flower", 200);
        hashmap.put("minecraft:purpur_block", 201);
        hashmap.put("minecraft:purpur_pillar", 202);
        hashmap.put("minecraft:purpur_stairs", 203);
        hashmap.put("minecraft:purpur_double_slab", 204);
        hashmap.put("minecraft:purpur_slab", 205);
        hashmap.put("minecraft:end_bricks", 206);
        hashmap.put("minecraft:beetroots", 207);
        hashmap.put("minecraft:grass_path", 208);
        hashmap.put("minecraft:end_gateway", 209);
        hashmap.put("minecraft:repeating_command_block", 210);
        hashmap.put("minecraft:chain_command_block", 211);
        hashmap.put("minecraft:frosted_ice", 212);
        hashmap.put("minecraft:magma", 213);
        hashmap.put("minecraft:nether_wart_block", 214);
        hashmap.put("minecraft:red_nether_brick", 215);
        hashmap.put("minecraft:bone_block", 216);
        hashmap.put("minecraft:structure_void", 217);
        hashmap.put("minecraft:observer", 218);
        hashmap.put("minecraft:white_shulker_box", 219);
        hashmap.put("minecraft:orange_shulker_box", 220);
        hashmap.put("minecraft:magenta_shulker_box", 221);
        hashmap.put("minecraft:light_blue_shulker_box", 222);
        hashmap.put("minecraft:yellow_shulker_box", 223);
        hashmap.put("minecraft:lime_shulker_box", 224);
        hashmap.put("minecraft:pink_shulker_box", 225);
        hashmap.put("minecraft:gray_shulker_box", 226);
        hashmap.put("minecraft:silver_shulker_box", 227);
        hashmap.put("minecraft:cyan_shulker_box", 228);
        hashmap.put("minecraft:purple_shulker_box", 229);
        hashmap.put("minecraft:blue_shulker_box", 230);
        hashmap.put("minecraft:brown_shulker_box", 231);
        hashmap.put("minecraft:green_shulker_box", 232);
        hashmap.put("minecraft:red_shulker_box", 233);
        hashmap.put("minecraft:black_shulker_box", 234);
        hashmap.put("minecraft:white_glazed_terracotta", 235);
        hashmap.put("minecraft:orange_glazed_terracotta", 236);
        hashmap.put("minecraft:magenta_glazed_terracotta", 237);
        hashmap.put("minecraft:light_blue_glazed_terracotta", 238);
        hashmap.put("minecraft:yellow_glazed_terracotta", 239);
        hashmap.put("minecraft:lime_glazed_terracotta", 240);
        hashmap.put("minecraft:pink_glazed_terracotta", 241);
        hashmap.put("minecraft:gray_glazed_terracotta", 242);
        hashmap.put("minecraft:silver_glazed_terracotta", 243);
        hashmap.put("minecraft:cyan_glazed_terracotta", 244);
        hashmap.put("minecraft:purple_glazed_terracotta", 245);
        hashmap.put("minecraft:blue_glazed_terracotta", 246);
        hashmap.put("minecraft:brown_glazed_terracotta", 247);
        hashmap.put("minecraft:green_glazed_terracotta", 248);
        hashmap.put("minecraft:red_glazed_terracotta", 249);
        hashmap.put("minecraft:black_glazed_terracotta", 250);
        hashmap.put("minecraft:concrete", 251);
        hashmap.put("minecraft:concrete_powder", 252);
        hashmap.put("minecraft:structure_block", 255);
    });

    public DataConverterEntityBlockState(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public static int a(String s) {
        Integer integer = (Integer) DataConverterEntityBlockState.a.get(s);

        return integer == null ? 0 : integer;
    }

    public TypeRewriteRule makeRule() {
        Schema schema = this.getInputSchema();
        Schema schema1 = this.getOutputSchema();
        Function<Typed<?>, Typed<?>> function = (typed) -> {
            return this.a(typed, "DisplayTile", "DisplayData", "DisplayState");
        };
        Function<Typed<?>, Typed<?>> function1 = (typed) -> {
            return this.a(typed, "inTile", "inData", "inBlockState");
        };
        Type<Pair<Either<Pair<String, Either<Integer, String>>, com.mojang.datafixers.util.Unit>, Dynamic<?>>> type = DSL.and(DSL.optional(DSL.field("inTile", DSL.named(DataConverterTypes.q.typeName(), DSL.or(DSL.intType(), DSL.namespacedString())))), DSL.remainderType());
        Function<Typed<?>, Typed<?>> function2 = (typed) -> {
            return typed.update(type.finder(), DSL.remainderType(), Pair::getSecond);
        };

        return this.fixTypeEverywhereTyped("EntityBlockStateFix", schema.getType(DataConverterTypes.ENTITY), schema1.getType(DataConverterTypes.ENTITY), (typed) -> {
            typed = this.a(typed, "minecraft:falling_block", this::a);
            typed = this.a(typed, "minecraft:enderman", (typed1) -> {
                return this.a(typed1, "carried", "carriedData", "carriedBlockState");
            });
            typed = this.a(typed, "minecraft:arrow", function1);
            typed = this.a(typed, "minecraft:spectral_arrow", function1);
            typed = this.a(typed, "minecraft:egg", function2);
            typed = this.a(typed, "minecraft:ender_pearl", function2);
            typed = this.a(typed, "minecraft:fireball", function2);
            typed = this.a(typed, "minecraft:potion", function2);
            typed = this.a(typed, "minecraft:small_fireball", function2);
            typed = this.a(typed, "minecraft:snowball", function2);
            typed = this.a(typed, "minecraft:wither_skull", function2);
            typed = this.a(typed, "minecraft:xp_bottle", function2);
            typed = this.a(typed, "minecraft:commandblock_minecart", function);
            typed = this.a(typed, "minecraft:minecart", function);
            typed = this.a(typed, "minecraft:chest_minecart", function);
            typed = this.a(typed, "minecraft:furnace_minecart", function);
            typed = this.a(typed, "minecraft:tnt_minecart", function);
            typed = this.a(typed, "minecraft:hopper_minecart", function);
            typed = this.a(typed, "minecraft:spawner_minecart", function);
            return typed;
        });
    }

    private Typed<?> a(Typed<?> typed) {
        Type<Either<Pair<String, Either<Integer, String>>, com.mojang.datafixers.util.Unit>> type = DSL.optional(DSL.field("Block", DSL.named(DataConverterTypes.q.typeName(), DSL.or(DSL.intType(), DSL.namespacedString()))));
        Type<Either<Pair<String, Dynamic<?>>, com.mojang.datafixers.util.Unit>> type1 = DSL.optional(DSL.field("BlockState", DSL.named(DataConverterTypes.m.typeName(), DSL.remainderType())));
        Dynamic<?> dynamic = (Dynamic) typed.get(DSL.remainderFinder());

        return typed.update(type.finder(), type1, (either) -> {
            int i = (Integer) either.map((pair) -> {
                return (Integer) ((Either) pair.getSecond()).map((integer) -> {
                    return integer;
                }, DataConverterEntityBlockState::a);
            }, (com_mojang_datafixers_util_unit) -> {
                Optional<Number> optional = dynamic.get("TileID").asNumber();

                return (Integer) optional.map(Number::intValue).orElseGet(() -> {
                    return dynamic.get("Tile").asByte((byte) 0) & 255;
                });
            });
            int j = dynamic.get("Data").asInt(0) & 15;

            return Either.left(Pair.of(DataConverterTypes.m.typeName(), DataConverterFlattenData.b(i << 4 | j)));
        }).set(DSL.remainderFinder(), dynamic.remove("Data").remove("TileID").remove("Tile"));
    }

    private Typed<?> a(Typed<?> typed, String s, String s1, String s2) {
        Type<Pair<String, Either<Integer, String>>> type = DSL.field(s, DSL.named(DataConverterTypes.q.typeName(), DSL.or(DSL.intType(), DSL.namespacedString())));
        Type<Pair<String, Dynamic<?>>> type1 = DSL.field(s2, DSL.named(DataConverterTypes.m.typeName(), DSL.remainderType()));
        Dynamic<?> dynamic = (Dynamic) typed.getOrCreate(DSL.remainderFinder());

        return typed.update(type.finder(), type1, (pair) -> {
            int i = (Integer) ((Either) pair.getSecond()).map((integer) -> {
                return integer;
            }, DataConverterEntityBlockState::a);
            int j = dynamic.get(s1).asInt(0) & 15;

            return Pair.of(DataConverterTypes.m.typeName(), DataConverterFlattenData.b(i << 4 | j));
        }).set(DSL.remainderFinder(), dynamic.remove(s1));
    }

    private Typed<?> a(Typed<?> typed, String s, Function<Typed<?>, Typed<?>> function) {
        Type<?> type = this.getInputSchema().getChoiceType(DataConverterTypes.ENTITY, s);
        Type<?> type1 = this.getOutputSchema().getChoiceType(DataConverterTypes.ENTITY, s);

        return typed.updateTyped(DSL.namedChoice(s, type), type1, function);
    }
}

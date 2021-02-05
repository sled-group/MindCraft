package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Set;

public class LootTables {

    private static final Set<MinecraftKey> at = Sets.newHashSet();
    private static final Set<MinecraftKey> au = Collections.unmodifiableSet(LootTables.at);
    public static final MinecraftKey a = new MinecraftKey("empty");
    public static final MinecraftKey b = a("chests/spawn_bonus_chest");
    public static final MinecraftKey c = a("chests/end_city_treasure");
    public static final MinecraftKey d = a("chests/simple_dungeon");
    public static final MinecraftKey e = a("chests/village/village_weaponsmith");
    public static final MinecraftKey f = a("chests/village/village_toolsmith");
    public static final MinecraftKey g = a("chests/village/village_armorer");
    public static final MinecraftKey h = a("chests/village/village_cartographer");
    public static final MinecraftKey i = a("chests/village/village_mason");
    public static final MinecraftKey j = a("chests/village/village_shepherd");
    public static final MinecraftKey k = a("chests/village/village_butcher");
    public static final MinecraftKey l = a("chests/village/village_fletcher");
    public static final MinecraftKey m = a("chests/village/village_fisher");
    public static final MinecraftKey n = a("chests/village/village_tannery");
    public static final MinecraftKey o = a("chests/village/village_temple");
    public static final MinecraftKey p = a("chests/village/village_desert_house");
    public static final MinecraftKey q = a("chests/village/village_plains_house");
    public static final MinecraftKey r = a("chests/village/village_taiga_house");
    public static final MinecraftKey s = a("chests/village/village_snowy_house");
    public static final MinecraftKey t = a("chests/village/village_savanna_house");
    public static final MinecraftKey u = a("chests/abandoned_mineshaft");
    public static final MinecraftKey v = a("chests/nether_bridge");
    public static final MinecraftKey w = a("chests/stronghold_library");
    public static final MinecraftKey x = a("chests/stronghold_crossing");
    public static final MinecraftKey y = a("chests/stronghold_corridor");
    public static final MinecraftKey z = a("chests/desert_pyramid");
    public static final MinecraftKey A = a("chests/jungle_temple");
    public static final MinecraftKey B = a("chests/jungle_temple_dispenser");
    public static final MinecraftKey C = a("chests/igloo_chest");
    public static final MinecraftKey D = a("chests/woodland_mansion");
    public static final MinecraftKey E = a("chests/underwater_ruin_small");
    public static final MinecraftKey F = a("chests/underwater_ruin_big");
    public static final MinecraftKey G = a("chests/buried_treasure");
    public static final MinecraftKey H = a("chests/shipwreck_map");
    public static final MinecraftKey I = a("chests/shipwreck_supply");
    public static final MinecraftKey J = a("chests/shipwreck_treasure");
    public static final MinecraftKey K = a("chests/pillager_outpost");
    public static final MinecraftKey L = a("entities/sheep/white");
    public static final MinecraftKey M = a("entities/sheep/orange");
    public static final MinecraftKey N = a("entities/sheep/magenta");
    public static final MinecraftKey O = a("entities/sheep/light_blue");
    public static final MinecraftKey P = a("entities/sheep/yellow");
    public static final MinecraftKey Q = a("entities/sheep/lime");
    public static final MinecraftKey R = a("entities/sheep/pink");
    public static final MinecraftKey S = a("entities/sheep/gray");
    public static final MinecraftKey T = a("entities/sheep/light_gray");
    public static final MinecraftKey U = a("entities/sheep/cyan");
    public static final MinecraftKey V = a("entities/sheep/purple");
    public static final MinecraftKey W = a("entities/sheep/blue");
    public static final MinecraftKey X = a("entities/sheep/brown");
    public static final MinecraftKey Y = a("entities/sheep/green");
    public static final MinecraftKey Z = a("entities/sheep/red");
    public static final MinecraftKey aa = a("entities/sheep/black");
    public static final MinecraftKey ab = a("gameplay/fishing");
    public static final MinecraftKey ac = a("gameplay/fishing/junk");
    public static final MinecraftKey ad = a("gameplay/fishing/treasure");
    public static final MinecraftKey ae = a("gameplay/fishing/fish");
    public static final MinecraftKey af = a("gameplay/cat_morning_gift");
    public static final MinecraftKey ag = a("gameplay/hero_of_the_village/armorer_gift");
    public static final MinecraftKey ah = a("gameplay/hero_of_the_village/butcher_gift");
    public static final MinecraftKey ai = a("gameplay/hero_of_the_village/cartographer_gift");
    public static final MinecraftKey aj = a("gameplay/hero_of_the_village/cleric_gift");
    public static final MinecraftKey ak = a("gameplay/hero_of_the_village/farmer_gift");
    public static final MinecraftKey al = a("gameplay/hero_of_the_village/fisherman_gift");
    public static final MinecraftKey am = a("gameplay/hero_of_the_village/fletcher_gift");
    public static final MinecraftKey an = a("gameplay/hero_of_the_village/leatherworker_gift");
    public static final MinecraftKey ao = a("gameplay/hero_of_the_village/librarian_gift");
    public static final MinecraftKey ap = a("gameplay/hero_of_the_village/mason_gift");
    public static final MinecraftKey aq = a("gameplay/hero_of_the_village/shepherd_gift");
    public static final MinecraftKey ar = a("gameplay/hero_of_the_village/toolsmith_gift");
    public static final MinecraftKey as = a("gameplay/hero_of_the_village/weaponsmith_gift");

    private static MinecraftKey a(String s) {
        return a(new MinecraftKey(s));
    }

    private static MinecraftKey a(MinecraftKey minecraftkey) {
        if (LootTables.at.add(minecraftkey)) {
            return minecraftkey;
        } else {
            throw new IllegalArgumentException(minecraftkey + " is already a registered built-in loot table");
        }
    }

    public static Set<MinecraftKey> a() {
        return LootTables.au;
    }
}

package net.minecraft.server;

public class StatisticList {

    public static final StatisticWrapper<Block> BLOCK_MINED = a("mined", (IRegistry) IRegistry.BLOCK);
    public static final StatisticWrapper<Item> ITEM_CRAFTED = a("crafted", (IRegistry) IRegistry.ITEM);
    public static final StatisticWrapper<Item> ITEM_USED = a("used", (IRegistry) IRegistry.ITEM);
    public static final StatisticWrapper<Item> ITEM_BROKEN = a("broken", (IRegistry) IRegistry.ITEM);
    public static final StatisticWrapper<Item> ITEM_PICKED_UP = a("picked_up", (IRegistry) IRegistry.ITEM);
    public static final StatisticWrapper<Item> ITEM_DROPPED = a("dropped", (IRegistry) IRegistry.ITEM);
    public static final StatisticWrapper<EntityTypes<?>> ENTITY_KILLED = a("killed", (IRegistry) IRegistry.ENTITY_TYPE);
    public static final StatisticWrapper<EntityTypes<?>> ENTITY_KILLED_BY = a("killed_by", (IRegistry) IRegistry.ENTITY_TYPE);
    public static final StatisticWrapper<MinecraftKey> CUSTOM = a("custom", IRegistry.CUSTOM_STAT);
    public static final MinecraftKey LEAVE_GAME = a("leave_game", Counter.DEFAULT);
    public static final MinecraftKey PLAY_ONE_MINUTE = a("play_one_minute", Counter.TIME);
    public static final MinecraftKey TIME_SINCE_DEATH = a("time_since_death", Counter.TIME);
    public static final MinecraftKey TIME_SINCE_REST = a("time_since_rest", Counter.TIME);
    public static final MinecraftKey SNEAK_TIME = a("sneak_time", Counter.TIME);
    public static final MinecraftKey WALK_ONE_CM = a("walk_one_cm", Counter.DISTANCE);
    public static final MinecraftKey CROUCH_ONE_CM = a("crouch_one_cm", Counter.DISTANCE);
    public static final MinecraftKey SPRINT_ONE_CM = a("sprint_one_cm", Counter.DISTANCE);
    public static final MinecraftKey WALK_ON_WATER_ONE_CM = a("walk_on_water_one_cm", Counter.DISTANCE);
    public static final MinecraftKey FALL_ONE_CM = a("fall_one_cm", Counter.DISTANCE);
    public static final MinecraftKey CLIMB_ONE_CM = a("climb_one_cm", Counter.DISTANCE);
    public static final MinecraftKey FLY_ONE_CM = a("fly_one_cm", Counter.DISTANCE);
    public static final MinecraftKey WALK_UNDER_WATER_ONE_CM = a("walk_under_water_one_cm", Counter.DISTANCE);
    public static final MinecraftKey MINECART_ONE_CM = a("minecart_one_cm", Counter.DISTANCE);
    public static final MinecraftKey BOAT_ONE_CM = a("boat_one_cm", Counter.DISTANCE);
    public static final MinecraftKey PIG_ONE_CM = a("pig_one_cm", Counter.DISTANCE);
    public static final MinecraftKey HORSE_ONE_CM = a("horse_one_cm", Counter.DISTANCE);
    public static final MinecraftKey AVIATE_ONE_CM = a("aviate_one_cm", Counter.DISTANCE);
    public static final MinecraftKey SWIM_ONE_CM = a("swim_one_cm", Counter.DISTANCE);
    public static final MinecraftKey JUMP = a("jump", Counter.DEFAULT);
    public static final MinecraftKey DROP = a("drop", Counter.DEFAULT);
    public static final MinecraftKey DAMAGE_DEALT = a("damage_dealt", Counter.DIVIDE_BY_TEN);
    public static final MinecraftKey DAMAGE_DEALT_ABSORBED = a("damage_dealt_absorbed", Counter.DIVIDE_BY_TEN);
    public static final MinecraftKey DAMAGE_DEALT_RESISTED = a("damage_dealt_resisted", Counter.DIVIDE_BY_TEN);
    public static final MinecraftKey DAMAGE_TAKEN = a("damage_taken", Counter.DIVIDE_BY_TEN);
    public static final MinecraftKey DAMAGE_BLOCKED_BY_SHIELD = a("damage_blocked_by_shield", Counter.DIVIDE_BY_TEN);
    public static final MinecraftKey DAMAGE_ABSORBED = a("damage_absorbed", Counter.DIVIDE_BY_TEN);
    public static final MinecraftKey DAMAGE_RESISTED = a("damage_resisted", Counter.DIVIDE_BY_TEN);
    public static final MinecraftKey DEATHS = a("deaths", Counter.DEFAULT);
    public static final MinecraftKey MOB_KILLS = a("mob_kills", Counter.DEFAULT);
    public static final MinecraftKey ANIMALS_BRED = a("animals_bred", Counter.DEFAULT);
    public static final MinecraftKey PLAYER_KILLS = a("player_kills", Counter.DEFAULT);
    public static final MinecraftKey FISH_CAUGHT = a("fish_caught", Counter.DEFAULT);
    public static final MinecraftKey TALKED_TO_VILLAGER = a("talked_to_villager", Counter.DEFAULT);
    public static final MinecraftKey TRADED_WITH_VILLAGER = a("traded_with_villager", Counter.DEFAULT);
    public static final MinecraftKey EAT_CAKE_SLICE = a("eat_cake_slice", Counter.DEFAULT);
    public static final MinecraftKey FILL_CAULDRON = a("fill_cauldron", Counter.DEFAULT);
    public static final MinecraftKey USE_CAULDRON = a("use_cauldron", Counter.DEFAULT);
    public static final MinecraftKey CLEAN_ARMOR = a("clean_armor", Counter.DEFAULT);
    public static final MinecraftKey CLEAN_BANNER = a("clean_banner", Counter.DEFAULT);
    public static final MinecraftKey CLEAN_SHULKER_BOX = a("clean_shulker_box", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_BREWINGSTAND = a("interact_with_brewingstand", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_BEACON = a("interact_with_beacon", Counter.DEFAULT);
    public static final MinecraftKey INSPECT_DROPPER = a("inspect_dropper", Counter.DEFAULT);
    public static final MinecraftKey INSPECT_HOPPER = a("inspect_hopper", Counter.DEFAULT);
    public static final MinecraftKey INSPECT_DISPENSER = a("inspect_dispenser", Counter.DEFAULT);
    public static final MinecraftKey PLAY_NOTEBLOCK = a("play_noteblock", Counter.DEFAULT);
    public static final MinecraftKey TUNE_NOTEBLOCK = a("tune_noteblock", Counter.DEFAULT);
    public static final MinecraftKey POT_FLOWER = a("pot_flower", Counter.DEFAULT);
    public static final MinecraftKey TRIGGER_TRAPPED_CHEST = a("trigger_trapped_chest", Counter.DEFAULT);
    public static final MinecraftKey OPEN_ENDERCHEST = a("open_enderchest", Counter.DEFAULT);
    public static final MinecraftKey ENCHANT_ITEM = a("enchant_item", Counter.DEFAULT);
    public static final MinecraftKey PLAY_RECORD = a("play_record", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_FURNACE = a("interact_with_furnace", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_CRAFTING_TABLE = a("interact_with_crafting_table", Counter.DEFAULT);
    public static final MinecraftKey OPEN_CHEST = a("open_chest", Counter.DEFAULT);
    public static final MinecraftKey SLEEP_IN_BED = a("sleep_in_bed", Counter.DEFAULT);
    public static final MinecraftKey OPEN_SHULKER_BOX = a("open_shulker_box", Counter.DEFAULT);
    public static final MinecraftKey OPEN_BARREL = a("open_barrel", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_BLAST_FURNACE = a("interact_with_blast_furnace", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_SMOKER = a("interact_with_smoker", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_LECTERN = a("interact_with_lectern", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_CAMPFIRE = a("interact_with_campfire", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_CARTOGRAPHY_TABLE = a("interact_with_cartography_table", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_LOOM = a("interact_with_loom", Counter.DEFAULT);
    public static final MinecraftKey INTERACT_WITH_STONECUTTER = a("interact_with_stonecutter", Counter.DEFAULT);
    public static final MinecraftKey BELL_RING = a("bell_ring", Counter.DEFAULT);
    public static final MinecraftKey RAID_TRIGGER = a("raid_trigger", Counter.DEFAULT);
    public static final MinecraftKey RAID_WIN = a("raid_win", Counter.DEFAULT);

    private static MinecraftKey a(String s, Counter counter) {
        MinecraftKey minecraftkey = new MinecraftKey(s);

        IRegistry.a(IRegistry.CUSTOM_STAT, s, (Object) minecraftkey);
        StatisticList.CUSTOM.a((Object) minecraftkey, counter);
        return minecraftkey;
    }

    private static <T> StatisticWrapper<T> a(String s, IRegistry<T> iregistry) {
        return (StatisticWrapper) IRegistry.a(IRegistry.STATS, s, (Object) (new StatisticWrapper<>(iregistry)));
    }
}

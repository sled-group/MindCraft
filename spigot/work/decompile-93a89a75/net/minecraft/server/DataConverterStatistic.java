package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public class DataConverterStatistic extends DataFix {

    private static final Set<String> a = ImmutableSet.builder().add("stat.craftItem.minecraft.spawn_egg").add("stat.useItem.minecraft.spawn_egg").add("stat.breakItem.minecraft.spawn_egg").add("stat.pickup.minecraft.spawn_egg").add("stat.drop.minecraft.spawn_egg").build();
    private static final Map<String, String> b = ImmutableMap.builder().put("stat.leaveGame", "minecraft:leave_game").put("stat.playOneMinute", "minecraft:play_one_minute").put("stat.timeSinceDeath", "minecraft:time_since_death").put("stat.sneakTime", "minecraft:sneak_time").put("stat.walkOneCm", "minecraft:walk_one_cm").put("stat.crouchOneCm", "minecraft:crouch_one_cm").put("stat.sprintOneCm", "minecraft:sprint_one_cm").put("stat.swimOneCm", "minecraft:swim_one_cm").put("stat.fallOneCm", "minecraft:fall_one_cm").put("stat.climbOneCm", "minecraft:climb_one_cm").put("stat.flyOneCm", "minecraft:fly_one_cm").put("stat.diveOneCm", "minecraft:dive_one_cm").put("stat.minecartOneCm", "minecraft:minecart_one_cm").put("stat.boatOneCm", "minecraft:boat_one_cm").put("stat.pigOneCm", "minecraft:pig_one_cm").put("stat.horseOneCm", "minecraft:horse_one_cm").put("stat.aviateOneCm", "minecraft:aviate_one_cm").put("stat.jump", "minecraft:jump").put("stat.drop", "minecraft:drop").put("stat.damageDealt", "minecraft:damage_dealt").put("stat.damageTaken", "minecraft:damage_taken").put("stat.deaths", "minecraft:deaths").put("stat.mobKills", "minecraft:mob_kills").put("stat.animalsBred", "minecraft:animals_bred").put("stat.playerKills", "minecraft:player_kills").put("stat.fishCaught", "minecraft:fish_caught").put("stat.talkedToVillager", "minecraft:talked_to_villager").put("stat.tradedWithVillager", "minecraft:traded_with_villager").put("stat.cakeSlicesEaten", "minecraft:eat_cake_slice").put("stat.cauldronFilled", "minecraft:fill_cauldron").put("stat.cauldronUsed", "minecraft:use_cauldron").put("stat.armorCleaned", "minecraft:clean_armor").put("stat.bannerCleaned", "minecraft:clean_banner").put("stat.brewingstandInteraction", "minecraft:interact_with_brewingstand").put("stat.beaconInteraction", "minecraft:interact_with_beacon").put("stat.dropperInspected", "minecraft:inspect_dropper").put("stat.hopperInspected", "minecraft:inspect_hopper").put("stat.dispenserInspected", "minecraft:inspect_dispenser").put("stat.noteblockPlayed", "minecraft:play_noteblock").put("stat.noteblockTuned", "minecraft:tune_noteblock").put("stat.flowerPotted", "minecraft:pot_flower").put("stat.trappedChestTriggered", "minecraft:trigger_trapped_chest").put("stat.enderchestOpened", "minecraft:open_enderchest").put("stat.itemEnchanted", "minecraft:enchant_item").put("stat.recordPlayed", "minecraft:play_record").put("stat.furnaceInteraction", "minecraft:interact_with_furnace").put("stat.craftingTableInteraction", "minecraft:interact_with_crafting_table").put("stat.chestOpened", "minecraft:open_chest").put("stat.sleepInBed", "minecraft:sleep_in_bed").put("stat.shulkerBoxOpened", "minecraft:open_shulker_box").build();
    private static final Map<String, String> c = ImmutableMap.builder().put("stat.craftItem", "minecraft:crafted").put("stat.useItem", "minecraft:used").put("stat.breakItem", "minecraft:broken").put("stat.pickup", "minecraft:picked_up").put("stat.drop", "minecraft:dropped").build();
    private static final Map<String, String> d = ImmutableMap.builder().put("stat.entityKilledBy", "minecraft:killed_by").put("stat.killEntity", "minecraft:killed").build();
    private static final Map<String, String> e = ImmutableMap.builder().put("Bat", "minecraft:bat").put("Blaze", "minecraft:blaze").put("CaveSpider", "minecraft:cave_spider").put("Chicken", "minecraft:chicken").put("Cow", "minecraft:cow").put("Creeper", "minecraft:creeper").put("Donkey", "minecraft:donkey").put("ElderGuardian", "minecraft:elder_guardian").put("Enderman", "minecraft:enderman").put("Endermite", "minecraft:endermite").put("EvocationIllager", "minecraft:evocation_illager").put("Ghast", "minecraft:ghast").put("Guardian", "minecraft:guardian").put("Horse", "minecraft:horse").put("Husk", "minecraft:husk").put("Llama", "minecraft:llama").put("LavaSlime", "minecraft:magma_cube").put("MushroomCow", "minecraft:mooshroom").put("Mule", "minecraft:mule").put("Ozelot", "minecraft:ocelot").put("Parrot", "minecraft:parrot").put("Pig", "minecraft:pig").put("PolarBear", "minecraft:polar_bear").put("Rabbit", "minecraft:rabbit").put("Sheep", "minecraft:sheep").put("Shulker", "minecraft:shulker").put("Silverfish", "minecraft:silverfish").put("SkeletonHorse", "minecraft:skeleton_horse").put("Skeleton", "minecraft:skeleton").put("Slime", "minecraft:slime").put("Spider", "minecraft:spider").put("Squid", "minecraft:squid").put("Stray", "minecraft:stray").put("Vex", "minecraft:vex").put("Villager", "minecraft:villager").put("VindicationIllager", "minecraft:vindication_illager").put("Witch", "minecraft:witch").put("WitherSkeleton", "minecraft:wither_skeleton").put("Wolf", "minecraft:wolf").put("ZombieHorse", "minecraft:zombie_horse").put("PigZombie", "minecraft:zombie_pigman").put("ZombieVillager", "minecraft:zombie_villager").put("Zombie", "minecraft:zombie").build();

    public DataConverterStatistic(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getOutputSchema().getType(DataConverterTypes.g);

        return this.fixTypeEverywhereTyped("StatsCounterFix", this.getInputSchema().getType(DataConverterTypes.g), type, (typed) -> {
            Dynamic<?> dynamic = (Dynamic) typed.get(DSL.remainderFinder());
            Map<Dynamic<?>, Dynamic<?>> map = Maps.newHashMap();
            Optional<? extends Map<? extends Dynamic<?>, ? extends Dynamic<?>>> optional = dynamic.getMapValues();

            if (optional.isPresent()) {
                Iterator iterator = ((Map) optional.get()).entrySet().iterator();

                while (iterator.hasNext()) {
                    Entry<? extends Dynamic<?>, ? extends Dynamic<?>> entry = (Entry) iterator.next();

                    if (((Dynamic) entry.getValue()).asNumber().isPresent()) {
                        String s = ((Dynamic) entry.getKey()).asString("");

                        if (!DataConverterStatistic.a.contains(s)) {
                            String s1;
                            String s2;

                            if (DataConverterStatistic.b.containsKey(s)) {
                                s1 = "minecraft:custom";
                                s2 = (String) DataConverterStatistic.b.get(s);
                            } else {
                                int i = StringUtils.ordinalIndexOf(s, ".", 2);

                                if (i < 0) {
                                    continue;
                                }

                                String s3 = s.substring(0, i);

                                if ("stat.mineBlock".equals(s3)) {
                                    s1 = "minecraft:mined";
                                    s2 = this.b(s.substring(i + 1).replace('.', ':'));
                                } else {
                                    String s4;

                                    if (DataConverterStatistic.c.containsKey(s3)) {
                                        s1 = (String) DataConverterStatistic.c.get(s3);
                                        s4 = s.substring(i + 1).replace('.', ':');
                                        String s5 = this.a(s4);

                                        s2 = s5 == null ? s4 : s5;
                                    } else {
                                        if (!DataConverterStatistic.d.containsKey(s3)) {
                                            continue;
                                        }

                                        s1 = (String) DataConverterStatistic.d.get(s3);
                                        s4 = s.substring(i + 1).replace('.', ':');
                                        s2 = (String) DataConverterStatistic.e.getOrDefault(s4, s4);
                                    }
                                }
                            }

                            Dynamic<?> dynamic1 = dynamic.createString(s1);
                            Dynamic<?> dynamic2 = (Dynamic) map.computeIfAbsent(dynamic1, (dynamic3) -> {
                                return dynamic.emptyMap();
                            });

                            map.put(dynamic1, dynamic2.set(s2, (Dynamic) entry.getValue()));
                        }
                    }
                }
            }

            return (Typed) ((Optional) type.readTyped(dynamic.emptyMap().set("stats", dynamic.createMap(map))).getSecond()).orElseThrow(() -> {
                return new IllegalStateException("Could not parse new stats object.");
            });
        });
    }

    @Nullable
    protected String a(String s) {
        return DataConverterFlatten.a(s, 0);
    }

    protected String b(String s) {
        return DataConverterFlattenData.a(s);
    }
}

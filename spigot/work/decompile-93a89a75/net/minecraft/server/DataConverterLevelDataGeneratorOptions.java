package net.minecraft.server;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.JsonOps;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

public class DataConverterLevelDataGeneratorOptions extends DataFix {

    static final Map<String, String> a = (Map) SystemUtils.a((Object) Maps.newHashMap(), (hashmap) -> {
        hashmap.put("0", "minecraft:ocean");
        hashmap.put("1", "minecraft:plains");
        hashmap.put("2", "minecraft:desert");
        hashmap.put("3", "minecraft:mountains");
        hashmap.put("4", "minecraft:forest");
        hashmap.put("5", "minecraft:taiga");
        hashmap.put("6", "minecraft:swamp");
        hashmap.put("7", "minecraft:river");
        hashmap.put("8", "minecraft:nether");
        hashmap.put("9", "minecraft:the_end");
        hashmap.put("10", "minecraft:frozen_ocean");
        hashmap.put("11", "minecraft:frozen_river");
        hashmap.put("12", "minecraft:snowy_tundra");
        hashmap.put("13", "minecraft:snowy_mountains");
        hashmap.put("14", "minecraft:mushroom_fields");
        hashmap.put("15", "minecraft:mushroom_field_shore");
        hashmap.put("16", "minecraft:beach");
        hashmap.put("17", "minecraft:desert_hills");
        hashmap.put("18", "minecraft:wooded_hills");
        hashmap.put("19", "minecraft:taiga_hills");
        hashmap.put("20", "minecraft:mountain_edge");
        hashmap.put("21", "minecraft:jungle");
        hashmap.put("22", "minecraft:jungle_hills");
        hashmap.put("23", "minecraft:jungle_edge");
        hashmap.put("24", "minecraft:deep_ocean");
        hashmap.put("25", "minecraft:stone_shore");
        hashmap.put("26", "minecraft:snowy_beach");
        hashmap.put("27", "minecraft:birch_forest");
        hashmap.put("28", "minecraft:birch_forest_hills");
        hashmap.put("29", "minecraft:dark_forest");
        hashmap.put("30", "minecraft:snowy_taiga");
        hashmap.put("31", "minecraft:snowy_taiga_hills");
        hashmap.put("32", "minecraft:giant_tree_taiga");
        hashmap.put("33", "minecraft:giant_tree_taiga_hills");
        hashmap.put("34", "minecraft:wooded_mountains");
        hashmap.put("35", "minecraft:savanna");
        hashmap.put("36", "minecraft:savanna_plateau");
        hashmap.put("37", "minecraft:badlands");
        hashmap.put("38", "minecraft:wooded_badlands_plateau");
        hashmap.put("39", "minecraft:badlands_plateau");
        hashmap.put("40", "minecraft:small_end_islands");
        hashmap.put("41", "minecraft:end_midlands");
        hashmap.put("42", "minecraft:end_highlands");
        hashmap.put("43", "minecraft:end_barrens");
        hashmap.put("44", "minecraft:warm_ocean");
        hashmap.put("45", "minecraft:lukewarm_ocean");
        hashmap.put("46", "minecraft:cold_ocean");
        hashmap.put("47", "minecraft:deep_warm_ocean");
        hashmap.put("48", "minecraft:deep_lukewarm_ocean");
        hashmap.put("49", "minecraft:deep_cold_ocean");
        hashmap.put("50", "minecraft:deep_frozen_ocean");
        hashmap.put("127", "minecraft:the_void");
        hashmap.put("129", "minecraft:sunflower_plains");
        hashmap.put("130", "minecraft:desert_lakes");
        hashmap.put("131", "minecraft:gravelly_mountains");
        hashmap.put("132", "minecraft:flower_forest");
        hashmap.put("133", "minecraft:taiga_mountains");
        hashmap.put("134", "minecraft:swamp_hills");
        hashmap.put("140", "minecraft:ice_spikes");
        hashmap.put("149", "minecraft:modified_jungle");
        hashmap.put("151", "minecraft:modified_jungle_edge");
        hashmap.put("155", "minecraft:tall_birch_forest");
        hashmap.put("156", "minecraft:tall_birch_hills");
        hashmap.put("157", "minecraft:dark_forest_hills");
        hashmap.put("158", "minecraft:snowy_taiga_mountains");
        hashmap.put("160", "minecraft:giant_spruce_taiga");
        hashmap.put("161", "minecraft:giant_spruce_taiga_hills");
        hashmap.put("162", "minecraft:modified_gravelly_mountains");
        hashmap.put("163", "minecraft:shattered_savanna");
        hashmap.put("164", "minecraft:shattered_savanna_plateau");
        hashmap.put("165", "minecraft:eroded_badlands");
        hashmap.put("166", "minecraft:modified_wooded_badlands_plateau");
        hashmap.put("167", "minecraft:modified_badlands_plateau");
    });

    public DataConverterLevelDataGeneratorOptions(Schema schema, boolean flag) {
        super(schema, flag);
    }

    protected TypeRewriteRule makeRule() {
        Type<?> type = this.getOutputSchema().getType(DataConverterTypes.a);

        return this.fixTypeEverywhereTyped("LevelDataGeneratorOptionsFix", this.getInputSchema().getType(DataConverterTypes.a), type, (typed) -> {
            Dynamic<?> dynamic = typed.write();
            Optional<String> optional = dynamic.get("generatorOptions").asString();
            Dynamic dynamic1;

            if ("flat".equalsIgnoreCase(dynamic.get("generatorName").asString(""))) {
                String s = (String) optional.orElse("");

                dynamic1 = dynamic.set("generatorOptions", a(s, dynamic.getOps()));
            } else if ("buffet".equalsIgnoreCase(dynamic.get("generatorName").asString("")) && optional.isPresent()) {
                Dynamic<JsonElement> dynamic2 = new Dynamic(JsonOps.INSTANCE, ChatDeserializer.a((String) optional.get(), true));

                dynamic1 = dynamic.set("generatorOptions", dynamic2.convert(dynamic.getOps()));
            } else {
                dynamic1 = dynamic;
            }

            return (Typed) ((Optional) type.readTyped(dynamic1).getSecond()).orElseThrow(() -> {
                return new IllegalStateException("Could not read new level type.");
            });
        });
    }

    private static <T> Dynamic<T> a(String s, DynamicOps<T> dynamicops) {
        Iterator<String> iterator = Splitter.on(';').split(s).iterator();
        String s1 = "minecraft:plains";
        Map<String, Map<String, String>> map = Maps.newHashMap();
        Object object;

        if (!s.isEmpty() && iterator.hasNext()) {
            object = b((String) iterator.next());
            if (!((List) object).isEmpty()) {
                if (iterator.hasNext()) {
                    s1 = (String) DataConverterLevelDataGeneratorOptions.a.getOrDefault(iterator.next(), "minecraft:plains");
                }

                if (iterator.hasNext()) {
                    String[] astring = ((String) iterator.next()).toLowerCase(Locale.ROOT).split(",");
                    String[] astring1 = astring;
                    int i = astring.length;

                    for (int j = 0; j < i; ++j) {
                        String s2 = astring1[j];
                        String[] astring2 = s2.split("\\(", 2);

                        if (!astring2[0].isEmpty()) {
                            map.put(astring2[0], Maps.newHashMap());
                            if (astring2.length > 1 && astring2[1].endsWith(")") && astring2[1].length() > 1) {
                                String[] astring3 = astring2[1].substring(0, astring2[1].length() - 1).split(" ");
                                String[] astring4 = astring3;
                                int k = astring3.length;

                                for (int l = 0; l < k; ++l) {
                                    String s3 = astring4[l];
                                    String[] astring5 = s3.split("=", 2);

                                    if (astring5.length == 2) {
                                        ((Map) map.get(astring2[0])).put(astring5[0], astring5[1]);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    map.put("village", Maps.newHashMap());
                }
            }
        } else {
            object = Lists.newArrayList();
            ((List) object).add(Pair.of(1, "minecraft:bedrock"));
            ((List) object).add(Pair.of(2, "minecraft:dirt"));
            ((List) object).add(Pair.of(1, "minecraft:grass_block"));
            map.put("village", Maps.newHashMap());
        }

        T t0 = dynamicops.createList(((List) object).stream().map((pair) -> {
            return dynamicops.createMap(ImmutableMap.of(dynamicops.createString("height"), dynamicops.createInt((Integer) pair.getFirst()), dynamicops.createString("block"), dynamicops.createString((String) pair.getSecond())));
        }));
        T t1 = dynamicops.createMap((Map) map.entrySet().stream().map((entry) -> {
            return Pair.of(dynamicops.createString(((String) entry.getKey()).toLowerCase(Locale.ROOT)), dynamicops.createMap((Map) ((Map) entry.getValue()).entrySet().stream().map((entry1) -> {
                return Pair.of(dynamicops.createString((String) entry1.getKey()), dynamicops.createString((String) entry1.getValue()));
            }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond))));
        }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)));

        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("layers"), t0, dynamicops.createString("biome"), dynamicops.createString(s1), dynamicops.createString("structures"), t1)));
    }

    @Nullable
    private static Pair<Integer, String> a(String s) {
        String[] astring = s.split("\\*", 2);
        int i;

        if (astring.length == 2) {
            try {
                i = Integer.parseInt(astring[0]);
            } catch (NumberFormatException numberformatexception) {
                return null;
            }
        } else {
            i = 1;
        }

        String s1 = astring[astring.length - 1];

        return Pair.of(i, s1);
    }

    private static List<Pair<Integer, String>> b(String s) {
        List<Pair<Integer, String>> list = Lists.newArrayList();
        String[] astring = s.split(",");
        String[] astring1 = astring;
        int i = astring.length;

        for (int j = 0; j < i; ++j) {
            String s1 = astring1[j];
            Pair<Integer, String> pair = a(s1);

            if (pair == null) {
                return Collections.emptyList();
            }

            list.add(pair);
        }

        return list;
    }
}

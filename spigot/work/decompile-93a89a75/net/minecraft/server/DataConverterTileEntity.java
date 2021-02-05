package net.minecraft.server;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TaggedChoice.TaggedChoiceType;
import com.mojang.datafixers.util.Pair;
import java.util.HashMap;
import java.util.Map;

public class DataConverterTileEntity extends DataFix {

    private static final Map<String, String> a = (Map) DataFixUtils.make(Maps.newHashMap(), (hashmap) -> {
        hashmap.put("Airportal", "minecraft:end_portal");
        hashmap.put("Banner", "minecraft:banner");
        hashmap.put("Beacon", "minecraft:beacon");
        hashmap.put("Cauldron", "minecraft:brewing_stand");
        hashmap.put("Chest", "minecraft:chest");
        hashmap.put("Comparator", "minecraft:comparator");
        hashmap.put("Control", "minecraft:command_block");
        hashmap.put("DLDetector", "minecraft:daylight_detector");
        hashmap.put("Dropper", "minecraft:dropper");
        hashmap.put("EnchantTable", "minecraft:enchanting_table");
        hashmap.put("EndGateway", "minecraft:end_gateway");
        hashmap.put("EnderChest", "minecraft:ender_chest");
        hashmap.put("FlowerPot", "minecraft:flower_pot");
        hashmap.put("Furnace", "minecraft:furnace");
        hashmap.put("Hopper", "minecraft:hopper");
        hashmap.put("MobSpawner", "minecraft:mob_spawner");
        hashmap.put("Music", "minecraft:noteblock");
        hashmap.put("Piston", "minecraft:piston");
        hashmap.put("RecordPlayer", "minecraft:jukebox");
        hashmap.put("Sign", "minecraft:sign");
        hashmap.put("Skull", "minecraft:skull");
        hashmap.put("Structure", "minecraft:structure_block");
        hashmap.put("Trap", "minecraft:dispenser");
    });

    public DataConverterTileEntity(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.ITEM_STACK);
        Type<?> type1 = this.getOutputSchema().getType(DataConverterTypes.ITEM_STACK);
        TaggedChoiceType<String> taggedchoicetype = this.getInputSchema().findChoiceType(DataConverterTypes.k);
        TaggedChoiceType<String> taggedchoicetype1 = this.getOutputSchema().findChoiceType(DataConverterTypes.k);

        return TypeRewriteRule.seq(this.convertUnchecked("item stack block entity name hook converter", type, type1), this.fixTypeEverywhere("BlockEntityIdFix", taggedchoicetype, taggedchoicetype1, (dynamicops) -> {
            return (pair) -> {
                return pair.mapFirst((s) -> {
                    return (String) DataConverterTileEntity.a.getOrDefault(s, s);
                });
            };
        }));
    }
}

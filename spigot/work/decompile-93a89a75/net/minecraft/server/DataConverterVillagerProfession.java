package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;

public class DataConverterVillagerProfession extends DataConverterNamedEntity {

    public DataConverterVillagerProfession(Schema schema, String s) {
        super(schema, false, "Villager profession data fix (" + s + ")", DataConverterTypes.ENTITY, s);
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        Dynamic<?> dynamic = (Dynamic) typed.get(DSL.remainderFinder());

        return typed.set(DSL.remainderFinder(), dynamic.remove("Profession").remove("Career").remove("CareerLevel").set("VillagerData", dynamic.createMap(ImmutableMap.of(dynamic.createString("type"), dynamic.createString("minecraft:plains"), dynamic.createString("profession"), dynamic.createString(a(dynamic.get("Profession").asInt(0), dynamic.get("Career").asInt(0))), dynamic.createString("level"), DataFixUtils.orElse(dynamic.get("CareerLevel").get(), dynamic.createInt(1))))));
    }

    private static String a(int i, int j) {
        return i == 0 ? (j == 2 ? "minecraft:fisherman" : (j == 3 ? "minecraft:shepherd" : (j == 4 ? "minecraft:fletcher" : "minecraft:farmer"))) : (i == 1 ? (j == 2 ? "minecraft:cartographer" : "minecraft:librarian") : (i == 2 ? "minecraft:cleric" : (i == 3 ? (j == 2 ? "minecraft:weaponsmith" : (j == 3 ? "minecraft:toolsmith" : "minecraft:armorer")) : (i == 4 ? (j == 2 ? "minecraft:leatherworker" : "minecraft:butcher") : (i == 5 ? "minecraft:nitwit" : "minecraft:none")))));
    }
}

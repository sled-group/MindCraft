package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;

public class DataConverterSchemaV100 extends Schema {

    public DataConverterSchemaV100(int i, Schema schema) {
        super(i, schema);
    }

    protected static TypeTemplate a(Schema schema) {
        return DSL.optionalFields("ArmorItems", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)), "HandItems", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
    }

    protected static void a(Schema schema, Map<String, Supplier<TypeTemplate>> map, String s) {
        schema.register(map, s, () -> {
            return a(schema);
        });
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = super.registerEntities(schema);

        a(schema, map, "ArmorStand");
        a(schema, map, "Creeper");
        a(schema, map, "Skeleton");
        a(schema, map, "Spider");
        a(schema, map, "Giant");
        a(schema, map, "Zombie");
        a(schema, map, "Slime");
        a(schema, map, "Ghast");
        a(schema, map, "PigZombie");
        schema.register(map, "Enderman", (s) -> {
            return DSL.optionalFields("carried", DataConverterTypes.q.in(schema), a(schema));
        });
        a(schema, map, "CaveSpider");
        a(schema, map, "Silverfish");
        a(schema, map, "Blaze");
        a(schema, map, "LavaSlime");
        a(schema, map, "EnderDragon");
        a(schema, map, "WitherBoss");
        a(schema, map, "Bat");
        a(schema, map, "Witch");
        a(schema, map, "Endermite");
        a(schema, map, "Guardian");
        a(schema, map, "Pig");
        a(schema, map, "Sheep");
        a(schema, map, "Cow");
        a(schema, map, "Chicken");
        a(schema, map, "Squid");
        a(schema, map, "Wolf");
        a(schema, map, "MushroomCow");
        a(schema, map, "SnowMan");
        a(schema, map, "Ozelot");
        a(schema, map, "VillagerGolem");
        schema.register(map, "EntityHorse", (s) -> {
            return DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)), "ArmorItem", DataConverterTypes.ITEM_STACK.in(schema), "SaddleItem", DataConverterTypes.ITEM_STACK.in(schema), a(schema));
        });
        a(schema, map, "Rabbit");
        schema.register(map, "Villager", (s) -> {
            return DSL.optionalFields("Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)), "Offers", DSL.optionalFields("Recipes", DSL.list(DSL.optionalFields("buy", DataConverterTypes.ITEM_STACK.in(schema), "buyB", DataConverterTypes.ITEM_STACK.in(schema), "sell", DataConverterTypes.ITEM_STACK.in(schema)))), a(schema));
        });
        a(schema, map, "Shulker");
        schema.registerSimple(map, "AreaEffectCloud");
        schema.registerSimple(map, "ShulkerBullet");
        return map;
    }

    public void registerTypes(Schema schema, Map<String, Supplier<TypeTemplate>> map, Map<String, Supplier<TypeTemplate>> map1) {
        super.registerTypes(schema, map, map1);
        schema.registerType(false, DataConverterTypes.f, () -> {
            return DSL.optionalFields("entities", DSL.list(DSL.optionalFields("nbt", DataConverterTypes.o.in(schema))), "blocks", DSL.list(DSL.optionalFields("nbt", DataConverterTypes.k.in(schema))), "palette", DSL.list(DataConverterTypes.m.in(schema)));
        });
        schema.registerType(false, DataConverterTypes.m, DSL::remainder);
    }
}

package net.minecraft.server;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.templates.TypeTemplate;
import com.mojang.datafixers.types.templates.Hook.HookFunction;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataConverterSchemaV99 extends Schema {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<String, String> c = (Map) DataFixUtils.make(Maps.newHashMap(), (hashmap) -> {
        hashmap.put("minecraft:furnace", "Furnace");
        hashmap.put("minecraft:lit_furnace", "Furnace");
        hashmap.put("minecraft:chest", "Chest");
        hashmap.put("minecraft:trapped_chest", "Chest");
        hashmap.put("minecraft:ender_chest", "EnderChest");
        hashmap.put("minecraft:jukebox", "RecordPlayer");
        hashmap.put("minecraft:dispenser", "Trap");
        hashmap.put("minecraft:dropper", "Dropper");
        hashmap.put("minecraft:sign", "Sign");
        hashmap.put("minecraft:mob_spawner", "MobSpawner");
        hashmap.put("minecraft:noteblock", "Music");
        hashmap.put("minecraft:brewing_stand", "Cauldron");
        hashmap.put("minecraft:enhanting_table", "EnchantTable");
        hashmap.put("minecraft:command_block", "CommandBlock");
        hashmap.put("minecraft:beacon", "Beacon");
        hashmap.put("minecraft:skull", "Skull");
        hashmap.put("minecraft:daylight_detector", "DLDetector");
        hashmap.put("minecraft:hopper", "Hopper");
        hashmap.put("minecraft:banner", "Banner");
        hashmap.put("minecraft:flower_pot", "FlowerPot");
        hashmap.put("minecraft:repeating_command_block", "CommandBlock");
        hashmap.put("minecraft:chain_command_block", "CommandBlock");
        hashmap.put("minecraft:standing_sign", "Sign");
        hashmap.put("minecraft:wall_sign", "Sign");
        hashmap.put("minecraft:piston_head", "Piston");
        hashmap.put("minecraft:daylight_detector_inverted", "DLDetector");
        hashmap.put("minecraft:unpowered_comparator", "Comparator");
        hashmap.put("minecraft:powered_comparator", "Comparator");
        hashmap.put("minecraft:wall_banner", "Banner");
        hashmap.put("minecraft:standing_banner", "Banner");
        hashmap.put("minecraft:structure_block", "Structure");
        hashmap.put("minecraft:end_portal", "Airportal");
        hashmap.put("minecraft:end_gateway", "EndGateway");
        hashmap.put("minecraft:shield", "Banner");
    });
    protected static final HookFunction a = new HookFunction() {
        public <T> T apply(DynamicOps<T> dynamicops, T t0) {
            return DataConverterSchemaV99.a(new Dynamic(dynamicops, t0), DataConverterSchemaV99.c, "ArmorStand");
        }
    };

    public DataConverterSchemaV99(int i, Schema schema) {
        super(i, schema);
    }

    protected static TypeTemplate a(Schema schema) {
        return DSL.optionalFields("Equipment", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
    }

    protected static void a(Schema schema, Map<String, Supplier<TypeTemplate>> map, String s) {
        schema.register(map, s, () -> {
            return a(schema);
        });
    }

    protected static void b(Schema schema, Map<String, Supplier<TypeTemplate>> map, String s) {
        schema.register(map, s, () -> {
            return DSL.optionalFields("inTile", DataConverterTypes.q.in(schema));
        });
    }

    protected static void c(Schema schema, Map<String, Supplier<TypeTemplate>> map, String s) {
        schema.register(map, s, () -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema));
        });
    }

    protected static void d(Schema schema, Map<String, Supplier<TypeTemplate>> map, String s) {
        schema.register(map, s, () -> {
            return DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
        });
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = Maps.newHashMap();

        schema.register(map, "Item", (s) -> {
            return DSL.optionalFields("Item", DataConverterTypes.ITEM_STACK.in(schema));
        });
        schema.registerSimple(map, "XPOrb");
        b(schema, map, "ThrownEgg");
        schema.registerSimple(map, "LeashKnot");
        schema.registerSimple(map, "Painting");
        schema.register(map, "Arrow", (s) -> {
            return DSL.optionalFields("inTile", DataConverterTypes.q.in(schema));
        });
        schema.register(map, "TippedArrow", (s) -> {
            return DSL.optionalFields("inTile", DataConverterTypes.q.in(schema));
        });
        schema.register(map, "SpectralArrow", (s) -> {
            return DSL.optionalFields("inTile", DataConverterTypes.q.in(schema));
        });
        b(schema, map, "Snowball");
        b(schema, map, "Fireball");
        b(schema, map, "SmallFireball");
        b(schema, map, "ThrownEnderpearl");
        schema.registerSimple(map, "EyeOfEnderSignal");
        schema.register(map, "ThrownPotion", (s) -> {
            return DSL.optionalFields("inTile", DataConverterTypes.q.in(schema), "Potion", DataConverterTypes.ITEM_STACK.in(schema));
        });
        b(schema, map, "ThrownExpBottle");
        schema.register(map, "ItemFrame", (s) -> {
            return DSL.optionalFields("Item", DataConverterTypes.ITEM_STACK.in(schema));
        });
        b(schema, map, "WitherSkull");
        schema.registerSimple(map, "PrimedTnt");
        schema.register(map, "FallingSand", (s) -> {
            return DSL.optionalFields("Block", DataConverterTypes.q.in(schema), "TileEntityData", DataConverterTypes.k.in(schema));
        });
        schema.register(map, "FireworksRocketEntity", (s) -> {
            return DSL.optionalFields("FireworksItem", DataConverterTypes.ITEM_STACK.in(schema));
        });
        schema.registerSimple(map, "Boat");
        schema.register(map, "Minecart", () -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
        });
        c(schema, map, "MinecartRideable");
        schema.register(map, "MinecartChest", (s) -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
        });
        c(schema, map, "MinecartFurnace");
        c(schema, map, "MinecartTNT");
        schema.register(map, "MinecartSpawner", () -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema), DataConverterTypes.s.in(schema));
        });
        schema.register(map, "MinecartHopper", (s) -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
        });
        c(schema, map, "MinecartCommandBlock");
        a(schema, (Map) map, "ArmorStand");
        a(schema, (Map) map, "Creeper");
        a(schema, (Map) map, "Skeleton");
        a(schema, (Map) map, "Spider");
        a(schema, (Map) map, "Giant");
        a(schema, (Map) map, "Zombie");
        a(schema, (Map) map, "Slime");
        a(schema, (Map) map, "Ghast");
        a(schema, (Map) map, "PigZombie");
        schema.register(map, "Enderman", (s) -> {
            return DSL.optionalFields("carried", DataConverterTypes.q.in(schema), a(schema));
        });
        a(schema, (Map) map, "CaveSpider");
        a(schema, (Map) map, "Silverfish");
        a(schema, (Map) map, "Blaze");
        a(schema, (Map) map, "LavaSlime");
        a(schema, (Map) map, "EnderDragon");
        a(schema, (Map) map, "WitherBoss");
        a(schema, (Map) map, "Bat");
        a(schema, (Map) map, "Witch");
        a(schema, (Map) map, "Endermite");
        a(schema, (Map) map, "Guardian");
        a(schema, (Map) map, "Pig");
        a(schema, (Map) map, "Sheep");
        a(schema, (Map) map, "Cow");
        a(schema, (Map) map, "Chicken");
        a(schema, (Map) map, "Squid");
        a(schema, (Map) map, "Wolf");
        a(schema, (Map) map, "MushroomCow");
        a(schema, (Map) map, "SnowMan");
        a(schema, (Map) map, "Ozelot");
        a(schema, (Map) map, "VillagerGolem");
        schema.register(map, "EntityHorse", (s) -> {
            return DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)), "ArmorItem", DataConverterTypes.ITEM_STACK.in(schema), "SaddleItem", DataConverterTypes.ITEM_STACK.in(schema), a(schema));
        });
        a(schema, (Map) map, "Rabbit");
        schema.register(map, "Villager", (s) -> {
            return DSL.optionalFields("Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)), "Offers", DSL.optionalFields("Recipes", DSL.list(DSL.optionalFields("buy", DataConverterTypes.ITEM_STACK.in(schema), "buyB", DataConverterTypes.ITEM_STACK.in(schema), "sell", DataConverterTypes.ITEM_STACK.in(schema)))), a(schema));
        });
        schema.registerSimple(map, "EnderCrystal");
        schema.registerSimple(map, "AreaEffectCloud");
        schema.registerSimple(map, "ShulkerBullet");
        a(schema, (Map) map, "Shulker");
        return map;
    }

    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = Maps.newHashMap();

        d(schema, map, "Furnace");
        d(schema, map, "Chest");
        schema.registerSimple(map, "EnderChest");
        schema.register(map, "RecordPlayer", (s) -> {
            return DSL.optionalFields("RecordItem", DataConverterTypes.ITEM_STACK.in(schema));
        });
        d(schema, map, "Trap");
        d(schema, map, "Dropper");
        schema.registerSimple(map, "Sign");
        schema.register(map, "MobSpawner", (s) -> {
            return DataConverterTypes.s.in(schema);
        });
        schema.registerSimple(map, "Music");
        schema.registerSimple(map, "Piston");
        d(schema, map, "Cauldron");
        schema.registerSimple(map, "EnchantTable");
        schema.registerSimple(map, "Airportal");
        schema.registerSimple(map, "Control");
        schema.registerSimple(map, "Beacon");
        schema.registerSimple(map, "Skull");
        schema.registerSimple(map, "DLDetector");
        d(schema, map, "Hopper");
        schema.registerSimple(map, "Comparator");
        schema.register(map, "FlowerPot", (s) -> {
            return DSL.optionalFields("Item", DSL.or(DSL.constType(DSL.intType()), DataConverterTypes.r.in(schema)));
        });
        schema.registerSimple(map, "Banner");
        schema.registerSimple(map, "Structure");
        schema.registerSimple(map, "EndGateway");
        return map;
    }

    public void registerTypes(Schema schema, Map<String, Supplier<TypeTemplate>> map, Map<String, Supplier<TypeTemplate>> map1) {
        schema.registerType(false, DataConverterTypes.a, DSL::remainder);
        schema.registerType(false, DataConverterTypes.PLAYER, () -> {
            return DSL.optionalFields("Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)), "EnderItems", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
        });
        schema.registerType(false, DataConverterTypes.c, () -> {
            return DSL.fields("Level", DSL.optionalFields("Entities", DSL.list(DataConverterTypes.o.in(schema)), "TileEntities", DSL.list(DataConverterTypes.k.in(schema)), "TileTicks", DSL.list(DSL.fields("i", DataConverterTypes.q.in(schema)))));
        });
        schema.registerType(true, DataConverterTypes.k, () -> {
            return DSL.taggedChoiceLazy("id", DSL.string(), map1);
        });
        schema.registerType(true, DataConverterTypes.o, () -> {
            return DSL.optionalFields("Riding", DataConverterTypes.o.in(schema), DataConverterTypes.ENTITY.in(schema));
        });
        schema.registerType(false, DataConverterTypes.n, () -> {
            return DSL.constType(DSL.namespacedString());
        });
        schema.registerType(true, DataConverterTypes.ENTITY, () -> {
            return DSL.taggedChoiceLazy("id", DSL.string(), map);
        });
        schema.registerType(true, DataConverterTypes.ITEM_STACK, () -> {
            return DSL.hook(DSL.optionalFields("id", DSL.or(DSL.constType(DSL.intType()), DataConverterTypes.r.in(schema)), "tag", DSL.optionalFields("EntityTag", DataConverterTypes.o.in(schema), "BlockEntityTag", DataConverterTypes.k.in(schema), "CanDestroy", DSL.list(DataConverterTypes.q.in(schema)), "CanPlaceOn", DSL.list(DataConverterTypes.q.in(schema)))), DataConverterSchemaV99.a, HookFunction.IDENTITY);
        });
        schema.registerType(false, DataConverterTypes.e, DSL::remainder);
        schema.registerType(false, DataConverterTypes.q, () -> {
            return DSL.or(DSL.constType(DSL.intType()), DSL.constType(DSL.namespacedString()));
        });
        schema.registerType(false, DataConverterTypes.r, () -> {
            return DSL.constType(DSL.namespacedString());
        });
        schema.registerType(false, DataConverterTypes.g, DSL::remainder);
        schema.registerType(false, DataConverterTypes.h, () -> {
            return DSL.optionalFields("data", DSL.optionalFields("Features", DSL.compoundList(DataConverterTypes.t.in(schema)), "Objectives", DSL.list(DataConverterTypes.u.in(schema)), "Teams", DSL.list(DataConverterTypes.v.in(schema))));
        });
        schema.registerType(false, DataConverterTypes.t, DSL::remainder);
        schema.registerType(false, DataConverterTypes.u, DSL::remainder);
        schema.registerType(false, DataConverterTypes.v, DSL::remainder);
        schema.registerType(true, DataConverterTypes.s, DSL::remainder);
        schema.registerType(false, DataConverterTypes.j, DSL::remainder);
    }

    protected static <T> T a(Dynamic<T> dynamic, Map<String, String> map, String s) {
        return dynamic.update("tag", (dynamic1) -> {
            return dynamic1.update("BlockEntityTag", (dynamic2) -> {
                String s1 = dynamic.get("id").asString("");
                String s2 = (String) map.get(DataConverterSchemaNamed.a(s1));

                if (s2 == null) {
                    DataConverterSchemaV99.LOGGER.warn("Unable to resolve BlockEntity for ItemStack: {}", s1);
                    return dynamic2;
                } else {
                    return dynamic2.set("id", dynamic.createString(s2));
                }
            }).update("EntityTag", (dynamic2) -> {
                String s1 = dynamic.get("id").asString("");

                return Objects.equals(DataConverterSchemaNamed.a(s1), "minecraft:armor_stand") ? dynamic2.set("id", dynamic.createString(s)) : dynamic2;
            });
        }).getValue();
    }
}

package net.minecraft.server;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.templates.TypeTemplate;
import com.mojang.datafixers.types.templates.Hook.HookFunction;
import java.util.Map;
import java.util.function.Supplier;

public class DataConverterSchemaV705 extends DataConverterSchemaNamed {

    protected static final HookFunction a = new HookFunction() {
        public <T> T apply(DynamicOps<T> dynamicops, T t0) {
            return DataConverterSchemaV99.a(new Dynamic(dynamicops, t0), DataConverterSchemaV704.a, "minecraft:armor_stand");
        }
    };

    public DataConverterSchemaV705(int i, Schema schema) {
        super(i, schema);
    }

    protected static void a(Schema schema, Map<String, Supplier<TypeTemplate>> map, String s) {
        schema.register(map, s, () -> {
            return DataConverterSchemaV100.a(schema);
        });
    }

    protected static void b(Schema schema, Map<String, Supplier<TypeTemplate>> map, String s) {
        schema.register(map, s, () -> {
            return DSL.optionalFields("inTile", DataConverterTypes.q.in(schema));
        });
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = Maps.newHashMap();

        schema.registerSimple(map, "minecraft:area_effect_cloud");
        a(schema, map, "minecraft:armor_stand");
        schema.register(map, "minecraft:arrow", (s) -> {
            return DSL.optionalFields("inTile", DataConverterTypes.q.in(schema));
        });
        a(schema, map, "minecraft:bat");
        a(schema, map, "minecraft:blaze");
        schema.registerSimple(map, "minecraft:boat");
        a(schema, map, "minecraft:cave_spider");
        schema.register(map, "minecraft:chest_minecart", (s) -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
        });
        a(schema, map, "minecraft:chicken");
        schema.register(map, "minecraft:commandblock_minecart", (s) -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema));
        });
        a(schema, map, "minecraft:cow");
        a(schema, map, "minecraft:creeper");
        schema.register(map, "minecraft:donkey", (s) -> {
            return DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(schema), DataConverterSchemaV100.a(schema));
        });
        schema.registerSimple(map, "minecraft:dragon_fireball");
        b(schema, map, "minecraft:egg");
        a(schema, map, "minecraft:elder_guardian");
        schema.registerSimple(map, "minecraft:ender_crystal");
        a(schema, map, "minecraft:ender_dragon");
        schema.register(map, "minecraft:enderman", (s) -> {
            return DSL.optionalFields("carried", DataConverterTypes.q.in(schema), DataConverterSchemaV100.a(schema));
        });
        a(schema, map, "minecraft:endermite");
        b(schema, map, "minecraft:ender_pearl");
        schema.registerSimple(map, "minecraft:eye_of_ender_signal");
        schema.register(map, "minecraft:falling_block", (s) -> {
            return DSL.optionalFields("Block", DataConverterTypes.q.in(schema), "TileEntityData", DataConverterTypes.k.in(schema));
        });
        b(schema, map, "minecraft:fireball");
        schema.register(map, "minecraft:fireworks_rocket", (s) -> {
            return DSL.optionalFields("FireworksItem", DataConverterTypes.ITEM_STACK.in(schema));
        });
        schema.register(map, "minecraft:furnace_minecart", (s) -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema));
        });
        a(schema, map, "minecraft:ghast");
        a(schema, map, "minecraft:giant");
        a(schema, map, "minecraft:guardian");
        schema.register(map, "minecraft:hopper_minecart", (s) -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
        });
        schema.register(map, "minecraft:horse", (s) -> {
            return DSL.optionalFields("ArmorItem", DataConverterTypes.ITEM_STACK.in(schema), "SaddleItem", DataConverterTypes.ITEM_STACK.in(schema), DataConverterSchemaV100.a(schema));
        });
        a(schema, map, "minecraft:husk");
        schema.register(map, "minecraft:item", (s) -> {
            return DSL.optionalFields("Item", DataConverterTypes.ITEM_STACK.in(schema));
        });
        schema.register(map, "minecraft:item_frame", (s) -> {
            return DSL.optionalFields("Item", DataConverterTypes.ITEM_STACK.in(schema));
        });
        schema.registerSimple(map, "minecraft:leash_knot");
        a(schema, map, "minecraft:magma_cube");
        schema.register(map, "minecraft:minecart", (s) -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema));
        });
        a(schema, map, "minecraft:mooshroom");
        schema.register(map, "minecraft:mule", (s) -> {
            return DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(schema), DataConverterSchemaV100.a(schema));
        });
        a(schema, map, "minecraft:ocelot");
        schema.registerSimple(map, "minecraft:painting");
        schema.registerSimple(map, "minecraft:parrot");
        a(schema, map, "minecraft:pig");
        a(schema, map, "minecraft:polar_bear");
        schema.register(map, "minecraft:potion", (s) -> {
            return DSL.optionalFields("Potion", DataConverterTypes.ITEM_STACK.in(schema), "inTile", DataConverterTypes.q.in(schema));
        });
        a(schema, map, "minecraft:rabbit");
        a(schema, map, "minecraft:sheep");
        a(schema, map, "minecraft:shulker");
        schema.registerSimple(map, "minecraft:shulker_bullet");
        a(schema, map, "minecraft:silverfish");
        a(schema, map, "minecraft:skeleton");
        schema.register(map, "minecraft:skeleton_horse", (s) -> {
            return DSL.optionalFields("SaddleItem", DataConverterTypes.ITEM_STACK.in(schema), DataConverterSchemaV100.a(schema));
        });
        a(schema, map, "minecraft:slime");
        b(schema, map, "minecraft:small_fireball");
        b(schema, map, "minecraft:snowball");
        a(schema, map, "minecraft:snowman");
        schema.register(map, "minecraft:spawner_minecart", (s) -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema), DataConverterTypes.s.in(schema));
        });
        schema.register(map, "minecraft:spectral_arrow", (s) -> {
            return DSL.optionalFields("inTile", DataConverterTypes.q.in(schema));
        });
        a(schema, map, "minecraft:spider");
        a(schema, map, "minecraft:squid");
        a(schema, map, "minecraft:stray");
        schema.registerSimple(map, "minecraft:tnt");
        schema.register(map, "minecraft:tnt_minecart", (s) -> {
            return DSL.optionalFields("DisplayTile", DataConverterTypes.q.in(schema));
        });
        schema.register(map, "minecraft:villager", (s) -> {
            return DSL.optionalFields("Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)), "Offers", DSL.optionalFields("Recipes", DSL.list(DSL.optionalFields("buy", DataConverterTypes.ITEM_STACK.in(schema), "buyB", DataConverterTypes.ITEM_STACK.in(schema), "sell", DataConverterTypes.ITEM_STACK.in(schema)))), DataConverterSchemaV100.a(schema));
        });
        a(schema, map, "minecraft:villager_golem");
        a(schema, map, "minecraft:witch");
        a(schema, map, "minecraft:wither");
        a(schema, map, "minecraft:wither_skeleton");
        b(schema, map, "minecraft:wither_skull");
        a(schema, map, "minecraft:wolf");
        b(schema, map, "minecraft:xp_bottle");
        schema.registerSimple(map, "minecraft:xp_orb");
        a(schema, map, "minecraft:zombie");
        schema.register(map, "minecraft:zombie_horse", (s) -> {
            return DSL.optionalFields("SaddleItem", DataConverterTypes.ITEM_STACK.in(schema), DataConverterSchemaV100.a(schema));
        });
        a(schema, map, "minecraft:zombie_pigman");
        a(schema, map, "minecraft:zombie_villager");
        schema.registerSimple(map, "minecraft:evocation_fangs");
        a(schema, map, "minecraft:evocation_illager");
        schema.registerSimple(map, "minecraft:illusion_illager");
        schema.register(map, "minecraft:llama", (s) -> {
            return DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(schema), "DecorItem", DataConverterTypes.ITEM_STACK.in(schema), DataConverterSchemaV100.a(schema));
        });
        schema.registerSimple(map, "minecraft:llama_spit");
        a(schema, map, "minecraft:vex");
        a(schema, map, "minecraft:vindication_illager");
        return map;
    }

    public void registerTypes(Schema schema, Map<String, Supplier<TypeTemplate>> map, Map<String, Supplier<TypeTemplate>> map1) {
        super.registerTypes(schema, map, map1);
        schema.registerType(true, DataConverterTypes.ENTITY, () -> {
            return DSL.taggedChoiceLazy("id", DSL.namespacedString(), map);
        });
        schema.registerType(true, DataConverterTypes.ITEM_STACK, () -> {
            return DSL.hook(DSL.optionalFields("id", DataConverterTypes.r.in(schema), "tag", DSL.optionalFields("EntityTag", DataConverterTypes.o.in(schema), "BlockEntityTag", DataConverterTypes.k.in(schema), "CanDestroy", DSL.list(DataConverterTypes.q.in(schema)), "CanPlaceOn", DSL.list(DataConverterTypes.q.in(schema)))), DataConverterSchemaV705.a, HookFunction.IDENTITY);
        });
    }
}

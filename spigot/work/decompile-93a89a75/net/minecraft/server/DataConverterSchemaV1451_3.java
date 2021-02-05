package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;

public class DataConverterSchemaV1451_3 extends DataConverterSchemaNamed {

    public DataConverterSchemaV1451_3(int i, Schema schema) {
        super(i, schema);
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = super.registerEntities(schema);

        schema.registerSimple(map, "minecraft:egg");
        schema.registerSimple(map, "minecraft:ender_pearl");
        schema.registerSimple(map, "minecraft:fireball");
        schema.register(map, "minecraft:potion", (s) -> {
            return DSL.optionalFields("Potion", DataConverterTypes.ITEM_STACK.in(schema));
        });
        schema.registerSimple(map, "minecraft:small_fireball");
        schema.registerSimple(map, "minecraft:snowball");
        schema.registerSimple(map, "minecraft:wither_skull");
        schema.registerSimple(map, "minecraft:xp_bottle");
        schema.register(map, "minecraft:arrow", () -> {
            return DSL.optionalFields("inBlockState", DataConverterTypes.m.in(schema));
        });
        schema.register(map, "minecraft:enderman", () -> {
            return DSL.optionalFields("carriedBlockState", DataConverterTypes.m.in(schema), DataConverterSchemaV100.a(schema));
        });
        schema.register(map, "minecraft:falling_block", () -> {
            return DSL.optionalFields("BlockState", DataConverterTypes.m.in(schema), "TileEntityData", DataConverterTypes.k.in(schema));
        });
        schema.register(map, "minecraft:spectral_arrow", () -> {
            return DSL.optionalFields("inBlockState", DataConverterTypes.m.in(schema));
        });
        schema.register(map, "minecraft:chest_minecart", () -> {
            return DSL.optionalFields("DisplayState", DataConverterTypes.m.in(schema), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
        });
        schema.register(map, "minecraft:commandblock_minecart", () -> {
            return DSL.optionalFields("DisplayState", DataConverterTypes.m.in(schema));
        });
        schema.register(map, "minecraft:furnace_minecart", () -> {
            return DSL.optionalFields("DisplayState", DataConverterTypes.m.in(schema));
        });
        schema.register(map, "minecraft:hopper_minecart", () -> {
            return DSL.optionalFields("DisplayState", DataConverterTypes.m.in(schema), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
        });
        schema.register(map, "minecraft:minecart", () -> {
            return DSL.optionalFields("DisplayState", DataConverterTypes.m.in(schema));
        });
        schema.register(map, "minecraft:spawner_minecart", () -> {
            return DSL.optionalFields("DisplayState", DataConverterTypes.m.in(schema), DataConverterTypes.s.in(schema));
        });
        schema.register(map, "minecraft:tnt_minecart", () -> {
            return DSL.optionalFields("DisplayState", DataConverterTypes.m.in(schema));
        });
        return map;
    }
}

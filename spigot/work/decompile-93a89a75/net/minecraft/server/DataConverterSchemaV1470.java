package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;

public class DataConverterSchemaV1470 extends DataConverterSchemaNamed {

    public DataConverterSchemaV1470(int i, Schema schema) {
        super(i, schema);
    }

    protected static void a(Schema schema, Map<String, Supplier<TypeTemplate>> map, String s) {
        schema.register(map, s, () -> {
            return DataConverterSchemaV100.a(schema);
        });
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = super.registerEntities(schema);

        a(schema, map, "minecraft:turtle");
        a(schema, map, "minecraft:cod_mob");
        a(schema, map, "minecraft:tropical_fish");
        a(schema, map, "minecraft:salmon_mob");
        a(schema, map, "minecraft:puffer_fish");
        a(schema, map, "minecraft:phantom");
        a(schema, map, "minecraft:dolphin");
        a(schema, map, "minecraft:drowned");
        schema.register(map, "minecraft:trident", (s) -> {
            return DSL.optionalFields("inBlockState", DataConverterTypes.m.in(schema));
        });
        return map;
    }
}

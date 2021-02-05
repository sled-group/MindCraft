package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;

public class DataConverterSchemaV1928 extends DataConverterSchemaNamed {

    public DataConverterSchemaV1928(int i, Schema schema) {
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

        map.remove("minecraft:illager_beast");
        a(schema, map, "minecraft:ravager");
        return map;
    }
}

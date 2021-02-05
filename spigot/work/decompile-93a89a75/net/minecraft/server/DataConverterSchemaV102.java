package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import com.mojang.datafixers.types.templates.Hook.HookFunction;
import java.util.Map;
import java.util.function.Supplier;

public class DataConverterSchemaV102 extends Schema {

    public DataConverterSchemaV102(int i, Schema schema) {
        super(i, schema);
    }

    public void registerTypes(Schema schema, Map<String, Supplier<TypeTemplate>> map, Map<String, Supplier<TypeTemplate>> map1) {
        super.registerTypes(schema, map, map1);
        schema.registerType(true, DataConverterTypes.ITEM_STACK, () -> {
            return DSL.hook(DSL.optionalFields("id", DataConverterTypes.r.in(schema), "tag", DSL.optionalFields("EntityTag", DataConverterTypes.o.in(schema), "BlockEntityTag", DataConverterTypes.k.in(schema), "CanDestroy", DSL.list(DataConverterTypes.q.in(schema)), "CanPlaceOn", DSL.list(DataConverterTypes.q.in(schema)))), DataConverterSchemaV99.a, HookFunction.IDENTITY);
        });
    }
}

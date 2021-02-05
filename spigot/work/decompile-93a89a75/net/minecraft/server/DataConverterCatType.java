package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;

public class DataConverterCatType extends DataConverterNamedEntity {

    public DataConverterCatType(Schema schema, boolean flag) {
        super(schema, flag, "CatTypeFix", DataConverterTypes.ENTITY, "minecraft:cat");
    }

    public Dynamic<?> a(Dynamic<?> dynamic) {
        return dynamic.get("CatType").asInt(0) == 9 ? dynamic.set("CatType", dynamic.createInt(10)) : dynamic;
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        return typed.update(DSL.remainderFinder(), this::a);
    }
}

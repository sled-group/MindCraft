package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;

public class DataConverterColorlessShulkerEntity extends DataConverterNamedEntity {

    public DataConverterColorlessShulkerEntity(Schema schema, boolean flag) {
        super(schema, flag, "Colorless shulker entity fix", DataConverterTypes.ENTITY, "minecraft:shulker");
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        return typed.update(DSL.remainderFinder(), (dynamic) -> {
            return dynamic.get("Color").asInt(0) == 10 ? dynamic.set("Color", dynamic.createByte((byte) 16)) : dynamic;
        });
    }
}

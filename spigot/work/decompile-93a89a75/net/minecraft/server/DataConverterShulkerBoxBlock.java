package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;

public class DataConverterShulkerBoxBlock extends DataConverterNamedEntity {

    public DataConverterShulkerBoxBlock(Schema schema, boolean flag) {
        super(schema, flag, "BlockEntityShulkerBoxColorFix", DataConverterTypes.k, "minecraft:shulker_box");
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        return typed.update(DSL.remainderFinder(), (dynamic) -> {
            return dynamic.remove("Color");
        });
    }
}

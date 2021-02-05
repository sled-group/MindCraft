package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;

public class DataConverterItemFrame extends DataConverterNamedEntity {

    public DataConverterItemFrame(Schema schema, boolean flag) {
        super(schema, flag, "EntityItemFrameDirectionFix", DataConverterTypes.ENTITY, "minecraft:item_frame");
    }

    public Dynamic<?> a(Dynamic<?> dynamic) {
        return dynamic.set("Facing", dynamic.createByte(a(dynamic.get("Facing").asByte((byte) 0))));
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        return typed.update(DSL.remainderFinder(), this::a);
    }

    private static byte a(byte b0) {
        switch (b0) {
            case 0:
                return 3;
            case 1:
                return 4;
            case 2:
            default:
                return 2;
            case 3:
                return 5;
        }
    }
}

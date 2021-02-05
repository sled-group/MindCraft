package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Optional;

public class DataConverterPiston extends DataConverterNamedEntity {

    public DataConverterPiston(Schema schema, boolean flag) {
        super(schema, flag, "BlockEntityBlockStateFix", DataConverterTypes.k, "minecraft:piston");
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        Type<?> type = this.getOutputSchema().getChoiceType(DataConverterTypes.k, "minecraft:piston");
        Type<?> type1 = type.findFieldType("blockState");
        OpticFinder<?> opticfinder = DSL.fieldFinder("blockState", type1);
        Dynamic<?> dynamic = (Dynamic) typed.get(DSL.remainderFinder());
        int i = dynamic.get("blockId").asInt(0);

        dynamic = dynamic.remove("blockId");
        int j = dynamic.get("blockData").asInt(0) & 15;

        dynamic = dynamic.remove("blockData");
        Dynamic<?> dynamic1 = DataConverterFlattenData.b(i << 4 | j);
        Typed<?> typed1 = (Typed) type.pointTyped(typed.getOps()).orElseThrow(() -> {
            return new IllegalStateException("Could not create new piston block entity.");
        });

        return typed1.set(DSL.remainderFinder(), dynamic).set(opticfinder, (Typed) ((Optional) type1.readTyped(dynamic1).getSecond()).orElseThrow(() -> {
            return new IllegalStateException("Could not parse newly created block state tag.");
        }));
    }
}

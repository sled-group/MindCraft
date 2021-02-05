package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Optional;

public class DataConverterJukeBox extends DataConverterNamedEntity {

    public DataConverterJukeBox(Schema schema, boolean flag) {
        super(schema, flag, "BlockEntityJukeboxFix", DataConverterTypes.k, "minecraft:jukebox");
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        Type<?> type = this.getInputSchema().getChoiceType(DataConverterTypes.k, "minecraft:jukebox");
        Type<?> type1 = type.findFieldType("RecordItem");
        OpticFinder<?> opticfinder = DSL.fieldFinder("RecordItem", type1);
        Dynamic<?> dynamic = (Dynamic) typed.get(DSL.remainderFinder());
        int i = dynamic.get("Record").asInt(0);

        if (i > 0) {
            dynamic.remove("Record");
            String s = DataConverterFlatten.a(DataConverterMaterialId.a(i), 0);

            if (s != null) {
                Dynamic<?> dynamic1 = dynamic.emptyMap();

                dynamic1 = dynamic1.set("id", dynamic1.createString(s));
                dynamic1 = dynamic1.set("Count", dynamic1.createByte((byte) 1));
                return typed.set(opticfinder, (Typed) ((Optional) type1.readTyped(dynamic1).getSecond()).orElseThrow(() -> {
                    return new IllegalStateException("Could not create record item stack.");
                })).set(DSL.remainderFinder(), dynamic);
            }
        }

        return typed;
    }
}

package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Objects;

public class DataConverterChunkStatus extends DataFix {

    public DataConverterChunkStatus(Schema schema, boolean flag) {
        super(schema, flag);
    }

    protected TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.c);
        Type<?> type1 = type.findFieldType("Level");
        OpticFinder<?> opticfinder = DSL.fieldFinder("Level", type1);

        return this.fixTypeEverywhereTyped("ChunkStatusFix", type, this.getOutputSchema().getType(DataConverterTypes.c), (typed) -> {
            return typed.updateTyped(opticfinder, (typed1) -> {
                Dynamic<?> dynamic = (Dynamic) typed1.get(DSL.remainderFinder());
                String s = dynamic.get("Status").asString("empty");

                if (Objects.equals(s, "postprocessed")) {
                    dynamic = dynamic.set("Status", dynamic.createString("fullchunk"));
                }

                return typed1.set(DSL.remainderFinder(), dynamic);
            });
        });
    }
}

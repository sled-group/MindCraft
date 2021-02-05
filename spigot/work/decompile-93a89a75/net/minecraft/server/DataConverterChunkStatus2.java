package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Map;
import java.util.Objects;

public class DataConverterChunkStatus2 extends DataFix {

    private static final Map<String, String> a = ImmutableMap.builder().put("structure_references", "empty").put("biomes", "empty").put("base", "surface").put("carved", "carvers").put("liquid_carved", "liquid_carvers").put("decorated", "features").put("lighted", "light").put("mobs_spawned", "spawn").put("finalized", "heightmaps").put("fullchunk", "full").build();

    public DataConverterChunkStatus2(Schema schema, boolean flag) {
        super(schema, flag);
    }

    protected TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.c);
        Type<?> type1 = type.findFieldType("Level");
        OpticFinder<?> opticfinder = DSL.fieldFinder("Level", type1);

        return this.fixTypeEverywhereTyped("ChunkStatusFix2", type, this.getOutputSchema().getType(DataConverterTypes.c), (typed) -> {
            return typed.updateTyped(opticfinder, (typed1) -> {
                Dynamic<?> dynamic = (Dynamic) typed1.get(DSL.remainderFinder());
                String s = dynamic.get("Status").asString("empty");
                String s1 = (String) DataConverterChunkStatus2.a.getOrDefault(s, "empty");

                return Objects.equals(s, s1) ? typed1 : typed1.set(DSL.remainderFinder(), dynamic.set("Status", dynamic.createString(s1)));
            });
        });
    }
}

package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DataConverterPOI extends DataFix {

    public DataConverterPOI(Schema schema, boolean flag) {
        super(schema, flag);
    }

    protected TypeRewriteRule makeRule() {
        Type<Pair<String, Dynamic<?>>> type = DSL.named(DataConverterTypes.j.typeName(), DSL.remainderType());

        if (!Objects.equals(type, this.getInputSchema().getType(DataConverterTypes.j))) {
            throw new IllegalStateException("Poi type is not what was expected.");
        } else {
            return this.fixTypeEverywhere("POI reorganization", type, (dynamicops) -> {
                return (pair) -> {
                    return pair.mapSecond(DataConverterPOI::a);
                };
            });
        }
    }

    private static <T> Dynamic<T> a(Dynamic<T> dynamic) {
        Map<Dynamic<T>, Dynamic<T>> map = Maps.newHashMap();

        for (int i = 0; i < 16; ++i) {
            String s = String.valueOf(i);
            Optional<Dynamic<T>> optional = dynamic.get(s).get();

            if (optional.isPresent()) {
                Dynamic<T> dynamic1 = (Dynamic) optional.get();
                Dynamic<T> dynamic2 = dynamic.createMap(ImmutableMap.of(dynamic.createString("Records"), dynamic1));

                map.put(dynamic.createInt(i), dynamic2);
                dynamic = dynamic.remove(s);
            }
        }

        return dynamic.set("Sections", dynamic.createMap(map));
    }
}

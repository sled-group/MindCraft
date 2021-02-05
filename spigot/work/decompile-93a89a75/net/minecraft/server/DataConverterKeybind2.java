package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DataConverterKeybind2 extends DataFix {

    public DataConverterKeybind2(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("OptionsKeyTranslationFix", this.getInputSchema().getType(DataConverterTypes.e), (typed) -> {
            return typed.update(DSL.remainderFinder(), (dynamic) -> {
                return (Dynamic) dynamic.getMapValues().map((map) -> {
                    return dynamic.createMap((Map) map.entrySet().stream().map((entry) -> {
                        if (((Dynamic) entry.getKey()).asString("").startsWith("key_")) {
                            String s = ((Dynamic) entry.getValue()).asString("");

                            if (!s.startsWith("key.mouse") && !s.startsWith("scancode.")) {
                                return Pair.of(entry.getKey(), dynamic.createString("key.keyboard." + s.substring("key.".length())));
                            }
                        }

                        return Pair.of(entry.getKey(), entry.getValue());
                    }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)));
                }).orElse(dynamic);
            });
        });
    }
}

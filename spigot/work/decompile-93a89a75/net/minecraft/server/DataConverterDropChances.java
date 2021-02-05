package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class DataConverterDropChances extends DataFix {

    public DataConverterDropChances(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("EntityRedundantChanceTagsFix", this.getInputSchema().getType(DataConverterTypes.ENTITY), (typed) -> {
            return typed.update(DSL.remainderFinder(), (dynamic) -> {
                Dynamic<?> dynamic1 = dynamic;

                if (Objects.equals(dynamic.get("HandDropChances"), Optional.of(dynamic.createList(Stream.generate(() -> {
                    return dynamic.createFloat(0.0F);
                }).limit(2L))))) {
                    dynamic = dynamic.remove("HandDropChances");
                }

                if (Objects.equals(dynamic.get("ArmorDropChances"), Optional.of(dynamic.createList(Stream.generate(() -> {
                    return dynamic1.createFloat(0.0F);
                }).limit(4L))))) {
                    dynamic = dynamic.remove("ArmorDropChances");
                }

                return dynamic;
            });
        });
    }
}

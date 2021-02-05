package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import java.util.function.Function;

public abstract class DataConverterItemName extends DataFix {

    private final String a;

    public DataConverterItemName(Schema schema, String s) {
        super(schema, false);
        this.a = s;
    }

    public TypeRewriteRule makeRule() {
        Type<Pair<String, String>> type = DSL.named(DataConverterTypes.r.typeName(), DSL.namespacedString());

        if (!Objects.equals(this.getInputSchema().getType(DataConverterTypes.r), type)) {
            throw new IllegalStateException("item name type is not what was expected.");
        } else {
            return this.fixTypeEverywhere(this.a, type, (dynamicops) -> {
                return (pair) -> {
                    return pair.mapSecond(this::a);
                };
            });
        }
    }

    protected abstract String a(String s);

    public static DataFix a(Schema schema, String s, final Function<String, String> function) {
        return new DataConverterItemName(schema, s) {
            @Override
            protected String a(String s1) {
                return (String) function.apply(s1);
            }
        };
    }
}

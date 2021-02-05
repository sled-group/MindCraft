package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TaggedChoice.TaggedChoiceType;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;

public abstract class DataConverterEntityRenameAbstract extends DataFix {

    private final String a;

    public DataConverterEntityRenameAbstract(String s, Schema schema, boolean flag) {
        super(schema, flag);
        this.a = s;
    }

    public TypeRewriteRule makeRule() {
        TaggedChoiceType<String> taggedchoicetype = this.getInputSchema().findChoiceType(DataConverterTypes.ENTITY);
        TaggedChoiceType<String> taggedchoicetype1 = this.getOutputSchema().findChoiceType(DataConverterTypes.ENTITY);
        Type<Pair<String, String>> type = DSL.named(DataConverterTypes.n.typeName(), DSL.namespacedString());

        if (!Objects.equals(this.getOutputSchema().getType(DataConverterTypes.n), type)) {
            throw new IllegalStateException("Entity name type is not what was expected.");
        } else {
            return TypeRewriteRule.seq(this.fixTypeEverywhere(this.a, taggedchoicetype, taggedchoicetype1, (dynamicops) -> {
                return (pair) -> {
                    return pair.mapFirst((s) -> {
                        String s1 = this.a(s);
                        Type<?> type1 = (Type) taggedchoicetype.types().get(s);
                        Type<?> type2 = (Type) taggedchoicetype1.types().get(s1);

                        if (!type2.equals(type1, true, true)) {
                            throw new IllegalStateException(String.format("Dynamic type check failed: %s not equal to %s", type2, type1));
                        } else {
                            return s1;
                        }
                    });
                };
            }), this.fixTypeEverywhere(this.a + " for entity name", type, (dynamicops) -> {
                return (pair) -> {
                    return pair.mapSecond(this::a);
                };
            }));
        }
    }

    protected abstract String a(String s);
}

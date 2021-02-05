package net.minecraft.server;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.DSL.TypeReference;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.templates.TaggedChoice.TaggedChoiceType;
import com.mojang.datafixers.util.Pair;

public class DataConverterAddChoices extends DataFix {

    private final String a;
    private final TypeReference b;

    public DataConverterAddChoices(Schema schema, String s, TypeReference typereference) {
        super(schema, true);
        this.a = s;
        this.b = typereference;
    }

    public TypeRewriteRule makeRule() {
        TaggedChoiceType<?> taggedchoicetype = this.getInputSchema().findChoiceType(this.b);
        TaggedChoiceType<?> taggedchoicetype1 = this.getOutputSchema().findChoiceType(this.b);

        return this.a(this.a, taggedchoicetype, taggedchoicetype1);
    }

    protected final <K> TypeRewriteRule a(String s, TaggedChoiceType<K> taggedchoicetype, TaggedChoiceType<?> taggedchoicetype1) {
        if (taggedchoicetype.getKeyType() != taggedchoicetype1.getKeyType()) {
            throw new IllegalStateException("Could not inject: key type is not the same");
        } else {
            return this.fixTypeEverywhere(s, taggedchoicetype, taggedchoicetype1, (dynamicops) -> {
                return (pair) -> {
                    if (!taggedchoicetype1.hasType(pair.getFirst())) {
                        throw new IllegalArgumentException(String.format("Unknown type %s in %s ", pair.getFirst(), this.b));
                    } else {
                        return pair;
                    }
                };
            });
        }
    }
}

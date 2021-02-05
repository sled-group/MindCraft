package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.DSL.TypeReference;
import com.mojang.datafixers.schemas.Schema;

public abstract class DataConverterNamedEntity extends DataFix {

    private final String a;
    private final String b;
    private final TypeReference c;

    public DataConverterNamedEntity(Schema schema, boolean flag, String s, TypeReference typereference, String s1) {
        super(schema, flag);
        this.a = s;
        this.c = typereference;
        this.b = s1;
    }

    public TypeRewriteRule makeRule() {
        OpticFinder<?> opticfinder = DSL.namedChoice(this.b, this.getInputSchema().getChoiceType(this.c, this.b));

        return this.fixTypeEverywhereTyped(this.a, this.getInputSchema().getType(this.c), this.getOutputSchema().getType(this.c), (typed) -> {
            return typed.updateTyped(opticfinder, this.getOutputSchema().getChoiceType(this.c, this.b), this::a);
        });
    }

    protected abstract Typed<?> a(Typed<?> typed);
}

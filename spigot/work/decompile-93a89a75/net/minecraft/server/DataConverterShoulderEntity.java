package net.minecraft.server;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.DSL.TypeReference;
import com.mojang.datafixers.schemas.Schema;

public class DataConverterShoulderEntity extends DataFix {

    private final String a;
    private final TypeReference b;

    public DataConverterShoulderEntity(Schema schema, String s, TypeReference typereference) {
        super(schema, true);
        this.a = s;
        this.b = typereference;
    }

    protected TypeRewriteRule makeRule() {
        return this.writeAndRead(this.a, this.getInputSchema().getType(this.b), this.getOutputSchema().getType(this.b));
    }
}

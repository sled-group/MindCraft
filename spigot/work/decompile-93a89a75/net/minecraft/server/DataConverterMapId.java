package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Optional;

public class DataConverterMapId extends DataFix {

    public DataConverterMapId(Schema schema, boolean flag) {
        super(schema, flag);
    }

    protected TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.h);
        OpticFinder<?> opticfinder = type.findField("data");

        return this.fixTypeEverywhereTyped("Map id fix", type, (typed) -> {
            Optional<? extends Typed<?>> optional = typed.getOptionalTyped(opticfinder);

            return optional.isPresent() ? typed : typed.update(DSL.remainderFinder(), (dynamic) -> {
                return dynamic.emptyMap().merge(dynamic.createString("data"), dynamic);
            });
        });
    }
}

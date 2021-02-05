package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import java.util.Optional;
import java.util.UUID;

public class DataConverterUUID extends DataFix {

    public DataConverterUUID(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("EntityStringUuidFix", this.getInputSchema().getType(DataConverterTypes.ENTITY), (typed) -> {
            return typed.update(DSL.remainderFinder(), (dynamic) -> {
                Optional<String> optional = dynamic.get("UUID").asString();

                if (optional.isPresent()) {
                    UUID uuid = UUID.fromString((String) optional.get());

                    return dynamic.remove("UUID").set("UUIDMost", dynamic.createLong(uuid.getMostSignificantBits())).set("UUIDLeast", dynamic.createLong(uuid.getLeastSignificantBits()));
                } else {
                    return dynamic;
                }
            });
        });
    }
}

package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;

public class DataConverterEntityCatSplit extends DataConverterEntityNameAbstract {

    public DataConverterEntityCatSplit(Schema schema, boolean flag) {
        super("EntityCatSplitFix", schema, flag);
    }

    @Override
    protected Pair<String, Dynamic<?>> a(String s, Dynamic<?> dynamic) {
        if (Objects.equals("minecraft:ocelot", s)) {
            int i = dynamic.get("CatType").asInt(0);

            if (i == 0) {
                String s1 = dynamic.get("Owner").asString("");
                String s2 = dynamic.get("OwnerUUID").asString("");

                if (s1.length() > 0 || s2.length() > 0) {
                    dynamic.set("Trusting", dynamic.createBoolean(true));
                }
            } else if (i > 0 && i < 4) {
                dynamic = dynamic.set("CatType", dynamic.createInt(i));
                dynamic = dynamic.set("OwnerUUID", dynamic.createString(dynamic.get("OwnerUUID").asString("")));
                return Pair.of("minecraft:cat", dynamic);
            }
        }

        return Pair.of(s, dynamic);
    }
}

package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import java.util.Optional;

public class DataConverterBedItem extends DataFix {

    public DataConverterBedItem(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        OpticFinder<Pair<String, String>> opticfinder = DSL.fieldFinder("id", DSL.named(DataConverterTypes.r.typeName(), DSL.namespacedString()));

        return this.fixTypeEverywhereTyped("BedItemColorFix", this.getInputSchema().getType(DataConverterTypes.ITEM_STACK), (typed) -> {
            Optional<Pair<String, String>> optional = typed.getOptional(opticfinder);

            if (optional.isPresent() && Objects.equals(((Pair) optional.get()).getSecond(), "minecraft:bed")) {
                Dynamic<?> dynamic = (Dynamic) typed.get(DSL.remainderFinder());

                if (dynamic.get("Damage").asInt(0) == 0) {
                    return typed.set(DSL.remainderFinder(), dynamic.set("Damage", dynamic.createShort((short) 14)));
                }
            }

            return typed;
        });
    }
}

package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import java.util.Optional;

public class DataConverterPotionWater extends DataFix {

    public DataConverterPotionWater(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.ITEM_STACK);
        OpticFinder<Pair<String, String>> opticfinder = DSL.fieldFinder("id", DSL.named(DataConverterTypes.r.typeName(), DSL.namespacedString()));
        OpticFinder<?> opticfinder1 = type.findField("tag");

        return this.fixTypeEverywhereTyped("ItemWaterPotionFix", type, (typed) -> {
            Optional<Pair<String, String>> optional = typed.getOptional(opticfinder);

            if (optional.isPresent()) {
                String s = (String) ((Pair) optional.get()).getSecond();

                if ("minecraft:potion".equals(s) || "minecraft:splash_potion".equals(s) || "minecraft:lingering_potion".equals(s) || "minecraft:tipped_arrow".equals(s)) {
                    Typed<?> typed1 = typed.getOrCreateTyped(opticfinder1);
                    Dynamic<?> dynamic = (Dynamic) typed1.get(DSL.remainderFinder());

                    if (!dynamic.get("Potion").asString().isPresent()) {
                        dynamic = dynamic.set("Potion", dynamic.createString("minecraft:water"));
                    }

                    return typed.set(opticfinder1, typed1.set(DSL.remainderFinder(), dynamic));
                }
            }

            return typed;
        });
    }
}

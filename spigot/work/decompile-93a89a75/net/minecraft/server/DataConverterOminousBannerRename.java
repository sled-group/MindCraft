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
import java.util.Objects;
import java.util.Optional;

public class DataConverterOminousBannerRename extends DataFix {

    public DataConverterOminousBannerRename(Schema schema, boolean flag) {
        super(schema, flag);
    }

    private Dynamic<?> a(Dynamic<?> dynamic) {
        Optional<? extends Dynamic<?>> optional = dynamic.get("display").get();

        if (optional.isPresent()) {
            Dynamic<?> dynamic1 = (Dynamic) optional.get();
            Optional<String> optional1 = dynamic1.get("Name").asString();

            if (optional1.isPresent()) {
                String s = (String) optional1.get();

                s = s.replace("\"translate\":\"block.minecraft.illager_banner\"", "\"translate\":\"block.minecraft.ominous_banner\"");
                dynamic1 = dynamic1.set("Name", dynamic1.createString(s));
            }

            return dynamic.set("display", dynamic1);
        } else {
            return dynamic;
        }
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.ITEM_STACK);
        OpticFinder<Pair<String, String>> opticfinder = DSL.fieldFinder("id", DSL.named(DataConverterTypes.r.typeName(), DSL.namespacedString()));
        OpticFinder<?> opticfinder1 = type.findField("tag");

        return this.fixTypeEverywhereTyped("OminousBannerRenameFix", type, (typed) -> {
            Optional<Pair<String, String>> optional = typed.getOptional(opticfinder);

            if (optional.isPresent() && Objects.equals(((Pair) optional.get()).getSecond(), "minecraft:white_banner")) {
                Optional<? extends Typed<?>> optional1 = typed.getOptionalTyped(opticfinder1);

                if (optional1.isPresent()) {
                    Typed<?> typed1 = (Typed) optional1.get();
                    Dynamic<?> dynamic = (Dynamic) typed1.get(DSL.remainderFinder());

                    return typed.set(opticfinder1, typed1.set(DSL.remainderFinder(), this.a(dynamic)));
                }
            }

            return typed;
        });
    }
}

package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Optional;
import java.util.stream.Stream;

public class DataConverterItemLoreComponentize extends DataFix {

    public DataConverterItemLoreComponentize(Schema schema, boolean flag) {
        super(schema, flag);
    }

    protected TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.ITEM_STACK);
        OpticFinder<?> opticfinder = type.findField("tag");

        return this.fixTypeEverywhereTyped("Item Lore componentize", type, (typed) -> {
            return typed.updateTyped(opticfinder, (typed1) -> {
                return typed1.update(DSL.remainderFinder(), (dynamic) -> {
                    return dynamic.update("display", (dynamic1) -> {
                        return dynamic1.update("Lore", (dynamic2) -> {
                            Optional optional = dynamic2.asStreamOpt().map(DataConverterItemLoreComponentize::a);

                            dynamic2.getClass();
                            return (Dynamic) DataFixUtils.orElse(optional.map(dynamic2::createList), dynamic2);
                        });
                    });
                });
            });
        });
    }

    private static <T> Stream<Dynamic<T>> a(Stream<Dynamic<T>> stream) {
        return stream.map((dynamic) -> {
            Optional optional = dynamic.asString().map(DataConverterItemLoreComponentize::a);

            dynamic.getClass();
            return (Dynamic) DataFixUtils.orElse(optional.map(dynamic::createString), dynamic);
        });
    }

    private static String a(String s) {
        return IChatBaseComponent.ChatSerializer.a((IChatBaseComponent) (new ChatComponentText(s)));
    }
}

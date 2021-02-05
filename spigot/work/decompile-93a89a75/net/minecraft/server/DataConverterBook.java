package net.minecraft.server;

import com.google.gson.JsonParseException;
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
import org.apache.commons.lang3.StringUtils;

public class DataConverterBook extends DataFix {

    public DataConverterBook(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public Dynamic<?> a(Dynamic<?> dynamic) {
        return dynamic.update("pages", (dynamic1) -> {
            Optional optional = dynamic1.asStreamOpt().map((stream) -> {
                return stream.map((dynamic2) -> {
                    if (!dynamic2.asString().isPresent()) {
                        return dynamic2;
                    } else {
                        String s = dynamic2.asString("");
                        Object object = null;

                        if (!"null".equals(s) && !StringUtils.isEmpty(s)) {
                            if ((s.charAt(0) != '"' || s.charAt(s.length() - 1) != '"') && (s.charAt(0) != '{' || s.charAt(s.length() - 1) != '}')) {
                                object = new ChatComponentText(s);
                            } else {
                                try {
                                    object = (IChatBaseComponent) ChatDeserializer.a(DataConverterSignText.a, s, IChatBaseComponent.class, true);
                                    if (object == null) {
                                        object = new ChatComponentText("");
                                    }
                                } catch (JsonParseException jsonparseexception) {
                                    ;
                                }

                                if (object == null) {
                                    try {
                                        object = IChatBaseComponent.ChatSerializer.a(s);
                                    } catch (JsonParseException jsonparseexception1) {
                                        ;
                                    }
                                }

                                if (object == null) {
                                    try {
                                        object = IChatBaseComponent.ChatSerializer.b(s);
                                    } catch (JsonParseException jsonparseexception2) {
                                        ;
                                    }
                                }

                                if (object == null) {
                                    object = new ChatComponentText(s);
                                }
                            }
                        } else {
                            object = new ChatComponentText("");
                        }

                        return dynamic2.createString(IChatBaseComponent.ChatSerializer.a((IChatBaseComponent) object));
                    }
                });
            });

            dynamic.getClass();
            return (Dynamic) DataFixUtils.orElse(optional.map(dynamic::createList), dynamic.emptyList());
        });
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.ITEM_STACK);
        OpticFinder<?> opticfinder = type.findField("tag");

        return this.fixTypeEverywhereTyped("ItemWrittenBookPagesStrictJsonFix", type, (typed) -> {
            return typed.updateTyped(opticfinder, (typed1) -> {
                return typed1.update(DSL.remainderFinder(), this::a);
            });
        });
    }
}

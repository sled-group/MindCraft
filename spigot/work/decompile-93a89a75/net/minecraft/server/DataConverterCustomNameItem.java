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

public class DataConverterCustomNameItem extends DataFix {

    public DataConverterCustomNameItem(Schema schema, boolean flag) {
        super(schema, flag);
    }

    private Dynamic<?> a(Dynamic<?> dynamic) {
        Optional<? extends Dynamic<?>> optional = dynamic.get("display").get();

        if (optional.isPresent()) {
            Dynamic<?> dynamic1 = (Dynamic) optional.get();
            Optional<String> optional1 = dynamic1.get("Name").asString();

            if (optional1.isPresent()) {
                dynamic1 = dynamic1.set("Name", dynamic1.createString(IChatBaseComponent.ChatSerializer.a((IChatBaseComponent) (new ChatComponentText((String) optional1.get())))));
            } else {
                Optional<String> optional2 = dynamic1.get("LocName").asString();

                if (optional2.isPresent()) {
                    dynamic1 = dynamic1.set("Name", dynamic1.createString(IChatBaseComponent.ChatSerializer.a((IChatBaseComponent) (new ChatMessage((String) optional2.get(), new Object[0])))));
                    dynamic1 = dynamic1.remove("LocName");
                }
            }

            return dynamic.set("display", dynamic1);
        } else {
            return dynamic;
        }
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.ITEM_STACK);
        OpticFinder<?> opticfinder = type.findField("tag");

        return this.fixTypeEverywhereTyped("ItemCustomNameToComponentFix", type, (typed) -> {
            return typed.updateTyped(opticfinder, (typed1) -> {
                return typed1.update(DSL.remainderFinder(), this::a);
            });
        });
    }
}

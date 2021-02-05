package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import java.util.Objects;
import java.util.Optional;

public class DataConverterCustomNameEntity extends DataFix {

    public DataConverterCustomNameEntity(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        OpticFinder<String> opticfinder = DSL.fieldFinder("id", DSL.namespacedString());

        return this.fixTypeEverywhereTyped("EntityCustomNameToComponentFix", this.getInputSchema().getType(DataConverterTypes.ENTITY), (typed) -> {
            return typed.update(DSL.remainderFinder(), (dynamic) -> {
                Optional<String> optional = typed.getOptional(opticfinder);

                return optional.isPresent() && Objects.equals(optional.get(), "minecraft:commandblock_minecart") ? dynamic : a(dynamic);
            });
        });
    }

    public static Dynamic<?> a(Dynamic<?> dynamic) {
        String s = dynamic.get("CustomName").asString("");

        return s.isEmpty() ? dynamic.remove("CustomName") : dynamic.set("CustomName", dynamic.createString(IChatBaseComponent.ChatSerializer.a((IChatBaseComponent) (new ChatComponentText(s)))));
    }
}

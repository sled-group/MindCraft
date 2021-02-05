package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.List.ListType;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import java.util.function.Function;

public class DataConverterVillagerTrade extends DataConverterNamedEntity {

    public DataConverterVillagerTrade(Schema schema, boolean flag) {
        super(schema, flag, "Villager trade fix", DataConverterTypes.ENTITY, "minecraft:villager");
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        OpticFinder<?> opticfinder = typed.getType().findField("Offers");
        OpticFinder<?> opticfinder1 = opticfinder.type().findField("Recipes");
        Type<?> type = opticfinder1.type();

        if (!(type instanceof ListType)) {
            throw new IllegalStateException("Recipes are expected to be a list.");
        } else {
            ListType<?> listtype = (ListType) type;
            Type<?> type1 = listtype.getElement();
            OpticFinder<?> opticfinder2 = DSL.typeFinder(type1);
            OpticFinder<?> opticfinder3 = type1.findField("buy");
            OpticFinder<?> opticfinder4 = type1.findField("buyB");
            OpticFinder<?> opticfinder5 = type1.findField("sell");
            OpticFinder<Pair<String, String>> opticfinder6 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.r.typeName(), DSL.namespacedString()));
            Function<Typed<?>, Typed<?>> function = (typed1) -> {
                return this.a(opticfinder6, typed1);
            };

            return typed.updateTyped(opticfinder, (typed1) -> {
                return typed1.updateTyped(opticfinder1, (typed2) -> {
                    return typed2.updateTyped(opticfinder2, (typed3) -> {
                        return typed3.updateTyped(opticfinder3, function).updateTyped(opticfinder4, function).updateTyped(opticfinder5, function);
                    });
                });
            });
        }
    }

    private Typed<?> a(OpticFinder<Pair<String, String>> opticfinder, Typed<?> typed) {
        return typed.update(opticfinder, (pair) -> {
            return pair.mapSecond((s) -> {
                return Objects.equals(s, "minecraft:carved_pumpkin") ? "minecraft:pumpkin" : s;
            });
        });
    }
}

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
import com.mojang.datafixers.types.templates.CompoundList.CompoundListType;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataConverterNewVillage extends DataFix {

    public DataConverterNewVillage(Schema schema, boolean flag) {
        super(schema, flag);
    }

    protected TypeRewriteRule makeRule() {
        CompoundListType<String, ?> compoundlisttype = DSL.compoundList(DSL.string(), this.getInputSchema().getType(DataConverterTypes.t));
        OpticFinder<? extends List<? extends Pair<String, ?>>> opticfinder = compoundlisttype.finder();

        return this.a(compoundlisttype);
    }

    private <SF> TypeRewriteRule a(CompoundListType<String, SF> compoundlisttype) {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.c);
        Type<?> type1 = this.getInputSchema().getType(DataConverterTypes.t);
        OpticFinder<?> opticfinder = type.findField("Level");
        OpticFinder<?> opticfinder1 = opticfinder.type().findField("Structures");
        OpticFinder<?> opticfinder2 = opticfinder1.type().findField("Starts");
        OpticFinder<List<Pair<String, SF>>> opticfinder3 = compoundlisttype.finder();

        return TypeRewriteRule.seq(this.fixTypeEverywhereTyped("NewVillageFix", type, (typed) -> {
            return typed.updateTyped(opticfinder, (typed1) -> {
                return typed1.updateTyped(opticfinder1, (typed2) -> {
                    return typed2.updateTyped(opticfinder2, (typed3) -> {
                        return typed3.update(opticfinder3, (list) -> {
                            return (List) list.stream().filter((pair) -> {
                                return !Objects.equals(pair.getFirst(), "Village");
                            }).map((pair) -> {
                                return pair.mapFirst((s) -> {
                                    return s.equals("New_Village") ? "Village" : s;
                                });
                            }).collect(Collectors.toList());
                        });
                    }).update(DSL.remainderFinder(), (dynamic) -> {
                        return dynamic.update("References", (dynamic1) -> {
                            Optional<? extends Dynamic<?>> optional = dynamic1.get("New_Village").get();

                            return ((Dynamic) DataFixUtils.orElse(optional.map((dynamic2) -> {
                                return dynamic1.remove("New_Village").merge(dynamic1.createString("Village"), dynamic2);
                            }), dynamic1)).remove("Village");
                        });
                    });
                });
            });
        }), this.fixTypeEverywhereTyped("NewVillageStartFix", type1, (typed) -> {
            return typed.update(DSL.remainderFinder(), (dynamic) -> {
                return dynamic.update("id", (dynamic1) -> {
                    return Objects.equals(DataConverterSchemaNamed.a(dynamic1.asString("")), "minecraft:new_village") ? dynamic1.createString("minecraft:village") : dynamic1;
                });
            });
        }));
    }
}

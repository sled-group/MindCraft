package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.List.ListType;
import java.util.Optional;

public class DataConverterVillagerLevelXp extends DataFix {

    private static final int[] a = new int[]{0, 10, 50, 100, 150};

    public static int a(int i) {
        return DataConverterVillagerLevelXp.a[MathHelper.clamp(i - 1, 0, DataConverterVillagerLevelXp.a.length - 1)];
    }

    public DataConverterVillagerLevelXp(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getChoiceType(DataConverterTypes.ENTITY, "minecraft:villager");
        OpticFinder<?> opticfinder = DSL.namedChoice("minecraft:villager", type);
        OpticFinder<?> opticfinder1 = type.findField("Offers");
        Type<?> type1 = opticfinder1.type();
        OpticFinder<?> opticfinder2 = type1.findField("Recipes");
        ListType<?> listtype = (ListType) opticfinder2.type();
        OpticFinder<?> opticfinder3 = listtype.getElement().finder();

        return this.fixTypeEverywhereTyped("Villager level and xp rebuild", this.getInputSchema().getType(DataConverterTypes.ENTITY), (typed) -> {
            return typed.updateTyped(opticfinder, type, (typed1) -> {
                Dynamic<?> dynamic = (Dynamic) typed1.get(DSL.remainderFinder());
                int i = ((Number) dynamic.get("VillagerData").get("level").asNumber().orElse(0)).intValue();
                Typed<?> typed2 = typed1;

                if (i == 0 || i == 1) {
                    int j = (Integer) typed1.getOptionalTyped(opticfinder1).flatMap((typed3) -> {
                        return typed3.getOptionalTyped(opticfinder2);
                    }).map((typed3) -> {
                        return typed3.getAllTyped(opticfinder3).size();
                    }).orElse(0);

                    i = MathHelper.clamp(j / 2, 1, 5);
                    if (i > 1) {
                        typed2 = a(typed1, i);
                    }
                }

                Optional<Number> optional = dynamic.get("Xp").asNumber();

                if (!optional.isPresent()) {
                    typed2 = b(typed2, i);
                }

                return typed2;
            });
        });
    }

    private static Typed<?> a(Typed<?> typed, int i) {
        return typed.update(DSL.remainderFinder(), (dynamic) -> {
            return dynamic.update("VillagerData", (dynamic1) -> {
                return dynamic1.set("level", dynamic1.createInt(i));
            });
        });
    }

    private static Typed<?> b(Typed<?> typed, int i) {
        int j = a(i);

        return typed.update(DSL.remainderFinder(), (dynamic) -> {
            return dynamic.set("Xp", dynamic.createInt(j));
        });
    }
}
